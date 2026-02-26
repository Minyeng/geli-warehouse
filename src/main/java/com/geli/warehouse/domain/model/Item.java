package com.geli.warehouse.domain.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class Item {

    private final Long id;
    private String name;
    private String description;
    private Boolean isActive;

    @Builder.Default
    private final List<ItemVariant> itemVariants = new ArrayList<>();
}
