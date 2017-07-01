package h1z1.screens;

import h1z1.MainFrame;
import h1z1.game.GameState;
import h1z1.input.ButtonHandler;
import h1z1.input.InputButton;
import h1z1.input.InputProvider;
import h1z1.io.ResourceManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.List;

public class ExitGameScreenManager extends ScreenManager {

    public ExitGameScreenManager(MainFrame mainFrame) throws Exception {
        super(mainFrame);
    }

    @Override
    public void update(List<InputProvider> inputs) {
        System.exit(0);
    }

    @Override
    public void paint(Frame frame, Graphics2D graphics) {

    }

}
