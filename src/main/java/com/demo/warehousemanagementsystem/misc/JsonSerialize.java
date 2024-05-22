package com.demo.warehousemanagementsystem.misc;

import com.demo.warehousemanagementsystem.exception.JsonStringConversionException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class JsonSerialize {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

    public static String convertResponseToJsonString(@NonNull final Object data) {
        try {
            ObjectNode jsonObject = OBJECT_MAPPER.createObjectNode();
            jsonObject.set("result", OBJECT_MAPPER.convertValue(HttpStatus.OK, JsonNode .class));
            jsonObject.set("data", OBJECT_MAPPER.convertValue(data, JsonNode.class));
            return OBJECT_MAPPER.writeValueAsString(jsonObject);
        } catch (JsonProcessingException e) {
            log.error("Error occurred while processing response object", e);
            throw new JsonStringConversionException("Could not create Json Response string", e);
        }
    }

    public static String exceptionJsonString(final Object data) {
        try {
            ObjectNode jsonObject = OBJECT_MAPPER.createObjectNode();
            jsonObject.set("resultInfo", OBJECT_MAPPER.convertValue(data, JsonNode.class));
            return OBJECT_MAPPER.writeValueAsString(jsonObject);
        } catch (JsonProcessingException e) {
            log.error("Error occurred while processing response object", e);
            throw new JsonStringConversionException("Could not create Json Response string", e);
        }
    }
}
