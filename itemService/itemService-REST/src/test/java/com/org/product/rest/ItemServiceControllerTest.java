package com.org.product.rest;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.ObjectUtils;

import com.org.product.entity.ItemDO;
import com.org.product.generated.model.Item;
import com.org.product.helper.TestHelper;
import com.org.product.services.GenericService;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class ItemServiceControllerTest extends Assertions {
    private static final String ITEMS_PATH = "/items";
    private static final String ITEM_PATH = "/item/{id}";

    @Inject
    private MockMvc mockMvc;
    @Inject
    private TestHelper<Item> itemHelper;
    @MockBean
    private GenericService<ItemDO> itemServiceMock;
    @Captor
    private ArgumentCaptor<Long> longCaptor;

    @Test
    public void getItem_respondsWithDummy() throws Exception {
        var mockItem = new ItemDO(1234567890L);
        mockItem.setName("Mock item");
        Mockito.when(this.itemServiceMock.getEntity(ArgumentMatchers.anyLong())).thenReturn(mockItem);

        var response = this.mockMvc
            .perform(MockMvcRequestBuilders.get(ITEM_PATH, mockItem.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Mockito.verify(this.itemServiceMock).getEntity(this.longCaptor.capture());
        assertEquals(mockItem.getId(), this.longCaptor.getValue(), "The captured id should match with the given.");
        assertFalse(ObjectUtils.isEmpty(response));

        var responseItem = this.itemHelper.entityFromJson(response);
        assertEquals(mockItem.getId(), responseItem.getId());
        assertEquals(mockItem.getName(), responseItem.getName());
    }

    @Test
    public void getItems_respondsNotImplemented() throws Exception {
        this.mockMvc
            .perform(MockMvcRequestBuilders.get(ITEMS_PATH).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotImplemented());
    }
}