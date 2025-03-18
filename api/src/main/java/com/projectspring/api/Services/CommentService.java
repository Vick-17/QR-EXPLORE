package com.projectspring.api.Services;


import java.io.IOException;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import java.nio.file.Path;
import java.util.Optional;

@Service
public class CommentService extends GenericServiceImpl<Comment, Long, CommentDto, CommentRepository, CommentMapper> implements GenericService<CommentDto, Long> {

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

public CommentDto postCommentByPlace(CommentDto comment, Long placeId) {
    try {
        // Récupérer l'utilisateur connecté depuis le JWT
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        } else {
            username = principal.toString();
        }

        // Vérifie que l'utilisateur existe bien
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUsername(username));
        User user = optionalUser.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // Vérifie que le lieu existe
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found"));

        // Gestion de l'image
        MultipartFile picture = comment.getPicture();
        if (picture != null && !picture.isEmpty()) {
            // Hash du fichier pour éviter les doublons et garantir un stockage sécurisé
            String storageHash = fileStorageService.getStorageHash(picture).get();
            Path rootLocation = fileStorageService.getRootLocation();
            String fileExtension = fileStorageService.mimeTypeToExtension(picture.getContentType());
            storageHash += fileExtension;
            Path saveLocation = rootLocation.resolve(storageHash);

            // Supprimer l'ancienne image si besoin
            Files.deleteIfExists(saveLocation);

            // Sauvegarde sécurisée du fichier
            Files.copy(picture.getInputStream(), saveLocation);

            // Associer l'image au commentaire
            comment.setImageName(storageHash);
        }

        // Assigner l'utilisateur et le lieu au commentaire
        comment.setPlace(place);
        comment.setUser(user);

        return saveOrUpdate(comment);

    } catch (IOException e) {
        logger.error("Erreur lors de la sauvegarde de l'image", e);
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la sauvegarde de l'image");
    }
}



    public void deleteComment(Long commentId) {
    Comment comment = repository.findById(commentId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found"));

    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username;

    if (principal instanceof UserDetails userDetails) {
        username = userDetails.getUsername();
    } else {
        username = principal.toString();
    }

    // Vérification des permissions
    boolean isOwner = comment.getUser().getUsername().equals(username);
    boolean isModerator = comment.getUser().getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_MODO"));

    if (!isOwner && !isModerator) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to delete this comment");
    }

    repository.deleteById(commentId);
}
    
}
