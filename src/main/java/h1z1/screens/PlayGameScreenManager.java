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
import h1z1.game.Maze;


public class PlayGameScreenManager extends ScreenManager {

    private Maze maze = new Maze();
    public PlayGameScreenManager(MainFrame mainFrame) throws Exception {
        super(mainFrame);
    }

    @Override
    public void update(List<InputProvider> inputs) {

    }

    @Override
    public void paint(Frame frame, Graphics2D graphics) {
        for(int x = 0; x < Maze.WIDTH; x++){
            for(int y = 0; y < Maze.HEIGHT; y++) {
                boolean value = maze.getValue(x, y);
                if (value) {graphics.setColor(Color.RED);}
                else{
                    graphics.setColor(Color.WHITE);
                }
                    graphics.fillRect(Maze.OFFSET + Maze.SIZE * x,
                            Maze.OFFSET + Maze.SIZE * y,
                            Maze.SIZE,
                            Maze.SIZE);
                }

        }


    }

}
