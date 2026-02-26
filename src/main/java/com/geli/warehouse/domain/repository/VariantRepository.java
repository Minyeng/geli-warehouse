package com.geli.warehouse.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.geli.warehouse.domain.model.Variant;

public interface VariantRepository {
    Variant save(Variant variant);

    Optional<Variant> findById(Long id);

    Page<Variant> findAll(Pageable pageable);

    void delete(Variant variant);
}
