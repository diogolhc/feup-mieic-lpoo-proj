package controller.menu;

import model.Position;
import model.menu.Button;

public class ButtonController {
    public void reactMouseClick(Button button, Position mousePosition) {
        if (button.contains(mousePosition)) {
            button.getCommand().execute();
        }
    }

    public void reactMouseMovement(Button button, Position mousePosition) {
        if (button.contains(mousePosition)) {
            button.select();
        } else {
            button.unselect();
        }
    }
}
