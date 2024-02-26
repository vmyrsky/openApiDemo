package com.org.product.rest;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.ObjectUtils;

import com.org.product.generated.model.Item;
import com.org.product.helper.ItemFixture;
import com.org.product.helper.TestHelper;

/**
 * Do not create an IT test for the rest controllers. The IT tests should be targeted directly to services which are
 * easier to debug in case of test failures. This is just an example for running integration tests with maven profile
 * for the purpose.
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class ItemServiceControllerIT extends Assertions {
    private static final String ITEM_PATH = "/item/{id}";

    @Inject
    private MockMvc mockMvc;
    @Inject
    private ItemFixture itemFixture;
    @Inject
    private TestHelper<Item> itemHelper;

    @Test
    public void getItem_respondsWithDummy() throws Exception {
        var fixtureItem = this.itemFixture.buildDummyEntity();
        assertNotNull(fixtureItem.getName());
        assertNotNull(fixtureItem.getDetails());
        this.itemFixture.persistToDB(fixtureItem);
        var response = this.mockMvc
            .perform(MockMvcRequestBuilders.get(ITEM_PATH, fixtureItem.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertFalse(ObjectUtils.isEmpty(response));
        var dummyItem = this.itemHelper.entityFromJson(response);
        assertEquals(fixtureItem.getId(), dummyItem.getId());
        assertEquals(fixtureItem.getName(), dummyItem.getName());
    }
}