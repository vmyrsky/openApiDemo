package com.org.product;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.org.product.generated.model.Item;
import com.org.product.helper.TestHelper;

@SpringBootTest
@ActiveProfiles("test")
class ItemApplicationTests extends Assertions {

    @Inject
    private TestHelper<Item> itemHelper;

    @Test
    void contextLoads() {
        assertTrue(true);
    }

    @Test
    public void testGenericInjectionWorks() {
        assertNotNull(this.itemHelper);
    }
}
