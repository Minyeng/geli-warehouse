package com.geli.warehouse.presentation.request;

import java.util.List;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateItemVariantRequest(

        @NotEmpty List<@NotNull Long> variants,

        @NotNull @DecimalMin(value = "0.01") Double price,

        @NotNull @Min(1) Integer stock) {
}
