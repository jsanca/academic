package com.github.jsanca.scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javafx.scene.input.KeyCode.T;

/**
 * Represents a marshaller
 * @author jsanca
 */
public class MarshallerUtils {

    private static Logger logger = Logger.getLogger(MarshallerUtils.class.getName());
    private final ObjectMapper objectMapper;

    public MarshallerUtils() {
        this(new ObjectMapper());
    }

    public MarshallerUtils(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> T fromJson (final InputStream stream,
                           final Class<T> clazz) {

        T object = null;
        try {

            object = this.objectMapper.readValue(stream, clazz);
        } catch (IOException e) {

            logger.log(Level.WARNING, e.getMessage(), e);
        }

        return object;
    }

}
