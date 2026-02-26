package com.geli.warehouse.infrastructure.persistence.mapper;

import java.util.ArrayList;
import java.util.List;

import com.geli.warehouse.domain.model.Item;
import com.geli.warehouse.domain.model.ItemVariant;
import com.geli.warehouse.domain.model.ItemVariantMap;
import com.geli.warehouse.infrastructure.persistence.jpa.entity.ItemVariantJpaEntity;
import com.geli.warehouse.infrastructure.persistence.jpa.entity.ItemVariantMapJpaEntity;

public class ItemVariantMapper {

    public static ItemVariant toDomain(ItemVariantJpaEntity entity) {
        ItemVariant.ItemVariantBuilder builder = ItemVariant.builder()
                .id(entity.getId())
                .itemId(entity.getItemEntity().getId())
                .code(entity.getCode())
                .price(entity.getPrice())
                .stock(entity.getStock());

        if (entity.getItemVariantMaps() != null) {
            List<ItemVariantMap> itemVariantMaps = new ArrayList<>();
            for (ItemVariantMapJpaEntity itemVariantMapJpaEntity : entity.getItemVariantMaps()) {
                itemVariantMaps.add(ItemVariantMapMapper.toDomain(itemVariantMapJpaEntity));
            }
            builder.itemVariantMaps(itemVariantMaps);
        }

        return builder.build();
    }

    public static ItemVariantJpaEntity toEntity(ItemVariant domain) {
        Item item = Item.builder()
                .id(domain.getItemId())
                .build();

        ItemVariantJpaEntity.ItemVariantJpaEntityBuilder builder = ItemVariantJpaEntity.builder()
                .id(domain.getId())
                .itemEntity(ItemMapper.toEntity(item))
                .code(domain.getCode())
                .price(domain.getPrice())
                .stock(domain.getStock());

        if (domain.getItemVariantMaps() != null) {
            List<ItemVariantMapJpaEntity> itemVariantMaps = new ArrayList<>();
            for (ItemVariantMap itemVariantMap : domain.getItemVariantMaps()) {
                itemVariantMaps.add(ItemVariantMapMapper.toEntity(itemVariantMap));
            }
            builder.itemVariantMaps(itemVariantMaps);
        }

        return builder.build();
    }
}
