package com.org.product.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.org.product.entity.PartDO;
import com.org.product.services.GadgetBuildingService;

/**
 * The test is post fixed with "PureTest" since this test is a 'fail fast test' which may not depend about the spring
 * context at all. Creating the context makes it unnecessary slow to execute.
 */
@TestInstance(Lifecycle.PER_CLASS)
public class GadgetBuildingServicePureTest extends Assertions {

    private GadgetBuildingService service;

    @BeforeAll
    public void initialize() {
        this.service = new GadgetBuildingService();
    }

    @Test
    public void composeGadgetFromItems() {
        var item1 = new PartDO(1L);
        var item2 = new PartDO(2L);
        var gadget = this.service.composeGadgetFromItems(item1, item2);
        assertNotNull(gadget);
        assertNotNull(gadget.getItems());
        assertEquals(2, gadget.getItems().size());
    }

    @Test
    public void composeGadgetFromItems_shouldThrowExceptionFromNullItems() {
        var item = new PartDO(1L);

        var thrown = assertThrows(IllegalArgumentException.class,
            () -> this.service.composeGadgetFromItems(item, null));
        assertNotNull(thrown);
        var message = thrown.getMessage();
        assertNotNull(message);
        assertTrue(message.contains("non null"),
            "The message is expected to mention about non null parameters, but was: " + message);

        thrown = assertThrows(IllegalArgumentException.class, () -> this.service.composeGadgetFromItems(null, item));
        assertNotNull(thrown);
        message = thrown.getMessage();
        assertNotNull(message);
        assertTrue(message.contains("non null"),
            "The message is expected to mention about non null parameters, but was: " + message);
    }

    @Test
    public void composeGadgetFromItems_shouldThrowExceptionFromEqualItems() {
        var item1 = new PartDO(1L);
        var item2 = new PartDO(1L);

        var thrown = assertThrows(IllegalArgumentException.class,
            () -> this.service.composeGadgetFromItems(item1, item2));
        assertNotNull(thrown);
        var message = thrown.getMessage();
        assertNotNull(message);
        assertTrue(message.contains("non equal"),
            "The message is expected to mention about non equal parameters, but was: " + message);
    }
}
