package me.smudge.client;

import me.smudge.client.configuration.Configuration;
import me.smudge.client.configuration.subconfig.Config;
import me.smudge.client.engine.Application;

/**
 * me.smudge.client.Main class for the client
 */
public class Chess {

    /**
     * Instance of the configuration file
     */
    private static Config config;

    /**
     * Used to create a new instance of the chess application
     */
    public Chess() {

        // Set up the config
        Chess.config = new Config();

        new Application("Chess");
    }

    /**
     * Used to get main {@link Configuration}
     * @return Config instance
     */
    public static Config getConfig() {
        return Chess.config;
    }
}
