package me.smudge.client.configuration.subconfig;

import me.smudge.client.configuration.Configuration;

import java.util.ArrayList;

/**
 * Represents the main config file
 */
public class Config extends Configuration implements ConfigSelection {

    /**
     * Creates a new {@link Configuration}
     */
    public Config() {
        super("config");

        // Load the config
        this.load();
    }

    @Override
    public int getMaxFPS() {
        return this.getInt("MaxFPS");
    }

    @Override
    public ArrayList<Integer> getScreenSize() {
        return this.getIntArray("ScreenSize");
    }
}
