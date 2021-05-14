package controller.menu;

import controller.command.Command;
import model.Position;
import model.menu.Button;

public class ButtonController {
    private final Button button;
    private final Command command;

    public ButtonController(Button button, Command command) {
        this.button = button;
        this.command = command;
    }

    public void reactMouseClick(Position mousePosition) {
        if (this.button.contains(mousePosition)) {
            this.command.execute();
        }
    }

    public void reactMouseMovement(Position mousePosition) {
        if (this.button.contains(mousePosition)) {
            this.button.select();
        } else {
            this.button.unselect();
        }
    }

    public Button getButton() {
        return this.button;
    }
}
