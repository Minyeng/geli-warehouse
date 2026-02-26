package com.geli.warehouse.domain.model;

import java.util.ArrayList;
import java.util.List;

import io.micrometer.common.lang.NonNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class ItemVariant {

    private final Long id;
    private String code;
    private Double price;
    private Integer stock;
    private Long itemId;


    public ItemVariant(@NonNull Long id, String code, Double price, Integer stock, Long itemId, List<ItemVariantMap> itemVariantMaps) {
        if (stock < 0)
            throw new IllegalArgumentException("Stock cannot be negative");
        this.id = id;
        this.code = code;
        this.price = price;
        this.stock = stock;
        this.itemId = itemId;
        this.itemVariantMaps = itemVariantMaps;
    }
    
    @Builder.Default
    private final List<ItemVariantMap> itemVariantMaps = new ArrayList<>();
}
