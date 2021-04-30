package controller.menu;

import controller.MouseClickObserver;
import model.Position;
import model.menu.Button;

public abstract class ButtonController implements MouseClickObserver {
    private Button button;

    public ButtonController(Button button) {
        this.button = button;
    }

    public abstract void doButtonAction();

    @Override
    public void notifyClick(Position mousePosition) {
        if (this.button.contains(mousePosition)) {
            this.doButtonAction();
        }
    }
}
