package com.springapp.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * This class contains utility methods for integration tests.
 */
public class IntegrationTestUtil {

    private IntegrationTestUtil() {

    }

    /**
     * This method convert an object to a byte array.
     *
     * @param object
     * @return
     * @throws IOException
     */
    public static byte[] convertObjectToJsonBytes(final Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}
