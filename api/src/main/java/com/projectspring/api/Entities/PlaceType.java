package com.projectspring.api.entities;

import com.projectspring.api.generic.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "place_type")
public class PlaceType extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

<<<<<<< HEAD:api/src/main/java/com/projectspring/api/Entities/PlaceType.java
    @OneToMany(mappedBy = "placeType")
    private List<PlaceSubType> placeSubtypes;
=======
>>>>>>> origin/feat-CREATE-QRCODE:api/src/main/java/com/projectspring/api/entities/PlaceType.java

}
