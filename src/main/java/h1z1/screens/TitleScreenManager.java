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

public class TitleScreenManager extends ScreenManager {

    public enum MenuOption {
        VERSUS   ("Local",      GameState.PlayGame),
        EXIT     ("Exit",     GameState.Exit);

        MenuOption(String text, GameState newState) {
            this.text = text;
            this.newState = newState;
        }

        private String text;
        private GameState newState;

        public String getText() {
            return text;
        }
        public void setText(String text) {
            this.text = text;
        }

        public GameState getNewState() {
            return newState;
        }
        public void setNewState(GameState newState) {
            this.newState = newState;
        }
    }

    private int selectedOption = 0;
    private Image backgroundImage;

    public TitleScreenManager(MainFrame mainFrame) throws Exception {
        super(mainFrame);
        backgroundImage = ImageIO.read(ResourceManager.getRelativeFile("Placeholder.jpg"));
    }

    @Override
    public void update(List<InputProvider> inputs) {
        for (InputProvider input : inputs) {
            input.setButtonHandler(InputButton.UP, new ButtonHandler() {
                @Override
                public void handlePress() {
                    goUp();
                }
            });

            input.setButtonHandler(InputButton.DOWN, new ButtonHandler() {
                @Override
                public void handlePress() {
                    goDown();
                }
            });

            input.setButtonHandler(InputButton.BASIC, new ButtonHandler() {
                @Override
                public void handlePress() {
                    pressEnter();
                }
            });
        }
    }

    @Override
    public void paint(Frame frame, Graphics2D graphics) {

        int screenWidth = frame.getWidth();
        int screenHeight = frame.getHeight();

        graphics.drawImage(backgroundImage,
                0, 0, screenWidth, screenHeight,
                0, 0,
                backgroundImage.getWidth(null),
                backgroundImage.getHeight(null),null);

        drawMenuOptions(graphics, screenWidth, screenHeight);
    }

    private void drawMenuOptions(Graphics2D graphics, int screenWidth, int screenHeight) {

        int y = (int)(screenHeight * 0.4);
        int x = (int)(screenWidth * 0.2);
        int vBuffer = (int)(screenHeight * 0.05);
        int optionHeight = (int)(screenHeight * 0.1);
        int fontSize = 32;


        int optionIndex = 0;
        for (MenuOption option : MenuOption.values()) {

            // Draw each option
            graphics.setFont(new Font("Arial", 0, fontSize));
            if (optionIndex == selectedOption) {
                graphics.setColor(Color.yellow);
            } else {
                graphics.setColor(Color.red);
            }
            graphics.drawString(option.getText(), x, y);

            y = y + optionHeight + vBuffer;
            optionIndex++;
        }
    }

    private void goUp() {
        selectedOption = selectedOption == 0 ?
                MenuOption.values().length - 1
                : (selectedOption - 1);
    }

    private void goDown() {
        selectedOption = (selectedOption + 1) % MenuOption.values().length;
    }

    private void pressEnter() {
        MenuOption option = MenuOption.values()[selectedOption];
        GameState state = option.getNewState();
        getMainFrame().changeState(state);
    }
}
