package com.github.jsanca.scheduler.config;

import com.github.jsanca.scheduler.MarshallerUtils;
import com.github.jsanca.scheduler.SchedulerException;
import com.github.jsanca.scheduler.UserInterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Reads the configuration.
 * By default assumes it is on the app root directory as a config folder name.
 * However you can override it with a system property "config={path}"
 * @author jsanca
 */
public class ConfigurationLoader {


    public static final String CONFIG_SYSTEM_PARAM_KEY = "config";
    public static final String DEFAULT_CONFIG_PATH = "/config";

    private final UserInterface userInterface;
    private final MarshallerUtils marshallerUtils;


    public ConfigurationLoader(final UserInterface userInterface,
                               final MarshallerUtils marshallerUtils) {

        this.userInterface   = userInterface;
        this.marshallerUtils = marshallerUtils;
    }

    public Configuration loadConfiguration () {

        final String configurationPath = null != System.getProperty(CONFIG_SYSTEM_PARAM_KEY)?
                System.getProperty(CONFIG_SYSTEM_PARAM_KEY): DEFAULT_CONFIG_PATH;

        final File configurationFile = new File(configurationPath);
        final List<TaskConfiguration> configurations;
        if (!configurationFile.exists()) {

            throw new SchedulerException("The configuration path: " + configurationPath +
                            ", does not exists. Can not starts the app");
        }


        try {

            configurations =
                    this.marshallerUtils.fromJson(new FileInputStream(configurationFile), List.class);
        } catch (FileNotFoundException e) {

            throw new SchedulerException(e);
        }

        return new Configuration(configurations);
    } // loadConfiguration

} // E:O:F:ConfigurationLoader.
