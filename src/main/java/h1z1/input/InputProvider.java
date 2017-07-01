package h1z1.input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An abstract class representing some kind of input device (such as a keyboard or controller).
 */
public abstract class InputProvider {

    // A map containing the last known state of a particular button
    private Map<InputButton, Boolean> buttonMap;
    // A map containing an action to take if a button is pressed (if any)
    private Map<InputButton, ButtonHandler> handlerMap;
    // A list of all buttons that have been pressed since the buffer was last asked for.
    // The takeButtonBuffer() method returns the values in this list and empties it.
    private List<InputButton> buttonBuffer = new ArrayList<>();

    public InputProvider() {
        buttonMap = new HashMap<>();
        handlerMap = new HashMap<>();
    }

    // A method that must be implemented in subclasses to get whether or not a button is currently down.
    public abstract boolean isPressed(InputButton button);

    public void poll() {}

    /**
     * A method to get whether or not an event has been attached to a particular button.
     * @param button the button to check for.
     * @return whether or not an event has been attached for a button press.
     */
    public boolean isButtonHandlerSet(InputButton button) {
        return handlerMap.containsKey(button);
    }

    /**
     * Attach an action to take when a particular button is pressed.
     * @param button the button to attach an action to
     * @param handler the action to take when the button is pressed
     */
    public void setButtonHandler(InputButton button, ButtonHandler handler) {
        handlerMap.put(button, handler);
    }

    /**
     * Clear all actions for all buttons.
     */
    public void clearButtonHandlers() {
        handlerMap.clear();
    }

    /**
     * Get the action attached for a particular button.
     * @param button the button to get the action for
     * @return the action associated with a button, or null if there isn't any associated action.
     */
    public ButtonHandler getButtonHandler(InputButton button) {
        return handlerMap.get(button);
    }

    // Subclasses may add buttons to the button buffer list
    protected void addButtonToBuffer(InputButton button){
        buttonBuffer.add(button);

    }

    /**
     * Empty the button buffer and return all its contents.
     * @return the buttons that have been pressed since this method was last called.
     */
    public List<InputButton> takeButtonBuffer(){
        List<InputButton> temp = new ArrayList<>(buttonBuffer);
        buttonBuffer.clear();
        return  temp;
    }

    /**
     * Manually set whether or not a button is currently down.
     * @param button the button to override the state for
     * @param state true if the button should be set to "pressed", otherwise false
     */
    public void setButtonState(InputButton button, Boolean state) {
        buttonMap.put(button, state);
   }

    /**
     * Get the state of a button from our state map.
     * @param button the button to get the state for
     * @return true if the button is currently thought to be down, otherwise false
     */
    public Boolean getButtonState(InputButton button) {
        return buttonMap.getOrDefault(button, false);
    }

}
