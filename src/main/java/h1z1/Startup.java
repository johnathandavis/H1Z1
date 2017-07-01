package h1z1;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import org.apache.commons.io.FileUtils;

public class Startup {

    /**
     * Entry point to the Nova game. No command line arguments needed.
     * @param args command line arguments, if provided.
     */

    public static void main(String[] args) {

        // TODO: Remove this test code once we're 100% sure controller works as expected
        /*
        ControllerEnvironment ce = ControllerEnvironment.getDefaultEnvironment();
        for (Controller controller : ce.getControllers()) {
            if (controller.getType() == Controller.Type.GAMEPAD) {
                System.out.println(controller.getName());
                listenToGamepad(controller);
            }
        }
        */

        // Create the MainFrame (or Main Window) to hold our game.
        // The constructor for MainFrame starts all the resources we need,
        // so just creating a MainFrame object using new MainFrame() is enough.
        System.setProperty("sun.java2d.opengl","true");
        MainFrame mainFrame = new MainFrame();
    }

}
