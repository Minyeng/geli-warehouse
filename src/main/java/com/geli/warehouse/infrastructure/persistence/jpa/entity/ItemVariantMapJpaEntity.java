package com.geli.warehouse.infrastructure.persistence.jpa.entity;

import java.time.OffsetDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "item_variant_maps")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemVariantMapJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name = "item_variant_id", nullable = false)
    private ItemVariantJpaEntity itemVariantEntity;
    
    @ManyToOne
    @JoinColumn(name = "variant_id", nullable = false)
    private VariantJpaEntity variantEntity;
}