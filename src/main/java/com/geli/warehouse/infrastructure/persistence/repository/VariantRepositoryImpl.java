package com.geli.warehouse.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.geli.warehouse.domain.model.Variant;
import com.geli.warehouse.domain.repository.VariantRepository;
import com.geli.warehouse.infrastructure.persistence.jpa.entity.VariantJpaEntity;
import com.geli.warehouse.infrastructure.persistence.jpa.repository.VariantJpaRepository;
import com.geli.warehouse.infrastructure.persistence.mapper.VariantMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class VariantRepositoryImpl implements VariantRepository {

    private final VariantJpaRepository jpaRepository;

    @Override
    public Variant save(Variant item) {
        VariantJpaEntity entity = VariantMapper.toEntity(item);
        return VariantMapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<Variant> findById(Long id) {
        return jpaRepository.findById(id)
                .map(VariantMapper::toDomain);
    }

    @Override
    public Page<Variant> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable)
                .map(VariantMapper::toDomain);
    }

    @Override
    public void delete(Variant item) {
        jpaRepository.deleteById(item.getId());
    }
}
