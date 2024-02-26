package com.org.product.entity;

import java.util.HashSet;
import java.util.Set;

import com.org.product.generated.model.Item;

public class ProductDO implements Identifiable {
    private Long id;
    private Set<ItemDO> items;

    public ProductDO(Identifiable id) {
        this(id.getId());
    }

    public ProductDO(Long id) {
        this.setId(id);
        this.items = new HashSet<>();
    }

    @Override
    public Long getId() {
        return this.id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public Set<ItemDO> getItems() {
        return items;
    }

    public void addItem(ItemDO item) {
        this.items.add(item);
    }
}
