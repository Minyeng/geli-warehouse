package com.geli.warehouse.infrastructure.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geli.warehouse.infrastructure.persistence.jpa.entity.VariantJpaEntity;

public interface VariantJpaRepository extends JpaRepository<VariantJpaEntity, Long> {

}
