package me.smudge.client.engine;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Represents the JFrame Listener
 */
public class ApplicationListener extends WindowAdapter {

    /**
     * Called when the JFrame is closed
     * Making sure the application is exited safely
     * @param event WindowEvent
     */
    public void windowClosing(WindowEvent event) {
        Application.setClosed();
    }
}
