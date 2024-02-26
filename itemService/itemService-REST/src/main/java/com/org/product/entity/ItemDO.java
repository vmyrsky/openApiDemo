package com.org.product.entity;

import com.org.product.generated.model.Item;

public class ItemDO extends Item implements Identifiable {

    public ItemDO(Long id) {
        super();
        super.setId(id);
    }

    public ItemDO(Identifiable id) {
        super();
        super.setId(id.getId());
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (obj instanceof ItemDO) {
            var item = (ItemDO) obj;
            if (this.getId() == item.getId()) {
                return true;
            }
        } else {
            return false;
        }
        return true;
    }
}
