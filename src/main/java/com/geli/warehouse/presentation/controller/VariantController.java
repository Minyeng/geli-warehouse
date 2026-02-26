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

import com.geli.warehouse.application.service.VariantService;
import com.geli.warehouse.domain.model.Variant;
import com.geli.warehouse.presentation.request.CreateVariantRequest;
import com.geli.warehouse.presentation.request.UpdateVariantRequest;
import com.geli.warehouse.presentation.response.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/variants")
@RequiredArgsConstructor
public class VariantController {

        private final VariantService itemService;

        @PostMapping
        public ResponseEntity<ApiResponse<Variant>> create(
                        @Valid @RequestBody CreateVariantRequest request) {

                Variant item = itemService.create(request);

                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(ApiResponse.success(
                                                HttpStatus.CREATED.value(),
                                                "Variant created successfully",
                                                item));
        }

        @GetMapping("/{id}")
        public ResponseEntity<ApiResponse<Variant>> getById(
                        @PathVariable Long id) {

                Variant item = itemService.getById(id);

                return ResponseEntity.ok(
                                ApiResponse.success(200, "Success", item));
        }

        @GetMapping
        public ResponseEntity<ApiResponse<Page<Variant>>> list(
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size) {

                Page<Variant> items = itemService.getAll(
                                PageRequest.of(page, size));

                return ResponseEntity.ok(
                                ApiResponse.success(200, "Success", items));
        }

        @PutMapping("/{id}")
        public ResponseEntity<ApiResponse<Variant>> update(
                        @PathVariable Long id,
                        @Valid @RequestBody UpdateVariantRequest request) {

                Variant updated = itemService.update(
                                id,
                                request);

                return ResponseEntity.ok(
                                ApiResponse.success(200, "Variant updated", updated));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse<Void>> delete(
                        @PathVariable Long id) {

                itemService.delete(id);

                return ResponseEntity.ok(
                                ApiResponse.success(200, "Variant deleted", null));
        }
}
