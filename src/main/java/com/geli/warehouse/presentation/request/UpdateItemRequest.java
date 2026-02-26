package com.geli.warehouse.presentation.request;

public record UpdateItemRequest(
                String name,
                String description,
                Boolean isActive) {
}
