package h1z1.screens;

import h1z1.MainFrame;
import h1z1.input.InputProvider;

import java.awt.*;
import java.util.List;

/**
 * The base ScreenManager class to control game logic for a specific GameState.
 * Each subclass must override the update() and paint() methods.
 */
public abstract class ScreenManager {

    private MainFrame mainFrame;

    public ScreenManager(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    // Allow subclasses only (hence the protected) to get access to the parent MainFrame
    protected MainFrame getMainFrame() {
        return mainFrame;
    }

    public abstract void update(List<InputProvider> inputs);
    public abstract void paint(Frame frame, Graphics2D graphics);

}
