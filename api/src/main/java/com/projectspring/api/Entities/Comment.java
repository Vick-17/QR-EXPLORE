package com.projectspring.api.entities;

import com.projectspring.api.generic.BaseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Comment extends BaseEntity {

    @Column(nullable = false)
    private String description;

    /*
     Le champ picture est un fichier (image dans notre cas).
     Il est ignoré lors de la sérialisation en JSON (ce qui signifie qu'il ne
     sera pas inclus dans la réponse API JSON).
     */
    @JsonIgnore
    @Transient // Cette annotation indique que le champ ne sera pas persisté dans la base de données
    private MultipartFile picture;

    private String imageName;
    
    /*
    Relation entre le commentaire et le lieu
    Un commentaire est associé à un seul lieu
    */
    @ManyToOne
    @JoinColumn(name = "place_id") // place_id est la colonne dans la base de données qui relie les 2 table
    private Place place;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
