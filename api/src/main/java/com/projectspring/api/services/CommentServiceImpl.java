package com.projectspring.api.services;


import java.io.IOException;
import java.nio.file.Files;

import com.projectspring.api.dtos.CommentDto;
import com.projectspring.api.mappers.CommentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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

    public CommentServiceImpl(CommentRepository repository, CommentMapper mapper, PlaceRepository placeRepository, UserRepository userRepository, FileStorageService fileStorageService) {
        super(repository, mapper);
        this.placeRepository = placeRepository;
        this.userRepository = userRepository;
        this.fileStorageService = fileStorageService;
    }

    //TODO: erreurs détectées dans cette méthode (à corriger)
//    public CommentDto postCommentByPlace(CommentDto comment, Long placeId, Long userId) {
//        try {
//            // Vérifie que le lieu existe
//            Place place = placeRepository.findById(placeId)
//                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found"));
//
//            User user = userRepository.findById(userId)
//                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
//
//            // Récupère l'image de CommentDto
//            MultipartFile picture = comment.getPicture();
//            if (picture != null && !picture.isEmpty()) {
//                // Calcul du hash du fichier pour obtenir un nom unique
//                String storageHash = fileStorageService.getStorageHash(picture).get();
//                Path rootLocation = fileStorageService.getRootLocation();
//                // Récupération de l'extension
//                String fileExtension = fileStorageService.mimeTypeToExtension(picture.getContentType());
//                // Ajout de l'extension au nom du fichier
//                storageHash += fileExtension;
//                // On retrouve le chemin de stockage de l'image
//                Path saveLocation = rootLocation.resolve(storageHash);
//
//                // Suppression du fichier si besoin
//                Files.deleteIfExists(saveLocation);
//
//                // Tentative de sauvegarde
//                Files.copy(picture.getInputStream(), saveLocation);
//                // À ce niveau il n'y a pas eu d'exception, on ajoute le nom utilisé pour
//                // stocker l'image
//                comment.setImageName(storageHash);
//            }
//
//            // On assigne le lieu récupéré
//            comment.setPlace(place);
//            comment.setUser(user);
//
//            return saveOrUpdate(comment);
//
//        } catch (IOException e) {
//            LOGGER.error(e.getMessage());
//        }
//
//        // Si on arrive ici, il y a eu une erreur
//        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la sauvegarde de l'image");
//    }
    
}
