package h1z1.game;

import h1z1.MainFrame;
import h1z1.screens.*;
import lombok.SneakyThrows;

/**
 * An enumerated list of the different global states the game can be in.
 * Each state is also linked to a subclass of ScreenManager. An instance of
 * the specified class will be used to delegate control of the screen.
 */
public enum GameState {
    TitleScreen(TitleScreenManager.class),
    PlayGame(PlayGameScreenManager.class),
    Exit(ExitGameScreenManager.class);


    /**
     * The default and only constructor for this enum.
     * Each item in the enum must specify a ScreenManager class.
     *
     * @param screenManager
     */
    GameState(Class<? extends ScreenManager> screenManager) {
        this.screenManager = screenManager;
    }

    private Class<? extends ScreenManager> screenManager;
    private ScreenManager screenManagerInstance = null;

    /**
     * Return an instance of the specified subclass of ScreenManager
     * for this particular GameState item. If an instance of the class
     * has not yet been created, it is lazily created when this method
     * is first called.
     *
     * @param mainFrame the MainFrame object that will 'own' the ScreenManager
     * @return
     */
    @SneakyThrows
    public ScreenManager getScreenManagerInstance(MainFrame mainFrame) {

        // Only create the new instance if we haven't already done so
        if (screenManagerInstance == null) {
            // Get the first (and only) constructor of this ScreenManager instance,
            // and pass it the MainFrame object that should own the ScreenManager
            screenManagerInstance = (ScreenManager) screenManager.getConstructors()[0].newInstance(mainFrame);
        }
        return screenManagerInstance;
    }

    public void setScreenManagerInstance(ScreenManager screenManager) {
        this.screenManagerInstance = screenManager;

    }

}
