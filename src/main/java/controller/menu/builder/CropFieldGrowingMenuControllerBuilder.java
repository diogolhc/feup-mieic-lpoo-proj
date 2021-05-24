package controller.menu.builder;

import controller.GameController;
import controller.command.Command;
import controller.command.CompoundCommand;
import controller.command.RemoveCropCommand;
import controller.menu.ButtonController;
import controller.menu.MenuController;
import controller.menu.PopupMenuControllerWithClosingCondition;
import model.Position;
import model.farm.Farm;
import model.farm.building.CropField;
import model.farm.building.crop_field_state.ReadyToHarvest;
import model.menu.Button;
import model.menu.Menu;
import model.menu.label.Label;

import java.util.List;

public class CropFieldGrowingMenuControllerBuilder extends PopupMenuControllerBuilder {
    private Farm farm;
    private CropField cropField;

    public CropFieldGrowingMenuControllerBuilder(GameController controller, Farm farm, CropField cropField) {
        super(controller);
        this.farm = farm;
        this.cropField = cropField;
    }

    @Override
    protected MenuController getMenuController(Menu menu) {
        MenuController harvestMenuController = new HarvestMenuControllerBuilder(
                this.controller, this.farm.getInventory(), this.cropField).buildMenu(new Position(1, 1));

        Command closingCondition = () -> {
            if (cropField.getState() instanceof ReadyToHarvest) {
                this.controller.setGameControllerState(harvestMenuController);
            }
        };

        return new PopupMenuControllerWithClosingCondition(menu, this.controller,
                this.controller.getGameControllerState(), closingCondition);
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        Button removeCropButton = new Button(new Position(1, 7), "REMOVE CROP");
        Command removeCropButtonCommand = new CompoundCommand()
                .addCommand(new RemoveCropCommand(cropField))
                .addCommand(super.getClosePopupMenuCommand());
        buttons.add(new ButtonController(removeCropButton, removeCropButtonCommand));

        return buttons;
    }

    @Override
    protected List<Label> getLabels() {
        List<Label> labels = super.getLabels();

        labels.add(new Label(
                new Position(1, 4),
                () -> "CROP: " + cropField.getState().getCrop().getName()
        ));

        labels.add(new Label(
                new Position(1, 5),
                () -> "REMAINING TIME: " + cropField.getRemainingTime().toCountdownString()
        ));

        return labels;
    }

    @Override
    protected int getHeight() {
        return 11;
    }

    @Override
    protected int getWidth() {
        return 30;
    }

    @Override
    protected String getTitle() {
        return "GROWING";
    }
}
