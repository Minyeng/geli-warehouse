package com.geli.warehouse.infrastructure.persistence.mapper;

import com.geli.warehouse.domain.model.ItemVariantMap;
import com.geli.warehouse.infrastructure.persistence.jpa.entity.ItemVariantJpaEntity;
import com.geli.warehouse.infrastructure.persistence.jpa.entity.ItemVariantMapJpaEntity;

public class ItemVariantMapMapper {

    public static ItemVariantMap toDomain(ItemVariantMapJpaEntity entity) {
        return ItemVariantMap.builder()
                .id(entity.getId())
                .itemVariantId(entity.getItemVariantEntity().getId())
                .variant(VariantMapper.toDomain((entity.getVariantEntity())))
                .build();
    }

    public static ItemVariantMapJpaEntity toEntity(ItemVariantMap domain) {
        ItemVariantJpaEntity itemVariant = ItemVariantJpaEntity.builder()
                .id(domain.getItemVariantId())
                .build();

        return ItemVariantMapJpaEntity.builder()
                .id(domain.getId())
                .itemVariantEntity(itemVariant)
                .variantEntity(VariantMapper.toEntity(domain.getVariant()))
                .build();
    }
}
