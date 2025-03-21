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


}
