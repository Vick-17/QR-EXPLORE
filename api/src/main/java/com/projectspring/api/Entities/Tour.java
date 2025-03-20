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

    @OneToMany
    private List<Place> places;
}
