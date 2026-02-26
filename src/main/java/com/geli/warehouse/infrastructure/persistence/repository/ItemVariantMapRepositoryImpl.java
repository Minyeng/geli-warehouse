package com.geli.warehouse.infrastructure.persistence.repository;

import org.springframework.stereotype.Repository;

import com.geli.warehouse.domain.model.ItemVariantMap;
import com.geli.warehouse.domain.repository.ItemVariantMapRepository;
import com.geli.warehouse.infrastructure.persistence.jpa.entity.ItemVariantMapJpaEntity;
import com.geli.warehouse.infrastructure.persistence.jpa.repository.ItemVariantMapJpaRepository;
import com.geli.warehouse.infrastructure.persistence.mapper.ItemVariantMapMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemVariantMapRepositoryImpl implements ItemVariantMapRepository {

    private final ItemVariantMapJpaRepository jpaRepository;

    @Override
    public void save(ItemVariantMap itemVariantMap) {
        ItemVariantMapJpaEntity entity = ItemVariantMapMapper.toEntity(itemVariantMap);
        jpaRepository.save(entity);
    }

    @Override
    public void delete(ItemVariantMap item) {
        jpaRepository.deleteById(item.getId());
    }
}
