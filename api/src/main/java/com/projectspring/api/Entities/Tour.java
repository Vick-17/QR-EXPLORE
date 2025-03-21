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
public class Tour extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private Boolean isVisible;

    @ManyToMany
    @JoinTable(name = "tour_places", joinColumns = @JoinColumn(name = "tour_id"), inverseJoinColumns = @JoinColumn(name = "place_id"))
    private List<Place> places;
}
