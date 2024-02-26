package com.org.product.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.org.product.entity.ItemDO;
import com.org.product.services.ProductBuildingService;

/**
 * The test is post fixed with "PureTest" since this test is a 'fail fast test' which may not depend about the spring
 * context at all. Creating the context makes it unnecessary slow to execute.
 */
@TestInstance(Lifecycle.PER_CLASS)
public class ProductBuildingServicePureTest extends Assertions {

    private ProductBuildingService service;

    @BeforeAll
    public void initialize() {
        this.service = new ProductBuildingService();
    }

    @Test
    public void composeProductFromItems() {
        var item1 = new ItemDO(1L);
        var item2 = new ItemDO(2L);
        var product = this.service.composeProductFromItems(item1, item2);
        assertNotNull(product);
        assertNotNull(product.getItems());
        assertEquals(2, product.getItems().size());
    }

    @Test
    public void composeProductFromItems_shouldThrowExceptionFromNullItems() {
        var item = new ItemDO(1L);

        var thrown = assertThrows(IllegalArgumentException.class,
            () -> this.service.composeProductFromItems(item, null));
        assertNotNull(thrown);
        var message = thrown.getMessage();
        assertNotNull(message);
        assertTrue(message.contains("non null"),
            "The message is expected to mention about non null parameters, but was: " + message);

        thrown = assertThrows(IllegalArgumentException.class, () -> this.service.composeProductFromItems(null, item));
        assertNotNull(thrown);
        message = thrown.getMessage();
        assertNotNull(message);
        assertTrue(message.contains("non null"),
            "The message is expected to mention about non null parameters, but was: " + message);
    }

    @Test
    public void composeProductFromItems_shouldThrowExceptionFromEqualItems() {
        var item1 = new ItemDO(1L);
        var item2 = new ItemDO(1L);

        var thrown = assertThrows(IllegalArgumentException.class,
            () -> this.service.composeProductFromItems(item1, item2));
        assertNotNull(thrown);
        var message = thrown.getMessage();
        assertNotNull(message);
        assertTrue(message.contains("non equal"),
            "The message is expected to mention about non equal parameters, but was: " + message);
    }
}
