package h1z1;

import h1z1.game.GameState;
import h1z1.input.GamePadInputProvider;
import h1z1.input.InputProvider;
import h1z1.input.KeyboardInputProvider;
import h1z1.screens.ScreenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * The main window for the Nova game. This class controls the game state,
 * global stateful variables such as the list of players and their inputs,
 * the FPS timer, and the double-buffer.
 */
public class MainFrame extends Frame{
    private Timer fpsTimer;
    private GameState gameState = GameState.TitleScreen;
    private List<InputProvider> allInputs = new ArrayList<InputProvider>();

    public MainFrame() {
        // Call our Frame superclass's constructor with the desired
        // Window title
        super("project: h1z1");

        // TODO: Replace this width and height with user-configurable options
        setSize(800, 600);
        setVisible(true);

        // Create a timer to repaint every 10ms, which is 100 FPS
        fpsTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainFrame.this.repaint();
            }
        });
        fpsTimer.start();

        // Release resources when window is naturally closed
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                fpsTimer.stop();
                dispose();
                System.exit(0);
            }
        });

        // Register the keyboard as the primary input
        //allInputs.add(defaultInput);
        try {
            allInputs.add(new GamePadInputProvider(0));
            // allInputs.add(new GamePadInputProvider(1));
        } catch (Throwable t) {
            allInputs.add(new KeyboardInputProvider());
        }

    }
    public void paint(Graphics g) {

        for (InputProvider inputProvider : allInputs) {
            inputProvider.poll();
        }

        // Create separate graphics to draw on first
        Image offscreen = this.createImage(this.getWidth(), this.getHeight());
        Graphics2D offscreenGraphics = (Graphics2D)offscreen.getGraphics();

        // Delegate the actual drawing of each screen to the
        // ScreenManager corresponding to whatever state the
        // game is currently in.
        ScreenManager screenManager = gameState.getScreenManagerInstance(this);
        screenManager.update(allInputs);
        screenManager.paint(this, offscreenGraphics);

        // Draw our offscreen image buffer to the main screen.
        // TODO: This approach is not enough, and we continue to see flickering issues on underpowered laptops.
        g.drawImage(offscreen, 0, 0, this);
    }

    /**
     * If a game screen manager wants to change the state (e.g. for menu navigation purposes),
     * they use this method.
     * @param gameState
     */
    public void changeState(GameState gameState) {
        this.gameState = gameState;

        // Clear all input hooks that may have been set
        // by a InputProvider
        for (InputProvider provider : allInputs) {
            provider.clearButtonHandlers();
        }
    }
}
