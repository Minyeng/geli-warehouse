package com.geli.warehouse.domain.repository;

import com.geli.warehouse.domain.model.ItemVariantMap;

public interface ItemVariantMapRepository {
    void save(ItemVariantMap itemVariantMap);

    void delete(ItemVariantMap itemVariantMap);
}
