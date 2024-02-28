package com.org.product.helper;

import org.springframework.stereotype.Service;

import com.org.product.entity.PartDO;
import com.org.product.generic.model.Identifiable;

@Service("partFixture")
public class PartFixture extends Fixture<PartDO> {

    @Override
    public PartDO buildDummyEntity(Identifiable id) {
        var part = new PartDO(id);
        part.setName("Dummy part");
        part.setDetails("Dummy part details.");
        return part;
    }
}
