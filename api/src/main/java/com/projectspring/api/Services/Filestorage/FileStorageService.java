package com.projectspring.api.Services.Filestorage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

/**
 * Classe service s'occupant du stockage de fichiers sur le disque.
 * 
 * Les classes "service" intègrent la logique métier (tout ne doit pas être dans
 * le controleur).
 * 
 * 
 * @param properties
 */
@Service
public class FileStorageService {

    /**
     * Attribut permettant d'utiliser le système de log "slf4j" (API de
     * journalisation Java)
     * Pour plus d'informations sur slf4j ->
     * https://www.baeldung.com/slf4j-with-log4j2-logback
     */
    Logger logger = LoggerFactory.getLogger(FileStorageService.class);

    private final Path rootLocation;

    /**
     * Constructeur de la classe prenant des propriétés en paramètre.
     * 
     * @param properties
     */
    public FileStorageService(FileStorageProperties properties) {
        this.rootLocation = Paths.get(properties.getPath());
    }

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);

            // logging d'information s'affichant dans la console en utilisant le formatage
            // d'une chaîne de caractères
            // pou plus d'informations sur le fonctionnement du formatage d'une chaîne en
            // utilisant la classe "Logger"
            // de slf4j veuillez vous référer à la ressource suivante :
            // https://examples.javacodegeeks.com/java-development/enterprise-java/slf4j/slf4j-format-string-example/
            logger.info("Chemin de sauvegarde des fichiers {}", rootLocation.toAbsolutePath().toString());
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    /**
     * Sauvegarde un fichier temporairement stocké sous la forme d'un objet de
     * "MultipartFile" sur le disque.
     * 
     * @param file Le fichier à stocker
     */
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Impossible de sauvegarder un fichier vide.");
            }
            Path destinationFile = this.rootLocation.resolve(Paths.get(file.getOriginalFilename())).normalize()
                    .toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new StorageException("Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                // copie du fichier en remplaçant l'existant
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Impossible de sauvegarder le fichier.", e);
        }
    }

    /***
     * Permet de charger tous les fichiers contenu dans le dossier racine.
     * 
     * @return
     */
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Impossible de lire tous les fichiers", e);
        }

    }

    /**
     * Chargement d'un fichier contenu dans le dossier "root"
     * 
     * @param filename Le nom du fichier
     * @return Chemin vers le fichier en question
     */
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageException("Impossible de lire le fichier : " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageException("Impossible de lire le fichier : " + filename, e);
        }
    }

    /**
     * Suppression récursive de tous les fichiers du dossier "root"
     */
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public Path getRootLocation() {
        return rootLocation;
    }

    /**
      * Retourne l'extension d'un fichier en fonction d'un type MIME
      * pour plus d'informations sur les types MIME :
      * https://developer.mozilla.org/fr/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Common_types
      */
     public String mimeTypeToExtension(String mimeType) {
         return switch (mimeType) {
             case "image/jpeg" -> ".jpeg";
             case "image/png" -> ".png";
             case "image/svg" -> ".svg";
             default -> "";
         };
     }

     /**
      * Permet de retrouver un hash qui pourra être utilisé comme nom de fichier
      * uniquement pour le stockage.
      *
      * Le hash sera calculé à partir du nom du fichier, de son type MIME
      * (https://developer.mozilla.org/fr/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Common_types)
      * et de la date d'upload.
      *
      * @return Le hash encodé en base64
      */
     public Optional<String> getStorageHash(MultipartFile file) {
         String hashString = null;

         if (!file.isEmpty()) {
             try {
                 MessageDigest messageDigest = MessageDigest.getInstance("MD5");

                 // La méthode digest de la classe "MessageDigest" prend en paramètre un byte[]
                 // il faut donc transformer les différents objets utilisés pour le hachage en
                 // tableau d'octets
                 // Nous utiliserons la classe "ByteArrayOutputStream" pour se faire
                 ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                 outputStream.write(file.getOriginalFilename().getBytes());
                 outputStream.write(file.getContentType().getBytes());
                 LocalDate date = LocalDate.now();
                 outputStream.write(date.toString().getBytes());

                 // calcul du hash, on obtient un tableau d'octets
                 byte[] hashBytes = messageDigest.digest(outputStream.toByteArray());

                 // on retrouve une chaîne de caractères à partir d'un tableau d'octets
                 hashString = String.format("%032x", new BigInteger(1, hashBytes));
             } catch (NoSuchAlgorithmException | IOException e) {
                 logger.error(e.getMessage());
             }
         }

         return Optional.ofNullable(hashString);
     }

}

