package controller.menu;

import controller.MouseObserver;
import model.Position;
import model.menu.Button;

public abstract class ButtonController implements MouseObserver {
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

    @Override
    public void notifyPosition(Position mousePosition) {
        if (this.button.contains(mousePosition)) {
            this.button.select();
        } else {
            this.button.unselect();
        }
    }
}
