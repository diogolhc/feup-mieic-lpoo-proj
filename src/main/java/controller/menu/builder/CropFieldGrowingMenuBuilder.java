package controller.menu.builder;

import controller.GameController;
import controller.command.Command;
import controller.command.CompoundCommand;
import controller.command.RemoveCropCommand;
import controller.menu.ButtonController;
import controller.menu.MenuController;
import controller.menu.PopupMenuController;
import controller.menu.PopupMenuControllerWithClosingCondition;
import model.Position;
import model.farm.building.crop_field.CropField;
import model.farm.building.crop_field.state.ReadyToHarvest;
import model.menu.Button;
import model.menu.Menu;
import model.menu.label.Label;

import java.util.List;

public class CropFieldGrowingMenuBuilder extends PopupMenuBuilder {
    private CropField cropField;

    public CropFieldGrowingMenuBuilder(GameController controller, CropField cropField) {
        super(controller);
        this.cropField = cropField;
    }

    @Override
    protected MenuController getMenuController(Menu menu) {
        // TODO make this its own class
        Command closingCondition = () -> {
            if (cropField.getState() instanceof ReadyToHarvest) {
                MenuController newController = new HarvestMenuBuilder(this.controller, cropField)
                        .buildMenu(new Position(1, 1));
                this.controller.setGameControllerState(newController);
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
