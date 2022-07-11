package me.smudge.client.configuration;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * Represents a config file
 */
public class Configuration implements ConfigurationSelection {

    /**
     * The configuration file
     */
    private final File file;

    /**
     * Data loaded from the yaml
     */
    private Map<String, Object> data;

    /**
     * Creates a new {@link Configuration}
     */
    public Configuration(String fileName) {

        // Get config file
        this.file = new File("src/main/resources/" + fileName + ".yml");

        // Create file if it doesn't exist
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();

            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * Loads the configuration file to the yaml instance
     */
    public void load() {
        try {
            InputStream inputStream = new FileInputStream(this.file);

            Yaml yaml = new Yaml();
            this.data = (Map<String, Object>) yaml.load(inputStream);

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void set(String path, Object value) {
        this.data.put(path, value);
    }

    @Override
    public String getString(String path) {
        if (this.data.get(path) instanceof String) return (String) this.data.get(path);
        return null;
    }

    @Override
    public int getInt(String path) {
        if (this.data.get(path) instanceof Integer) return (int) this.data.get(path);
        return 0;
    }

    @Override
    public ArrayList<Integer> getIntArray(String path) {
        if (this.data.get(path) instanceof ArrayList) return (ArrayList<Integer>) this.data.get(path);
        return null;
    }
}
