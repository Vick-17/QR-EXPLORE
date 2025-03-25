package com.projectspring.api.entities;

import java.util.List;

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

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "placeType")
    private List<PlaceSubType> placeSubtypes;

    @OneToMany(mappedBy = "placeType")
    private List<Place> places;
}
