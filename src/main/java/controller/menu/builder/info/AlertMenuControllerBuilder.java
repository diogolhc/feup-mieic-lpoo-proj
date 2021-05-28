package controller.menu.builder.info;

import controller.GameController;
import controller.menu.element.ButtonController;
import model.Position;
import model.menu.Button;

import java.util.*;

public class AlertMenuControllerBuilder extends InfoMenuControllerBuilder {
    public AlertMenuControllerBuilder(GameController controller, String message) {
        super(controller, "ALERT", message);
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        int buttonX = this.getWidth()/2 - 2;
        int buttonY = this.getHeight() - 4;
        Button okButton = new Button(new Position(buttonX, buttonY), "OK");
        buttons.add(new ButtonController(okButton, super.getClosePopupMenuCommand()));

        return buttons;
    }

    @Override
    protected int getHeight() {
        return super.getHeight() + 4;
    }

    @Override
    protected int getWidth() {
        if (super.getWidth() >= 6) {
            return super.getWidth();
        } else {
            return 6;
        }
    }
}
