package controller.menu.builder.info;

import controller.GameController;
import controller.command.Command;
import controller.command.CompoundCommand;
import controller.command.RemoveCropCommand;
import controller.menu.ButtonController;
import controller.menu.MenuController;
import controller.menu.PopupMenuControllerWithClosingCondition;
import controller.menu.builder.PopupMenuControllerBuilder;
import controller.menu.builder.crop_field.HarvestMenuControllerBuilder;
import gui.Color;
import model.Position;
import model.farm.Farm;
import model.farm.building.CropField;
import model.farm.building.crop_field_state.ReadyToHarvest;
import model.menu.Button;
import model.menu.Menu;
import model.menu.label.Label;
import model.menu.label.LabelText;

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
