package com.org.product.configuration;

import javax.inject.Inject;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.product.entity.ItemDO;
import com.org.product.generated.model.Item;
import com.org.product.helper.Fixture;
import com.org.product.helper.TestHelper;

@TestConfiguration
public class ConfigForTests {

    @Inject
    private ObjectMapper mapper;

    /**
     * Need to explicitly provide the sample class for a generic bean when one is needed.
     * @return Instance of the TestHelper to inject when a helper for type 'Item' is needed.
     */
    @Bean("itemHelper")
    public TestHelper<Item> getTestHelper() {
        return new TestHelper<>(new Item(), mapper);
    }
}