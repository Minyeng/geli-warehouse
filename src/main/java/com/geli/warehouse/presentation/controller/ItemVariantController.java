package com.geli.warehouse.presentation.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geli.warehouse.application.service.ItemVariantService;
import com.geli.warehouse.domain.model.ItemVariant;
import com.geli.warehouse.presentation.request.CreateItemVariantRequest;
import com.geli.warehouse.presentation.request.UpdateItemVariantStockRequest;
import com.geli.warehouse.presentation.response.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/item-variants")
public class ItemVariantController {

        private final ItemVariantService itemVariantService;

        @PostMapping("/{itemId}")
        public ResponseEntity<ApiResponse<ItemVariant>> createItemVariant(
                        @PathVariable Long itemId,
                        @Valid @RequestBody CreateItemVariantRequest request) {

                ItemVariant variant = itemVariantService.create(itemId, request);

                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(ApiResponse.success(
                                                201,
                                                "Item Variant created",
                                                variant));
        }

        @GetMapping("/{itemId}")
        public ResponseEntity<ApiResponse<Page<ItemVariant>>> list(
                        @PathVariable Long itemId,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size) {

                Page<ItemVariant> items = itemVariantService.getAllByItemId(
                                itemId,
                                PageRequest.of(page, size));

                return ResponseEntity.ok(
                                ApiResponse.success(200, "Success", items));
        }

        @PatchMapping("/{id}/stock")
        public ResponseEntity<ApiResponse<ItemVariant>> updateStock(
                        @PathVariable Long id,
                        @Valid @RequestBody UpdateItemVariantStockRequest request) {

                ItemVariant updated = itemVariantService.updateStock(
                                id,
                                request);

                return ResponseEntity.ok(
                                ApiResponse.success(200, "Stock updated", updated));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse<Void>> delete(
                        @PathVariable Long id) {

                itemVariantService.delete(id);

                return ResponseEntity.ok(
                                ApiResponse.success(200, "Item Variant deleted", null));
        }

        @GetMapping
        public ResponseEntity<ApiResponse<Page<ItemVariant>>> listWithFilter(
                        @RequestParam(required = false) List<Long> items,
                        @RequestParam(required = false) List<Long> variants,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size) {

                Page<ItemVariant> data = itemVariantService.getAll(
                                items,
                                variants,
                                PageRequest.of(page, size));

                return ResponseEntity.ok(
                                ApiResponse.success(200, "Success", data));
        }
}
