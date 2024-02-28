package com.org.product.entity;

import java.util.HashSet;
import java.util.Set;

public class GadgetDO implements Identifiable {
    private Long id;
    private Set<PartDO> items;

    public GadgetDO(Identifiable id) {
        this(id.getId());
    }

    public GadgetDO(Long id) {
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

    public Set<PartDO> getItems() {
        return items;
    }

    public void addItem(PartDO item) {
        this.items.add(item);
    }
}
