package com.geli.warehouse.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class ItemVariantMap {

    private final Long id;
    private final Long itemVariantId;
    private final Long variantId;
    private final Variant variant;
}
