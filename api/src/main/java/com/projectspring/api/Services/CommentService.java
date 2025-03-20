package com.projectspring.api.Services;

import java.io.IOException;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.projectspring.api.Dto.CommentDto;
import com.projectspring.api.Entities.Comment;
import com.projectspring.api.Entities.Place;
import com.projectspring.api.Entities.User;
import com.projectspring.api.Generic.GenericService;
import com.projectspring.api.Generic.GenericServiceImpl;
import com.projectspring.api.Mappers.CommentMapper;
import com.projectspring.api.Repositories.CommentRepository;
import com.projectspring.api.Repositories.PlaceRepository;
import com.projectspring.api.Repositories.UserRepository;
import com.projectspring.api.Services.Filestorage.FileStorageService;

import jakarta.transaction.Transactional;

import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService extends GenericServiceImpl<Comment, Long, CommentDto, CommentRepository, CommentMapper>
        implements GenericService<CommentDto, Long> {

    public CommentService(CommentRepository repository, CommentMapper mapper) {
        super(repository, mapper);
    }

    /**
     * Attribut permettant d'utiliser le système de log "slf4j" (API de
     * journalisation Java)
     * Pour plus d'informations sur slf4j ->
     * https://www.baeldung.com/slf4j-with-log4j2-logback
     */
    Logger logger = LoggerFactory.getLogger(FileStorageService.class);

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileStorageService fileStorageService;

    /**
     * Ajoute un commentaire à un lieu en associant automatiquement l'utilisateur
     * authentifié.
     * Si une image est fournie, elle est stockée de manière sécurisée avec un nom
     * unique basé sur un hash.
     *
     * @param comment Les données du commentaire à enregistrer.
     * @param placeId L'ID du lieu auquel le commentaire est rattaché.
     * @return Le commentaire enregistré sous forme de DTO.
     * @throws ResponseStatusException Si l'utilisateur ou le lieu n'existent pas
     *                                 (404),
     *                                 ou en cas d'erreur de sauvegarde de l'image
     *                                 (500).
     */
    public CommentDto postCommentByPlace(CommentDto comment, Long placeId) {
        try {
            // Récupération de l'utilisateur connecté depuis le contexte de sécurité
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username;

            if (principal instanceof UserDetails userDetails) {
                username = userDetails.getUsername();
            } else {
                username = principal.toString();
            }

            // Vérification de l'existence de l'utilisateur
            User user = Optional.ofNullable(userRepository.findByUsername(username))
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

            // Vérification de l'existence du lieu
            Place place = placeRepository.findById(placeId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found"));

            // Gestion de l'image du commentaire
            MultipartFile picture = comment.getPicture();
            if (picture != null && !picture.isEmpty()) {
                // Génération d'un nom unique pour éviter les conflits
                String storageHash = fileStorageService.getStorageHash(picture).get();
                Path rootLocation = fileStorageService.getRootLocation();
                String fileExtension = fileStorageService.mimeTypeToExtension(picture.getContentType());
                storageHash += fileExtension;
                Path saveLocation = rootLocation.resolve(storageHash);

                // Suppression de l'image existante si nécessaire
                Files.deleteIfExists(saveLocation);

                // Sauvegarde du fichier
                Files.copy(picture.getInputStream(), saveLocation);

                // Enregistrement du nom de l'image dans le commentaire
                comment.setImageName(storageHash);
            }

            // Association du commentaire avec le lieu et l'utilisateur
            comment.setPlace(place);
            comment.setUser(user);

            return saveOrUpdate(comment);

        } catch (IOException e) {
            logger.error("Erreur lors de la sauvegarde de l'image", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erreur lors de la sauvegarde de l'image");
        }
    }

    /**
     * Supprime un commentaire si l'utilisateur authentifié est soit :
     * - Le créateur du commentaire.
     * - Un modérateur (ROLE_MODO).
     *
     * @param commentId L'ID du commentaire à supprimer.
     * @throws ResponseStatusException Si le commentaire n'existe pas (404) ou si
     *                                 l'utilisateur
     *                                 n'est ni l'auteur ni un modérateur (403).
     */
    public void deleteComment(Long commentId) {
        // Recherche du commentaire dans la base de données
        Comment comment = repository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found"));

        // Récupération de l'utilisateur authentifié
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername(); // Si l'utilisateur est un UserDetails, on récupère son username
        } else {
            username = principal.toString(); // Sinon, on utilise la chaîne brute du principal
        }

        // Vérification des permissions :
        boolean isOwner = comment.getUser().getUsername().equals(username); // L'utilisateur est-il le créateur ?
        boolean isModerator = comment.getUser().getRoles().stream()
                .anyMatch(role -> role.getName().equals("ROLE_MODO")); // A-t-il le rôle de modérateur ?

        // Si l'utilisateur n'est ni propriétaire ni modérateur, refus de suppression
        if (!isOwner && !isModerator) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to delete this comment");
        }

        // Suppression du commentaire
        repository.deleteById(commentId);
    }

    /**
     * Récupère tous les commentaires associés à un lieu spécifique.
     *
     * @param placeId L'ID du lieu dont on souhaite obtenir les commentaires.
     * @return Une liste de commentaires associés au lieu.
     * @throws ResponseStatusException Si le lieu n'existe pas.
     */
    public List<Comment> getCommentsByPlace(Long placeId) {
        // Vérifier si le lieu existe avant de récupérer les commentaires
        if (!placeRepository.existsById(placeId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found");
        }

        return repository.findByPlaceId(placeId);
    }

    /**
     * Récupère tous les commentaires associés à un lieu spécifique.
     *
     * @param placeId L'ID du lieu dont on souhaite obtenir les commentaires.
     * @return Une liste de commentaires associés au lieu.
     * @throws ResponseStatusException Si le lieu n'existe pas.
     */
    public List<Comment> getCommentsByUser(Long userId) {
        // Vérifier si l'utilisateur existe avant de récupérer les commentaires
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        return repository.findByUserId(userId);
    }

    public CommentDto updateComment(Long commentId, CommentDto comment) {
        // Récupération de l'utilisateur authentifié
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername(); // Si l'utilisateur est un UserDetails, on récupère son username
        } else {
            username = principal.toString(); // Sinon, on utilise la chaîne brute du principal
        }

        // Vérification de l'existence de l'utilisateur
        User user = Optional.ofNullable(userRepository.findByUsername(username))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // Récupération du commentaire existant
        Comment existingComment = repository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found"));

        // Vérification que le commentaire appartient bien à l'utilisateur connecté
        if (!existingComment.getUser().equals(user)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not authorized to update this comment");
        }

        // Gestion sécurisée de l'image
        MultipartFile picture = comment.getPicture();
        if (picture != null && !picture.isEmpty()) {
            String fileExtension = fileStorageService.mimeTypeToExtension(picture.getContentType());
            if (fileExtension == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid file type");
            }

            String storageHash = fileStorageService.getStorageHash(picture).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                            "Failed to generate file hash"));
            Path saveLocation = fileStorageService.getRootLocation().resolve(storageHash + fileExtension);

            try {
                Files.deleteIfExists(saveLocation);
                Files.copy(picture.getInputStream(), saveLocation, StandardCopyOption.REPLACE_EXISTING);
                existingComment.setImageName(storageHash + fileExtension); // Met à jour l'image du commentaire existant
            } catch (IOException e) {
                logger.error("Error saving image", e);
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error saving image");
            }
        }

        // Mise à jour des informations du commentaire existant
        existingComment.setDescription(comment.getDescription());

        // Sauvegarde de l'entité Comment mise à jour (pas un DTO)
        repository.save(existingComment); // Met à jour directement l'entité dans la base de données

        // Convertir l'entité mise à jour en DTO pour la réponse
        CommentDto updatedCommentDto = mapper.toDto(existingComment);

        return updatedCommentDto;
    }

}
