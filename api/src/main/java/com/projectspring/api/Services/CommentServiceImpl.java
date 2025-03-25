package com.projectspring.api.services;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import com.projectspring.api.dtos.CommentDto;
import com.projectspring.api.mappers.CommentMapper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.projectspring.api.entities.Comment;
import com.projectspring.api.entities.Place;
import com.projectspring.api.entities.User;
import com.projectspring.api.generic.GenericServiceImpl;
import com.projectspring.api.repositories.CommentRepository;
import com.projectspring.api.repositories.PlaceRepository;
import com.projectspring.api.repositories.UserRepository;
import com.projectspring.api.services.Filestorage.FileStorageService;

import java.nio.file.Path;

@Service
public class CommentServiceImpl
        extends GenericServiceImpl<
        Comment,
        CommentDto,
        CommentRepository,
        CommentMapper> implements CommentService {

    /**
     * Attribut permettant d'utiliser le système de log "slf4j" (API de
     * journalisation Java)
     * Pour plus d'informations sur slf4j ->
     * https://www.baeldung.com/slf4j-with-log4j2-logback
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);

    private final PlaceRepository placeRepository;

    private final UserRepository userRepository;

    private final FileStorageService fileStorageService;
    Logger logger = LoggerFactory.getLogger(FileStorageService.class);

    public CommentServiceImpl(CommentRepository repository, CommentMapper mapper, PlaceRepository placeRepository,
            UserRepository userRepository, FileStorageService fileStorageService) {
        super(repository, mapper);
        this.placeRepository = placeRepository;
        this.userRepository = userRepository;
        this.fileStorageService = fileStorageService;
    }

    public CommentDto postCommentByPlace(CommentDto commentDto, Long placeId) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = (principal instanceof UserDetails userDetails) ? userDetails.getUsername()
                    : principal.toString();

            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

            Place place = placeRepository.findById(placeId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found"));

            String imageName = null;
            MultipartFile picture = commentDto.getPicture();
            if (picture != null && !picture.isEmpty()) {
                String storageHash = fileStorageService.getStorageHash(picture).get();
                Path rootLocation = fileStorageService.getRootLocation();
                String fileExtension = fileStorageService.mimeTypeToExtension(picture.getContentType());
                storageHash += fileExtension;
                Path saveLocation = rootLocation.resolve(storageHash);
                Files.deleteIfExists(saveLocation);
                Files.copy(picture.getInputStream(), saveLocation);
                imageName = storageHash;
            }

            Comment comment = new Comment();
            comment.setDescription(commentDto.getDescription());
            comment.setPlace(place);
            comment.setUser(user);
            comment.setImageName(imageName);

            Comment savedComment = repository.save(comment);
            return mapper.toDto(savedComment);
        } catch (IOException e) {
            LOGGER.error("Erreur lors de la sauvegarde de l'image", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erreur lors de la sauvegarde de l'image");
        }
    }

    public CommentDto updateComment(CommentDto commentDto, Long commentId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = (principal instanceof UserDetails userDetails) ? userDetails.getUsername()
                : principal.toString();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Comment existingComment = repository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found"));

        if (!existingComment.getUser().equals(user)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not authorized to update this comment");
        }

        String imageName = existingComment.getImageName();
        MultipartFile picture = commentDto.getPicture();
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
                imageName = storageHash + fileExtension;
            } catch (IOException e) {
                LOGGER.error("Error saving image", e);
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error saving image");
            }
        }

        existingComment.setDescription(commentDto.getDescription());
        existingComment.setImageName(imageName);
        repository.save(existingComment);
        return mapper.toDto(existingComment);
    }

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

    public List<Comment> getCommentsByPlace(Long placeId) {
        // Vérifier si le lieu existe avant de récupérer les commentaires
        if (!placeRepository.existsById(placeId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found");
        }

        return repository.findByPlaceId(placeId);
    }

    public List<Comment> getCommentsByUser() {
        Long userId = getAuthenticatedUserId();
        // Vérifier si l'utilisateur existe avant de récupérer les commentaires
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        return repository.findByUserId(userId);
    }

    public byte[] getImage(Long commentId) {
        Path rooLocation = this.fileStorageService.getRootLocation();
        Optional<Comment> comment = repository.findById(commentId);

        if (comment.isPresent()) {

            String imageName = comment.get().getImageName();
            Path imagePath = rooLocation.resolve(imageName);

            try {
                return Files.readAllBytes(imagePath);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
    }

    private Long getAuthenticatedUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = (principal instanceof UserDetails userDetails) ? userDetails.getUsername()
                : principal.toString();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable avec le nom: " + username));

        return user.getId();
    }
}
