package com.geli.warehouse.infrastructure.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geli.warehouse.infrastructure.persistence.jpa.entity.ItemVariantMapJpaEntity;

public interface ItemVariantMapJpaRepository extends JpaRepository<ItemVariantMapJpaEntity, Long> {

}
