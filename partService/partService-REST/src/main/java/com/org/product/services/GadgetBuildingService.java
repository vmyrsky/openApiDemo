package com.org.product.services;

import org.springframework.stereotype.Service;

import com.org.product.entity.PartDO;
import com.org.product.entity.GadgetDO;

@Service
public class GadgetBuildingService {

    public GadgetBuildingService() {
    }

    /**
     * Creates a product composed of the two items provided.
     * @param item1 An instance of item to use in product.
     * @param Item2 An instance of item to use in product.
     */
    public GadgetDO composeGadgetFromItems(PartDO item1, PartDO item2) {
        // Should be in a validator
        if (item1 == null || item2 == null || item1.equals(item2)) {
            throw new IllegalArgumentException("Parameters must be non null and non equal.");
        }
        var product = new GadgetDO(-1L);
        product.addItem(item1);
        product.addItem(item2);
        return product;
    }
}
