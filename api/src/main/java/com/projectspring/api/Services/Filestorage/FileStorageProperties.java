package com.projectspring.api.services.Filestorage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuration pour la sauvegarde de fichiers.
 * 
 * L'annotation "@ConfigurationProperties" permet de récupérer automatiquement les
 * valeurs des propriétés contenues dans le fichier "application.properties"
 */
@Configuration
@ConfigurationProperties(prefix = "filestorage")
public class FileStorageProperties {

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    
}
