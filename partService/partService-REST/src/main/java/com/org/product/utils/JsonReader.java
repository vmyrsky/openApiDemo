package com.org.product.utils;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class is for reading sample files containing json data and mapping that data into entities of the type specified
 * as generalization for this class.
 * @param <T> The type to of the entity to convert the json data to.
 */
public class JsonReader<T> implements UTF8FileReader {
    private T type;
    private ObjectMapper objectMapper;

    /**
     * Constructor available only with Injection for object mapper.
     * @param type         A sample type of the class generalized.
     * @param objectMapper The object mapper to use. Injectable resource available, use
     *                     <code>@Inject ObjectMapper mapper</code>.
     */
    public JsonReader(T type, ObjectMapper objectMapper) {
        this.setType(type);
        this.setObjectMapper(objectMapper);
    }

    /**
     * @return A sample type of the entities this reader can handle.
     */
    public final T getType() {
        return this.type;
    }

    private void setType(T type) {
        this.type = type;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    private void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * @param <T> The type of the entities handled by this class.
     * @return A sample of the entity array to use.
     */
    public final <T> T[] getTypeArray() {
        return (T[]) Array.newInstance(this.getType().getClass(), 1);
    }

    /**
     * Reads in a single json object from a file. The json is converted to the type this class is instantiated for.
     * @param fileName    The name of the file from which to load the json data from.
     * @param charsetName The name of the charset to use. If not given (<code>null</code>), will default to 'UTF-8'.
     *                    Could be also e.g. ."ISO-8859-1";
     * @return A single entity created from the json object in the file.
     * @throws IOException    If the file was not found.
     * @throws ParseException If the json could not be mapped to the entity type specified for this class.
     */
    public T readEntityFromJsonFile(String fileName, String charsetName) throws IOException {
        return this.readEntityFromJsonFile(fileName, Charset.forName(charsetName));
    }

    /**
     * Reads in a single json object from a file. The json is converted to the type this class is instantiated for.
     * @param fileName The name of the file from which to load the json data from.
     * @param charset  The character set to use. If not given (<code>null</code>), will default to 'UTF-8'. Could be
     *                 also e.g. ."ISO-8859-1";
     * @return A single entity created from the json object in the file.
     * @throws IOException    If the file was not found.
     * @throws ParseException If the json could not be mapped to the entity type specified for this class.
     */
    public T readEntityFromJsonFile(String fileName, Charset charset) throws IOException {
        return this.readEntityFromJsonString(this.readFile(fileName, charset));
    }

    /**
     * Convert a json String into an entity.
     * @param json The string to convert.
     * @return The entity based on the gcharsetNameiven json.
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    public T readEntityFromJsonString(String json) throws JsonMappingException, JsonProcessingException {
        return (T) this.objectMapper.readValue(json, this.getType().getClass());
    }

    /**
     * Reads in multiple json objects from a file. The json objects are converted to the type this class is instantiated
     * for.
     * @param fileName    The name of the file from which to load the json data from.
     * @param charsetName The name of the charset to use. If not given (<code>null</code>), will default to 'UTF-8'.
     *                    Could be also e.g. ."ISO-8859-1";
     * @return Multiple entities created from the json objects in the file.
     * @throws IOException    If the file was not found.
     * @throws ParseException If the json could not be mapped to the entity type specified for this class.
     */
    public List<T> readEntitiesFromJsonFile(String fileName, String charsetName) throws IOException {
        return this
            .readEntitiesFromJsonString(
                this.readFile(fileName, charsetName != null ? Charset.forName(charsetName) : null));
    }

    /**
     * Reads in multiple json objects from a file. The json objects are converted to the type this class is instantiated
     * for.
     * @param fileName The name of the file from which to load the json data from.
     * @param charset  The character set to use. If not given (<code>null</code>), will default to 'UTF-8'. Could be
     *                 also e.g. ."ISO-8859-1";
     * @return Multiple entities created from the json objects in the file.
     * @throws IOException    If the file was not found.
     * @throws ParseException If the json could not be mapped to the entity type specified for this class.
     */
    public List<T> readEntitiesFromJsonFile(String fileName, Charset charset) throws IOException {
        return this.readEntitiesFromJsonString(this.readFile(fileName, charset));
    }

    /**
     * Reads in multiple json objects from a json string. The json objects are converted to the type this class is
     * instantiated for.
     * @param json The json string from which to get data from.
     * @return Multiple entities created from the json objects.
     * @throws IOException    If the file was not found.
     * @throws ParseException If the json could not be mapped to the entity type specified for this class.
     */
    public List<T> readEntitiesFromJsonString(String json) throws IOException {
        var entitiesArray = (T[]) this.objectMapper.readValue(json, this.getTypeArray().getClass());
        return List.of(entitiesArray);
    }
}
