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
@Table(name = "place_subtype")
public class PlaceSubType extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "place_type_id")
    private PlaceType placeType;
}
