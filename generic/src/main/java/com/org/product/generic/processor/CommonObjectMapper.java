package com.org.product.generic.processor;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;

public class CommonObjectMapper {

    /**
     * Creates an ObjectMapper that is usable for most cases to process and produce well formed JSON.
     * @return A generally usable ObjectMapper to use for processing JSON.
     */
    public static ObjectMapper createObjectMapper() {
        var module = new JavaTimeModule();
        module.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ISO_TIME));
        module.addSerializer(ZonedDateTime.class, new ZonedDateTimeSerializer(DateTimeFormatter.ISO_DATE_TIME));
        var mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.enable(JsonReadFeature.ALLOW_UNQUOTED_FIELD_NAMES.mappedFeature());
        mapper.enable(JsonReadFeature.ALLOW_SINGLE_QUOTES.mappedFeature());
        mapper.enable(JsonWriteFeature.QUOTE_FIELD_NAMES.mappedFeature());
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        mapper.registerModule(module);
        return mapper;
    }
}
