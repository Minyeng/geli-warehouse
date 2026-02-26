package com.geli.warehouse.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.geli.warehouse.domain.model.ItemVariant;
import com.geli.warehouse.domain.model.ItemVariantMap;
import com.geli.warehouse.domain.repository.ItemRepository;
import com.geli.warehouse.domain.repository.ItemVariantMapRepository;
import com.geli.warehouse.domain.repository.ItemVariantRepository;
import com.geli.warehouse.domain.repository.VariantRepository;
import com.geli.warehouse.presentation.request.CreateItemVariantRequest;
import com.geli.warehouse.presentation.request.UpdateItemVariantStockRequest;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ItemVariantService {

    private final ItemRepository itemRepository;
    private final ItemVariantRepository itemVariantRepository;
    private final ItemVariantMapRepository itemVariantMapRepository;
    private final VariantRepository variantRepository;

    public Page<ItemVariant> getAll(List<Long> items, List<Long> variants, Pageable pageable) {
        return itemVariantRepository.findWithFilter(items, variants, pageable);
    }

    public Page<ItemVariant> getAllByItemId(Long itemId, Pageable pageable) {
        return itemVariantRepository.findAllByItemId(itemId, pageable);
    }

    public ItemVariant getById(Long id) {
        return itemVariantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item variant not found"));
    }

    @Transactional
    public ItemVariant create(Long itemId, CreateItemVariantRequest request) {
        itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        ItemVariant itemVariant = ItemVariant.builder()
                .code(generateCode(itemId, request.variants()))
                .price(request.price())
                .stock(request.stock())
                .itemId(itemId)
                .build();

        ItemVariant savedItemVariant = itemVariantRepository.save(itemVariant);

        for (Long variantId : request.variants()) {
            ItemVariantMap itemVariantMap = ItemVariantMap.builder()
                    .itemVariantId(savedItemVariant.getId())
                    .variant(variantRepository.findById(variantId)
                            .orElseThrow(() -> new RuntimeException("Variant not found")))
                    .build();

            itemVariantMapRepository.save(itemVariantMap);
        }

        return savedItemVariant;
    }

    @Transactional
    public ItemVariant updateStock(Long id, UpdateItemVariantStockRequest request) {
        ItemVariant itemVariant = itemVariantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item Variant not found"));

        ItemVariant newItemVariant = itemVariant.toBuilder()
                .stock(itemVariant.getStock() - request.amount())
                .build();

        return itemVariantRepository.save(newItemVariant);
    }

    @Transactional
    public void delete(Long id) {
        ItemVariant itemVariant = itemVariantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item Variant not found"));

        itemVariantRepository.delete(itemVariant);
    }

    private String generateCode(Long itemId, List<Long> variants) {
        return itemId + "-" + variants.stream()
                .map(String::valueOf)
                .collect(Collectors.joining("-"));
    }
}
