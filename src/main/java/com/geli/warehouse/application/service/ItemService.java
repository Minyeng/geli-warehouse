package com.geli.warehouse.application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.geli.warehouse.domain.model.Item;
import com.geli.warehouse.domain.repository.ItemRepository;
import com.geli.warehouse.presentation.request.CreateItemRequest;
import com.geli.warehouse.presentation.request.UpdateItemRequest;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Page<Item> getAll(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    public Item getById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    @Transactional
    public Item create(CreateItemRequest request) {
        Item item = Item.builder()
                .name(request.name())
                .description(request.name())
                .isActive(true)
                // .updatedAt(OffsetDateTime.now())
                .build();
        return itemRepository.save(item);
    }

    @Transactional
    public Item update(Long id, UpdateItemRequest request) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        Item newItem = item.toBuilder()
                .name(request.name())
                .description(request.description())
                .isActive(request.isActive())
                .build();

        return itemRepository.save(newItem);
    }

    @Transactional
    public void delete(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        itemRepository.delete(item);
    }
}
