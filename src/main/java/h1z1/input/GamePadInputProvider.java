package h1z1.input;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GamePadInputProvider extends InputProvider {

    private Controller controller;
    private Component.Identifier xId;
    private Component.Identifier yId;
    private Component.Identifier basicId;
    private Component.Identifier specialId;
    private Component.Identifier jumpId;
    private Component.Identifier blockId;
    private Map<InputButton, Boolean> buttonMap;

    public GamePadInputProvider(int controllerIndex) {
        ControllerEnvironment ce = ControllerEnvironment.getDefaultEnvironment();
        Controller[] allControllers = ce.getControllers();
        Arrays.stream(allControllers)
                .filter((controller) -> controller.getName().contains("Xbox"))
                .forEach((controller) -> System.out.println(controller.getName() + " (" + controller.getType().toString()));

        List<Controller> controllers = Arrays.stream(allControllers)
                .filter((controller) -> controller.getType() == Controller.Type.GAMEPAD)
                .collect(Collectors.toList());


        controller = controllers.get(controllerIndex);

        for (Component component : controller.getComponents()) {
            String componentName = component.getName().toLowerCase();
            if (componentName.equals("x") || componentName.equals("x axis")) xId = component.getIdentifier();
            if (componentName.equals("y") || componentName.equals("y axis")) yId = component.getIdentifier();
            if (componentName.equals("0") || componentName.equals("button 0")) basicId = component.getIdentifier();
            if (componentName.equals("1") || componentName.equals("button 1")) specialId = component.getIdentifier();
            if (componentName.equals("2") || componentName.equals("button 2")) jumpId = component.getIdentifier();
            if (componentName.equals("z") || componentName.equals("z axis")) blockId = component.getIdentifier();
        }

        if (xId == null || yId == null || basicId == null || specialId == null) {
            throw new UncheckedIOException(new IOException("Invalid controller."));
        }

        buttonMap = new HashMap<>();
    }

    @Override
    public void poll() {
        if (!controller.poll()) return;

        float x = controller.getComponent(xId).getPollData();
        float y = controller.getComponent(yId).getPollData();
        float basic = controller.getComponent(basicId).getPollData();
        float special = controller.getComponent(specialId).getPollData();
        float jump = controller.getComponent(jumpId).getPollData();
        float block = controller.getComponent(blockId).getPollData();

        boolean isLeft = x < -0.5;
        boolean isRight = x > 0.5;
        boolean isUp = y < -0.5;
        boolean isDown = y > 0.5;
        boolean isBasic = Math.abs(basic) > 0.5;
        boolean isSpecial = Math.abs(special) > 0.5;
        boolean isJump = Math.abs(jump) > 0.5;
        boolean isBlock = block > 0.5;


        if (isLeft) updateAndFireIfBound(InputButton.LEFT);
        else unsetButton(InputButton.LEFT);
        if (isRight) updateAndFireIfBound(InputButton.RIGHT);
        else unsetButton(InputButton.RIGHT);
        if (isUp) updateAndFireIfBound(InputButton.UP);
        else unsetButton(InputButton.UP);
        if (isDown) updateAndFireIfBound(InputButton.DOWN);
        else unsetButton(InputButton.DOWN);
        if (isBasic) updateAndFireIfBound(InputButton.BASIC);
        else unsetButton(InputButton.BASIC);
    }

    @Override
    public boolean isPressed(InputButton button) {

        return buttonMap.containsKey(button) && buttonMap.get(button);
    }

    private void updateAndFireIfBound(InputButton button) {
        boolean inMap = buttonMap.containsKey(button);
        if ((!inMap || !buttonMap.get(button))) {
            this.setButtonState(button, true);
            this.addButtonToBuffer(button);
            if (isButtonHandlerSet(button)) this.getButtonHandler(button).handlePress();
        }
        buttonMap.put(button, true);
    }

    private void unsetButton(InputButton button) {
        buttonMap.put(button, false);
        this.setButtonState(button, false);
    }
}
