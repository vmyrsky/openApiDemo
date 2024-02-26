package com.org.product.helper;

import org.springframework.stereotype.Service;

import com.org.product.entity.Identifiable;
import com.org.product.entity.ItemDO;

@Service("itemFixture")
public class ItemFixture extends Fixture<ItemDO> {

    @Override
    public ItemDO buildDummyEntity(Identifiable id) {
        var item = new ItemDO(id);
        item.setName("Dummy item");
        item.setDetails("Dummy item details.");
        return item;
    }
}
