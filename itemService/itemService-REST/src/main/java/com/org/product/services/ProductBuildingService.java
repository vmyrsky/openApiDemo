package com.org.product.services;

import org.springframework.stereotype.Service;

import com.org.product.entity.ItemDO;
import com.org.product.entity.ProductDO;

@Service
public class ProductBuildingService {

    public ProductBuildingService() {
    }

    /**
     * Creates a product composed of the two items provided.
     * @param item1 An instance of item to use in product.
     * @param Item2 An instance of item to use in product.
     */
    public ProductDO composeProductFromItems(ItemDO item1, ItemDO item2) {
        // Should be in a validator
        if (item1 == null || item2 == null || item1.equals(item2)) {
            throw new IllegalArgumentException("Parameters must be non null and non equal.");
        }
        var product = new ProductDO(-1L);
        product.addItem(item1);
        product.addItem(item2);
        return product;
    }
}
