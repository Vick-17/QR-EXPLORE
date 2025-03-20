package com.projectspring.api.entities;

import com.projectspring.api.generic.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class Role extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;
}
