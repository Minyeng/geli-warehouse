package com.geli.warehouse.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.geli.warehouse.domain.model.Item;

public interface ItemRepository {
    Item save(Item item);

    Optional<Item> findById(Long id);

    Page<Item> findAll(Pageable pageable);

    void delete(Item item);
}
