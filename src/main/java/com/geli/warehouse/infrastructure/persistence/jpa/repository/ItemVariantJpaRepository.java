package com.geli.warehouse.infrastructure.persistence.jpa.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.geli.warehouse.infrastructure.persistence.jpa.entity.ItemVariantJpaEntity;

public interface ItemVariantJpaRepository extends JpaRepository<ItemVariantJpaEntity, Long> {

    @Query(value = """
             SELECT * FROM item_variants v
             WHERE (
                 '{}'::bigint[] = (COALESCE(:items, '{}'::bigint[])) OR
                 v.item_id IN (:items)
             ) AND (
                 '{}'::bigint[] = (COALESCE(:variants, '{}'::bigint[])) OR
                 EXISTS (
                     SELECT 1 FROM item_variant_maps w
                     WHERE w.item_variant_id = v.id AND w.variant_id IN (:variants)
                 )
             )
            """, countQuery = """
             SELECT count(1) FROM item_variants v
             WHERE (
                 '{}'::bigint[] = (COALESCE(:items, '{}'::bigint[])) OR
                 v.item_id IN (:items)
             ) AND (
                 '{}'::bigint[] = (COALESCE(:variants, '{}'::bigint[])) OR
                 EXISTS (
                     SELECT 1 FROM item_variant_maps w
                     WHERE w.item_variant_id = v.id AND w.variant_id IN (:variants)
                 )
             )
            """, nativeQuery = true)
    Page<ItemVariantJpaEntity> findNative(
            @Param("items") Long[] items,
            @Param("variants") Long[] variants,
            Pageable pageable);

    Optional<ItemVariantJpaEntity> findByCode(String code);

    Page<ItemVariantJpaEntity> findByItemEntity_Id(Long itemId, Pageable pageable);
}
