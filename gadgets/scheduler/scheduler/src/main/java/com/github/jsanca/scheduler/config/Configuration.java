package com.github.jsanca.scheduler.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Encapsulates the main configuration.
 * @author jsanca
 */
public class Configuration implements Serializable {

    private final List<TaskConfiguration> configurations;

    @JsonCreator
    public Configuration(@JsonProperty(value = "configurations", required = true)
                             final List<TaskConfiguration> configurations) {
        this.configurations = configurations;
    }

    public List<TaskConfiguration> getConfigurations() {
        return configurations;
    }
} // E:O:F:Configuration
