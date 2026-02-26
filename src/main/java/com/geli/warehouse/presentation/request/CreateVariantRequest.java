package com.geli.warehouse.presentation.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateVariantRequest(

                @NotBlank String category,

                @NotBlank String name,

                @NotNull Boolean isActive) {
}
