package com.projectspring.api.Dto;

import java.io.Serializable;

import com.projectspring.api.Entities.Place;
import com.projectspring.api.Generic.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QRCodeDto extends BaseDto implements Serializable {

    private String qrName;

    private String imageName;
    
    private Place place;
}
