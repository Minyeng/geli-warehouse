package com.geli.warehouse.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.geli.warehouse.domain.model.ItemVariant;

public interface ItemVariantRepository {
    ItemVariant save(ItemVariant variant);

    Optional<ItemVariant> findById(Long id);

    Optional<ItemVariant> findByCode(String code);

    Page<ItemVariant> findAll(Pageable pageable);

    Page<ItemVariant> findWithFilter(List<Long> items, List<Long> variants, Pageable pageable);

    Page<ItemVariant> findAllByItemId(Long itemId, Pageable pageable);

    void delete(ItemVariant itemVariant);
}
