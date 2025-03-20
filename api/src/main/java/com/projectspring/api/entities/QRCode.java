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
@Table(name = "qr_code")
public class QRCode extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "place_id")
    private Place place;
}
