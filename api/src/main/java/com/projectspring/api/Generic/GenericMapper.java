package com.projectspring.api.Generic;

public interface GenericMapper<E, D> {
    D toDto(E entity);

    E toEntity(D dto);
}
