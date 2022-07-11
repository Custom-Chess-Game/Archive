package me.smudge.client.configuration;

import java.util.ArrayList;

/**
 * Represents a configs options
 */
public interface ConfigurationSelection {

    /**
     * Used to set a value to the config
     * @param path The location of the key
     * @param value The value to set
     */
    void set(String path, Object value);

    /**
     * Used to get a string from the config
     * @param path The location of the key
     * @return String value
     */
    String getString(String path);

    /**
     * Used to get an integer from the config
     * @param path The location of the key
     * @return Int value
     */
    int getInt(String path);

    /**
     * Used to get an array of integers from the config
     * @param path The location of the key
     * @return Array of integers
     */
    ArrayList<Integer> getIntArray(String path);
}
