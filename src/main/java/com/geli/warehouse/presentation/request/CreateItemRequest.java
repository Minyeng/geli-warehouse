package com.geli.warehouse.presentation.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateItemRequest(
                @NotBlank String name,

                @NotBlank String description,

                @NotNull Boolean isActive) {
}
