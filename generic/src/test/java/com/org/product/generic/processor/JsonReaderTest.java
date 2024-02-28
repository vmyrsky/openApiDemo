package com.org.product.generic.processor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JsonReaderTest extends Assertions {
    private JsonReader<TestEntity> testReader;

    @BeforeEach
    public void init() {
        var objectMapper = CommonObjectMapper.createObjectMapper();
        this.testReader = new JsonReader<TestEntity>(new TestEntity(), objectMapper);
    }

    @Test
    public void readSingleEntity() throws IOException {
        var testEntity = this.testReader.readEntityFromJsonFile("test.json", StandardCharsets.UTF_8);
        assertNotNull(testEntity);

        var expected = new TestEntity();
        expected.id = "id";
        expected.flag = true;
        expected.number = 123;
        expected.decimal = 23.45;
        expected.date = LocalDate.of(2023, 01, 30);
        expected.time = LocalTime.of(13, 52, 15);
        expected.zonedDateTime = ZonedDateTime.of(2023, 01, 30, 13, 52, 15, 00, ZoneId.of("Etc/GMT+6"));
        // expected.zonedDateTime = expected.zonedDateTime.
        expected.ignored = null;
        expected.renamed = "original json field name is 'named'";

        assertEquals(expected.getId(), testEntity.getId());
        assertEquals(expected.getIgnored(), testEntity.getIgnored());
        assertEquals(expected.getRenamed(), testEntity.getRenamed());
        assertEquals(expected.getDate(), testEntity.getDate());
        assertEquals(expected.getDecimal(), testEntity.getDecimal());
        assertEquals(expected.getNumber(), testEntity.getNumber());
        assertEquals(expected.getTime(), testEntity.getTime());
        assertTrue(expected.getZonedDateTime().isEqual(testEntity.getZonedDateTime()), "Expecting the zoned date time from json '"
            + testEntity.getZonedDateTime() + "' to be same as '" + expected.getZonedDateTime() + "'");
    }

    @Test
    public void readListOfEntities() throws IOException {
        var tests = this.testReader.readEntitiesFromJsonFile("tests.json", (String) null);
        assertNotNull(tests);
        assertEquals(2, tests.size());
        assertEquals("id1", tests.get(0).getId());
        assertEquals("id2", tests.get(1).getId());
    }
}
