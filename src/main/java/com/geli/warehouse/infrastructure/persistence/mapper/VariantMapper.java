package com.geli.warehouse.infrastructure.persistence.mapper;

import com.geli.warehouse.domain.model.Variant;
import com.geli.warehouse.infrastructure.persistence.jpa.entity.VariantJpaEntity;

public class VariantMapper {

    public static Variant toDomain(VariantJpaEntity entity) {
        return Variant.builder()
                .id(entity.getId())
                .category(entity.getCategory())
                .name(entity.getName())
                .isActive(entity.getIsActive())
                .build();
    }

    public static VariantJpaEntity toEntity(Variant domain) {
        return VariantJpaEntity.builder()
                .id(domain.getId())
                .category(domain.getCategory())
                .name(domain.getName())
                .isActive(domain.getIsActive())
                .build();
    }
}
