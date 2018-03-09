package com.github.jsanca.scheduler.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Map;

/**
 * Encapsulate the task configuration, basically is a map
 * @author jsanca
 */
public class TaskConfiguration implements Serializable {

    private final Map<String, Object> config;

    @JsonCreator
    public TaskConfiguration(@JsonProperty(value = "config", required = true)
                                 final Map<String, Object> config) {

        this.config = config;
    }

    public Map<String, Object> getConfig() {
        return config;
    }
} // E:O:F:TaskConfiguration.
