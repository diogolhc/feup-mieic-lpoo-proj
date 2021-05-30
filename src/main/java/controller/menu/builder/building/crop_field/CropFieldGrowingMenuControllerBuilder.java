package controller.menu.builder.building.crop_field;

import controller.GameController;
import controller.command.Command;
import controller.command.CompoundCommand;
import controller.command.ConditionalCommand;
import controller.command.controller_state.SetControllerStateCommand;
import controller.command.farm.crop_field.RemoveCropCommand;
import controller.menu.element.ButtonController;
import controller.menu.MenuController;
import controller.menu.PopupMenuControllerWithTimePassedReaction;
import controller.menu.builder.MenuControllerBuilder;
import controller.menu.builder.PopupMenuControllerBuilder;
import model.Position;
import model.farm.Farm;
import model.farm.building.crop_field.CropField;
import model.farm.building.crop_field.state.ReadyToHarvest;
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
        MenuControllerBuilder harvestMenuControllerBuilder = new HarvestMenuControllerBuilder(
                this.controller, this.farm.getInventory(), this.cropField);

        MenuController harvestMenuController = harvestMenuControllerBuilder.buildMenuCentered(
                controller.getWindowWidth(), controller.getWindowHeight());

        Command closingCondition = new ConditionalCommand(() -> cropField.getState() instanceof ReadyToHarvest)
                .ifTrue(new SetControllerStateCommand(this.controller, harvestMenuController));

        return new PopupMenuControllerWithTimePassedReaction(menu, this.controller,
                this.controller.getGameControllerState(), closingCondition);
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        addRemoveCropCommand(buttons);

        return buttons;
    }

    private void addRemoveCropCommand(List<ButtonController> buttons) {
        Button removeCropButton = new Button(new Position(1, 8), "REMOVE CROP");
        Command removeCropButtonCommand = new CompoundCommand()
                .addCommand(new RemoveCropCommand(cropField))
                .addCommand(super.getClosePopupMenuCommand());
        buttons.add(new ButtonController(removeCropButton, removeCropButtonCommand));
    }

    @Override
    protected List<Label> getLabels() {
        List<Label> labels = super.getLabels();

        addCropTypeLabel(labels);
        addRemainingTimeLabel(labels);
        addQuantityLabel(labels);

        return labels;
    }

    private void addCropTypeLabel(List<Label> labels) {
        labels.add(new Label(
                new Position(1, 4),
                () -> "CROP: " + cropField.getState().getCrop().getName()
        ));
    }

    private void addRemainingTimeLabel(List<Label> labels) {
        labels.add(new Label(
                new Position(1, 5),
                () -> "REMAINING TIME: " + cropField.getRemainingTime().getTimerString()
        ));
    }

    private void addQuantityLabel(List<Label> labels) {
        labels.add( new Label(
                new Position(1, 6),
                () -> "QUANTITY: " + cropField.getHarvestAmount()
        ));
    }

    @Override
    protected int getHeight() {
        return 12;
    }

    @Override
    protected int getWidth() {
        return 23;
    }

    @Override
    public String getTitle() {
        return "GROWING";
    }
}
