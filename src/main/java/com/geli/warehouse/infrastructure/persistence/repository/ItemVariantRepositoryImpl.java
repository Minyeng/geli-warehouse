package com.geli.warehouse.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.geli.warehouse.domain.model.ItemVariant;
import com.geli.warehouse.domain.repository.ItemVariantRepository;
import com.geli.warehouse.infrastructure.persistence.jpa.repository.ItemVariantJpaRepository;
import com.geli.warehouse.infrastructure.persistence.mapper.ItemVariantMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemVariantRepositoryImpl implements ItemVariantRepository {

    private final ItemVariantJpaRepository jpaRepository;

    @Override
    public ItemVariant save(ItemVariant variant) {
        return ItemVariantMapper.toDomain(jpaRepository.save(ItemVariantMapper.toEntity(variant)));
    }

    @Override
    public Optional<ItemVariant> findById(Long id) {
        return jpaRepository.findById(id)
                .map(ItemVariantMapper::toDomain);
    }

    @Override
    public Optional<ItemVariant> findByCode(String code) {
        return jpaRepository.findByCode(code)
                .map(ItemVariantMapper::toDomain);
    }

    @Override
    public Page<ItemVariant> findAllByItemId(Long itemId, Pageable pageable) {
        return jpaRepository.findByItemEntity_Id(itemId, pageable)
                .map(ItemVariantMapper::toDomain);
    }

    @Override
    public void delete(ItemVariant itemVariant) {
        jpaRepository.deleteById(itemVariant.getId());
    }

    @Override
    public Page<ItemVariant> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable)
                .map(ItemVariantMapper::toDomain);
    }

    @Override
    public Page<ItemVariant> findWithFilter(List<Long> items, List<Long> variants, Pageable pageable) {
        return jpaRepository.findNative(
                items == null ? null : items.toArray(new Long[0]),
                variants == null ? null : variants.toArray(new Long[0]),
                pageable)
                .map(ItemVariantMapper::toDomain);
    }
}
