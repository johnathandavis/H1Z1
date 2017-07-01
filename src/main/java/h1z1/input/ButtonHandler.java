package h1z1.input;

/**
 * A simple interface to implement for actions that should be taken
 * when a ButtonCombo or ButtonStreak is performed.
 */
public interface ButtonHandler {

    // The method that must be implemented and will be called
    void handlePress();

}
