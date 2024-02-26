package com.org.product.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.product.utils.JsonReader;

import java.io.IOException;
import java.util.List;

/**
 * Provides helper methods for test classes.
 * @param <T> The type of entity being handled by the test class.
 */
public class TestHelper<T> {

    private JsonReader<T> dataReader;
    private String baseUrl;

    /**
     * Default constructor.
     * @param sampleClass  A dummy instance of the type class.
     * @param objectMapper Injectable resource available, use <code>@Inject ObjectMapper mapper</code>.
     */
    public TestHelper() {
    }

    /**
     * Constructor for parametrized initialization.
     * @param sampleClass  A dummy instance of the type class.
     * @param objectMapper Injectable resource available, use <code>@Inject ObjectMapper mapper</code>.
     */
    public TestHelper(T sampleClass, ObjectMapper objectMapper) {
        this(sampleClass, "http://localhost:8000", objectMapper);
    }

    /**
     * @param sampleClass  A dummy instance of the type class.
     * @param baseUrl      The base url to use with constructed urls.
     * @param objectMapper Injectable resource available, use <code>@Inject ObjectMapper mapper</code>.
     */
    public TestHelper(T sampleClass, String baseUrl, ObjectMapper objectMapper) {
        this.dataReader = new JsonReader<>(sampleClass, objectMapper);
        this.setBaseUrl(baseUrl);
    }

    /**
     * Has little significance with the actual request being made in tests, mostly for constructing "a real like" url
     * that could be used for manual testing. Only the url path after the port is important for testing the actual end
     * points.
     * @return The base url to use with with requests, e.g. "http://localhost:8000".
     */
    protected String getBaseUrl() {
        return this.baseUrl;
    }

    /**
     * Has little significance with the actual request being made in tests, mostly for constructing "a real like" url
     * that could be used for manual testing. Only the url path after the port is important for testing the actual end
     * points.
     * @param baseUrl The base url to use with request, e.g. "http://localhost:8000".
     * @return Instance of this class.
     */
    protected TestHelper<T> setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    /**
     * Return a full url to the provided end point, using the base url and the context path root assumed to be used for
     * services at this project.
     * @param path The specific path (after the default root context path) to the endpoint being tested.
     * @return
     */
    public String getUrlToPath(String path) {
        return this.getBaseUrl() + "/" + path;
    }

    /**
     * Return a full url to the provided end point, using the base url and the <u>secured</u> context path root assumed
     * to be used for services at this project.
     * @param path The specific path (after the default root context path) to the endpoint being tested.
     * @return
     */
    public String getSecuredUrlToPath(String path) {
        return this.getBaseUrl() + "/secured/" + path;
    }

    /**
     * Create an entity from the given json.
     * @param jsonData The data to create the entity from.
     * @return An entity parsed from the string.
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    public T entityFromJson(String jsonData) throws JsonMappingException, JsonProcessingException {
        return this.dataReader.readEntityFromJsonString(jsonData);
    }

    /**
     * Create a list of entities from the given json.
     * @param jsonData The json list containing data to created the entities from.
     * @return A list of entities created from the data.
     * @throws IOException
     */
    public List<T> entitiesFromJson(String jsonData) throws IOException {
        return this.dataReader.readEntitiesFromJsonString(jsonData);
    }

    /**
     * Converts the given entity to json string.
     * @param entity The entity to jsonify.
     * @return A json string representing of the given object.
     * @throws JsonProcessingException
     */
    public String jsonFromEntity(T entity) throws JsonProcessingException {
        return this.dataReader.getObjectMapper().writeValueAsString(entity);
    }
}