package com.geli.warehouse.application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.geli.warehouse.domain.model.Variant;
import com.geli.warehouse.domain.repository.VariantRepository;
import com.geli.warehouse.presentation.request.CreateVariantRequest;
import com.geli.warehouse.presentation.request.UpdateVariantRequest;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VariantService {

    private final VariantRepository itemRepository;

    public Page<Variant> getAll(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    public Variant getById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Variant not found"));
    }

    @Transactional
    public Variant create(CreateVariantRequest request) {
        Variant item = Variant.builder()
                .category(request.category())
                .name(request.name())
                .isActive(true)
                .build();
        return itemRepository.save(item);
    }

    @Transactional
    public Variant update(Long id, UpdateVariantRequest request) {
        Variant item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Variant not found"));

        Variant newVariant = item.toBuilder()
                .category(request.category())
                .name(request.name())
                .isActive(request.isActive())
                .build();

        return itemRepository.save(newVariant);
    }

    @Transactional
    public void delete(Long id) {
        Variant item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Variant not found"));

        itemRepository.delete(item);
    }
}
