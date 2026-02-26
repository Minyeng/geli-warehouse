package com.geli.warehouse.infrastructure.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geli.warehouse.infrastructure.persistence.jpa.entity.ItemJpaEntity;

public interface ItemJpaRepository extends JpaRepository<ItemJpaEntity, Long> {

}
