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

import com.org.product.generated.model.Part;
import com.org.product.helper.PartFixture;
import com.org.product.helper.TestHelper;

/**
 * Do not create an IT test for the rest controllers. The IT tests should be targeted directly to services which are
 * easier to debug in case of test failures. This is just an example for running integration tests with maven profile
 * for the purpose.
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class PartServiceControllerIT extends Assertions {
    private static final String PART_PATH = "/part/{id}";

    @Inject
    private MockMvc mockMvc;
    @Inject
    private PartFixture partFixture;
    @Inject
    private TestHelper<Part> partHelper;

    @Test
    public void getPart_respondsWithDummy() throws Exception {
        var fixturePart = this.partFixture.buildDummyEntity();
        assertNotNull(fixturePart.getName());
        assertNotNull(fixturePart.getDetails());
        this.partFixture.persistToDB(fixturePart);
        var response = this.mockMvc
            .perform(MockMvcRequestBuilders.get(PART_PATH, fixturePart.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertFalse(ObjectUtils.isEmpty(response));
        var dummyPart = this.partHelper.entityFromJson(response);
        assertEquals(fixturePart.getId(), dummyPart.getId());
        assertEquals(fixturePart.getName(), dummyPart.getName());
    }
}