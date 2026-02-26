package com.geli.warehouse.infrastructure.persistence.mapper;

import java.util.ArrayList;
import java.util.List;

import com.geli.warehouse.domain.model.Item;
import com.geli.warehouse.domain.model.ItemVariant;
import com.geli.warehouse.infrastructure.persistence.jpa.entity.ItemJpaEntity;
import com.geli.warehouse.infrastructure.persistence.jpa.entity.ItemVariantJpaEntity;

public class ItemMapper {

    public static Item toDomain(ItemJpaEntity entity) {
        Item.ItemBuilder builder = Item.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .isActive(entity.getIsActive());

        if (entity.getItemVariants() != null) {
            List<ItemVariant> itemVariants = new ArrayList<>();
            for (ItemVariantJpaEntity variantEntity : entity.getItemVariants()) {
                itemVariants.add(ItemVariantMapper.toDomain(variantEntity));
            }
            builder.itemVariants(itemVariants);
        }

        return builder.build();
    }

    public static Item toDomainItem(ItemJpaEntity entity) {
        Item.ItemBuilder builder = Item.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .isActive(entity.getIsActive());

        return builder.build();
    }

    public static ItemJpaEntity toEntity(Item domain) {
        ItemJpaEntity.ItemJpaEntityBuilder builder = ItemJpaEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .description(domain.getDescription())
                .isActive(domain.getIsActive());

        if (domain.getItemVariants() != null) {
            List<ItemVariantJpaEntity> itemVariants = new ArrayList<>();
            for (ItemVariant itemVariant : domain.getItemVariants()) {
                itemVariants.add(ItemVariantMapper.toEntity(itemVariant));
            }
            builder.itemVariants(itemVariants);
        }

        return builder.build();
    }
}
