package me.smudge.client.configuration.subconfig;

import java.util.ArrayList;

/**
 * Represents the main config options
 */
public interface ConfigSelection {

    /**
     * The maximum amount of frames per second
     * @return Integer config value
     */
    int getMaxFPS();

    /**
     * The current window size
     * @return Array containing with and height
     */
    ArrayList<Integer> getScreenSize();
}
