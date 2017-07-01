package h1z1.input;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

// TODO: Clean up this class, it got a bit messy
// TODO: Change keyboard bindings to a configurable value

/**
 * A class to handle keyboard inputs.
 */
public class KeyboardInputProvider extends InputProvider {

    private static Map<Integer, Boolean> keys;

    public KeyboardInputProvider() {
        if (keys != null) return;
        keys = new HashMap<Integer, Boolean>();
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                switch (e.getID()) {
                    case KeyEvent.KEY_PRESSED:
                        keys.put(e.getKeyCode(), true);
                        KeyboardInputProvider.this.setButtonState(getButtonForKeyCode(e.getKeyCode()), true);
                        if (KeyboardInputProvider.this.isButtonHandlerSet(getButtonForKeyCode(e.getKeyCode()))) {
                            KeyboardInputProvider.this.getButtonHandler(getButtonForKeyCode(e.getKeyCode())).handlePress();
                        }
                        break;
                    case KeyEvent.KEY_RELEASED:
                        keys.put(e.getKeyCode(), false);
                        KeyboardInputProvider.this.setButtonState(getButtonForKeyCode(e.getKeyCode()), false);
                        KeyboardInputProvider.this.addButtonToBuffer(getButtonForKeyCode(e.getKeyCode()));

                        break;
                }
                KeyboardFocusManager.getCurrentKeyboardFocusManager().postProcessKeyEvent(e);
                return false;
            }
        });
    }

    @Override
    public boolean isPressed(InputButton button) {
        Integer keyCode = getKeyCodeForButton(button);
        boolean pressed = false;
        if (keys.containsKey(keyCode)) {
            pressed = keys.get(keyCode);
        }
        return pressed;
    }

    private static Integer getKeyCodeForButton(InputButton button) {
        switch (button) {
            case UP:
                return 87; // W
            case DOWN:
                return 83; // S
            case LEFT:
                return 65; // A
            case RIGHT:
                return 68; // D
            case BASIC:
                return 74; // J
            case SPECIAL:
                return 75; // K
            case JUMP:
                return 32; // Space
            case BLOCK:
                return 76; //L
            default:
                return null;
        }
    }

    private static InputButton getButtonForKeyCode(int keyCode) {
        switch (keyCode) {
            case 87:
                return InputButton.UP;
            case 83:
                return InputButton.DOWN;
            case 65:
                return InputButton.LEFT;
            case 68:
                return InputButton.RIGHT;
            case 74:
                return InputButton.BASIC;
            case 75:
                return InputButton.SPECIAL;
            case 32:
                return InputButton.JUMP;
            case 76:
                return InputButton.BLOCK;
            default:
                return null;
        }
    }
}
