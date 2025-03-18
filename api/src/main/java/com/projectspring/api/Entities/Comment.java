package com.projectspring.api.Entities;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "comment")
public class Comment {

    // clé primaire
    @Id
   // auto increment d'un valeur unique
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    /*
     Le champ picture est un fichier (image dans notre cas).
     Il est ignoré lors de la sérialisation en JSON (ce qui signifie qu'il ne
     sera pas inclus dans la réponse API JSON).
     */
    @JsonIgnore
    @Transient // Cette annotaion indique que le champ ne sera pas persisté dans la base de données
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
