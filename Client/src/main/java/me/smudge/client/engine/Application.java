package me.smudge.client.engine;

import me.smudge.client.Chess;
import me.smudge.client.items.Item;
import me.smudge.client.pages.MainMenu;
import me.smudge.client.pages.Page;
import me.smudge.client.positions.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

/**
 * Represents the main handler
 */
public class Application {

    /**
     * Current page
     * Static so any class can change the page
     */
    private static Page page = null;

    /**
     * The state of the application
     */
    private static ApplicationState state;

    /**
     * Represents the window
     */
    private static JFrame frame;
    private static JPanel background;

    /**
     * Create a new instance of the application
     */
    public Application(String caption) {
        frame = new JFrame(caption);

        frame.setSize(Chess.getConfig().getScreenSize().get(0), Chess.getConfig().getScreenSize().get(1));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ApplicationListener listener = new ApplicationListener();
        frame.addWindowListener(listener);

        this.setupWindowReSizer();

        Application.state = ApplicationState.Running;
        Application.setPage(new MainMenu());

        this.start();
    }

    /**
     * When the window is resized
     * - the screen size will be updated in the config
     * - the page will be re-rendered
     */
    private void setupWindowReSizer() {
        Application.frame.addComponentListener(
            new ComponentAdapter() {
                public void componentResized(ComponentEvent componentEvent) {
                    ArrayList<Integer> screenSize = new ArrayList<>();

                    screenSize.add(componentEvent.getComponent().getWidth());
                    screenSize.add(componentEvent.getComponent().getHeight());

                    Chess.getConfig().set("ScreenSize", screenSize);
                    render();
                }
            }
        );
    }

    /**
     * Used to start the loop
     * This loop demonstrates an efficient delta time method
     * Whereas it isn't necessary, this is a good example
     */
    private void start() {
        // Time since last tick
        long lastTime = System.nanoTime();

        double targetFPS = Chess.getConfig().getMaxFPS();
        double target = 1000000000 / targetFPS;

        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        Page lastPage = null;

        while (this.isRunning()) {

            long now = System.nanoTime();
            delta += (now - lastTime) / target;
            lastTime = now;

            while (delta >= 1) {
                this.tick();
                delta --;
            }

            if (this.isRunning() && lastPage != Application.page) {
                Application.render();
                lastPage = Application.page;
            }

            frames ++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }
    }

    /**
     * Update all objects
     */
    public void tick() {
        if (Application.page == null) return;
        Application.page.update();
    }

    /**
     * Render all objects
     */
    public static void render() {
        if (Application.page == null) return;

        Application.removeBackground();

        Application.background = new JPanel();
        Application.background.setBackground(Color.BLACK);
        Application.background.setLayout(null);
        Application.background.setPreferredSize(new Dimension(50, 50));

        // Render page
        Application.page.render(Application.background);

        Application.frame.add(Application.background);
        Application.frame.setVisible(true);
    }

    /**
     * Used to check if the {@link Application} is running
     * @return True if running
     */
    public boolean isRunning() {
        return Application.state == ApplicationState.Running;
    }

    /**
     * Used to set what page is being shown
     * @param page Page to show
     */
    public static void setPage(Page page) {
        Application.removeItemsFromPage();
        Application.page = page;
        Application.frame.setVisible(true);
    }

    /**
     * Used to remove all items from the page
     * Doing this will create a clean canvas for the next page
     */
    public static void removeItemsFromPage() {
        if (Application.page == null) return;
        for (Item item : Application.page.getItems()) {
            Application.frame.remove(item.getComponent());
        }
    }

    /**
     * Used to remove the background panel
     */
    public static void removeBackground() {
        if (Application.background == null) return;
        Application.frame.remove(Application.background);
    }

    /**
     * Used to get the page currently displayed
     * @return Current page
     */
    public static Page getPage() {
        return Application.page;
    }

    /**
     * Stop application when the window has been closed
     */
    public static void setClosed() {
        Application.state = ApplicationState.Stopped;
    }

    /**
     * Used to get the game state
     * @return Current game state
     */
    public static ApplicationState getState() {
        return Application.state;
    }

    /**
     * @return Location of window
     */
    public static Position getLocation() {
        return new Position(
                Application.frame.getX(),
                Application.frame.getY() + 30
        );
    }
}
