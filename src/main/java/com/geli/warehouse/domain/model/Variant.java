package com.geli.warehouse.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class Variant {

    private final Long id;
    private String category;
    private String name;
    private Boolean isActive;
}
