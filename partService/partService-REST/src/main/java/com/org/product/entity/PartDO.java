package com.org.product.entity;

import com.org.product.generated.model.Part;

public class PartDO extends Part implements Identifiable {

    public PartDO(Long id) {
        super();
        super.setId(id);
    }

    public PartDO(Identifiable id) {
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
        if (obj instanceof PartDO) {
            var item = (PartDO) obj;
            if (this.getId() == item.getId()) {
                return true;
            }
        } else {
            return false;
        }
        return true;
    }
}
