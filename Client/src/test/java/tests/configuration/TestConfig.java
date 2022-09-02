package tests.configuration;

import me.smudge.client.configuration.subconfig.Config;
import org.junit.jupiter.api.Test;
import utilitys.ResultChecker;
import utilitys.ResultNotNull;

/**
 * Used to test the config class
 */
public class TestConfig {

    @Test
    public void testScreenSize() throws Exception {
        Config config = new Config();

        new ResultChecker().expect(config.getScreenSize(), new ResultNotNull());
    }
}
