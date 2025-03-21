package com.projectspring.api.entities;


import com.projectspring.api.generic.BaseEntity;
import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Place extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String description;
    @Column(nullable = false)
    private String location;

    @JsonIgnore
    @Transient
    private MultipartFile picture;

    @OneToOne(mappedBy = "place")
    private QRCode qrCode;

    @ManyToOne
    @JoinColumn(name = "place_type_id")
    private PlaceType placeType;

}
