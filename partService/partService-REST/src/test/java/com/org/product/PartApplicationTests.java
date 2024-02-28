package com.org.product;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.org.product.generated.model.Part;
import com.org.product.helper.TestHelper;

@SpringBootTest
class PartApplicationTests extends Assertions {

    @Inject
    private TestHelper<Part> partHelper;

    @Test
    void contextLoads() {
        assertTrue(true);
    }

    @Test
    public void testGenericInjectionWorks() {
        assertNotNull(this.partHelper);
    }
}
