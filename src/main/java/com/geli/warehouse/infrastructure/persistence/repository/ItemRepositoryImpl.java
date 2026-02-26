package com.geli.warehouse.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.geli.warehouse.domain.model.Item;
import com.geli.warehouse.domain.repository.ItemRepository;
import com.geli.warehouse.infrastructure.persistence.jpa.entity.ItemJpaEntity;
import com.geli.warehouse.infrastructure.persistence.jpa.repository.ItemJpaRepository;
import com.geli.warehouse.infrastructure.persistence.mapper.ItemMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {

    private final ItemJpaRepository jpaRepository;

    @Override
    public Item save(Item item) {
        ItemJpaEntity entity = ItemMapper.toEntity(item);
        return ItemMapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<Item> findById(Long id) {
        return jpaRepository.findById(id)
                .map(ItemMapper::toDomain);
    }

    @Override
    public Page<Item> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable)
                .map(ItemMapper::toDomain);
    }

    @Override
    public void delete(Item item) {
        jpaRepository.deleteById(item.getId());
    }
}
