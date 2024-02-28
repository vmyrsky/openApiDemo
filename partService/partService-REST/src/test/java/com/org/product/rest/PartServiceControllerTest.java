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

import com.org.product.entity.PartDO;
import com.org.product.generated.model.Part;
import com.org.product.helper.TestHelper;
import com.org.product.services.GenericService;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class PartServiceControllerTest extends Assertions {
    private static final String PARTS_PATH = "/parts";
    private static final String PART_PATH = "/part/{id}";

    @Inject
    private MockMvc mockMvc;
    @Inject
    private TestHelper<Part> partHelper;
    @MockBean
    private GenericService<PartDO> partServiceMock;
    @Captor
    private ArgumentCaptor<Long> longCaptor;

    @Test
    public void getPart_respondsWithDummy() throws Exception {
        var mockPart = new PartDO(1234567890L);
        mockPart.setName("Mock part");
        Mockito.when(this.partServiceMock.getEntity(ArgumentMatchers.anyLong())).thenReturn(mockPart);

        var response = this.mockMvc
            .perform(MockMvcRequestBuilders.get(PART_PATH, mockPart.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Mockito.verify(this.partServiceMock).getEntity(this.longCaptor.capture());
        assertEquals(mockPart.getId(), this.longCaptor.getValue(), "The captured id should match with the given.");
        assertFalse(ObjectUtils.isEmpty(response));

        var responsePart = this.partHelper.entityFromJson(response);
        assertEquals(mockPart.getId(), responsePart.getId());
        assertEquals(mockPart.getName(), responsePart.getName());
    }

    @Test
    public void getParts_respondsNotImplemented() throws Exception {
        this.mockMvc
            .perform(MockMvcRequestBuilders.get(PARTS_PATH).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotImplemented());
    }
}