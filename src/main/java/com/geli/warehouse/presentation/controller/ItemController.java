package com.geli.warehouse.presentation.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geli.warehouse.application.service.ItemService;
import com.geli.warehouse.domain.model.Item;
import com.geli.warehouse.presentation.request.CreateItemRequest;
import com.geli.warehouse.presentation.request.UpdateItemRequest;
import com.geli.warehouse.presentation.response.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController {

        private final ItemService itemService;

        @PostMapping
        public ResponseEntity<ApiResponse<Item>> create(
                        @Valid @RequestBody CreateItemRequest request) {

                Item item = itemService.create(request);

                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(ApiResponse.success(
                                                HttpStatus.CREATED.value(),
                                                "Item created successfully",
                                                item));
        }

        @GetMapping("/{id}")
        public ResponseEntity<ApiResponse<Item>> getById(
                        @PathVariable Long id) {

                Item item = itemService.getById(id);

                return ResponseEntity.ok(
                                ApiResponse.success(200, "Success", item));
        }

        @GetMapping
        public ResponseEntity<ApiResponse<Page<Item>>> list(
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size) {

                Page<Item> items = itemService.getAll(
                                PageRequest.of(page, size));

                return ResponseEntity.ok(
                                ApiResponse.success(200, "Success", items));
        }

        @PutMapping("/{id}")
        public ResponseEntity<ApiResponse<Item>> update(
                        @PathVariable Long id,
                        @Valid @RequestBody UpdateItemRequest request) {

                Item updated = itemService.update(
                                id,
                                request);

                return ResponseEntity.ok(
                                ApiResponse.success(200, "Item updated", updated));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse<Void>> delete(
                        @PathVariable Long id) {

                itemService.delete(id);

                return ResponseEntity.ok(
                                ApiResponse.success(200, "Item deleted", null));
        }
}
