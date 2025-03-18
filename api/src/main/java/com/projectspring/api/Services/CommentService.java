package com.projectspring.api.Services;


import java.io.IOException;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    public CommentDto postCommentByPlace(CommentDto comment, Long placeId, Long userId) {
        try {
            // Vérifie que le lieu existe
            Place place = placeRepository.findById(placeId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found"));
            
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

            // Récupère l'image de CommentDto
            MultipartFile picture = comment.getPicture();
            if (picture != null && !picture.isEmpty()) {
                // Calcul du hash du fichier pour obtenir un nom unique
                String storageHash = fileStorageService.getStorageHash(picture).get();
                Path rootLocation = fileStorageService.getRootLocation();
                // Récupération de l'extension
                String fileExtension = fileStorageService.mimeTypeToExtension(picture.getContentType());
                // Ajout de l'extension au nom du fichier
                storageHash += fileExtension;
                // On retrouve le chemin de stockage de l'image
                Path saveLocation = rootLocation.resolve(storageHash);

                // Suppression du fichier si besoin
                Files.deleteIfExists(saveLocation);

                // Tentative de sauvegarde
                Files.copy(picture.getInputStream(), saveLocation);
                // À ce niveau il n'y a pas eu d'exception, on ajoute le nom utilisé pour
                // stocker l'image
                comment.setImageName(storageHash);
            }

            // On assigne le Place récupéré
            comment.setPlace(place);
            comment.setUser(user);

            return saveOrUpdate(comment);

        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        // Si on arrive ici, il y a eu une erreur
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la sauvegarde de l'image");
    } 
    
}
