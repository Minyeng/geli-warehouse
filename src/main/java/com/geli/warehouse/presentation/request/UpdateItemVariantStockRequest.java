package com.geli.warehouse.presentation.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateItemVariantStockRequest(
                @NotNull @Min(1) Integer amount) {
}
