package controller.menu.builder.building.crop_field;

import controller.GameController;
import controller.command.*;
import controller.command.farm.crop_field.HarvestCropCommand;
import controller.menu.element.ButtonController;
import controller.menu.builder.PopupMenuControllerBuilder;
import model.Position;
import model.farm.Inventory;
import model.farm.building.crop_field.CropField;
import model.menu.Button;
import model.menu.label.Label;

import java.util.List;

public class HarvestMenuControllerBuilder extends PopupMenuControllerBuilder {
    private Inventory inventory;
    private CropField cropField;

    public HarvestMenuControllerBuilder(GameController controller, Inventory inventory, CropField cropField) {
        super(controller);
        this.inventory = inventory;
        this.cropField = cropField;
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        addHarvestButton(buttons);

        return buttons;
    }

    private void addHarvestButton(List<ButtonController> buttons) {
        Button harvestButton = new Button(new Position(1, 7), "HARVEST");
        Command harvestButtonCommand = new CompoundCommand()
                .addCommand(new HarvestCropCommand(inventory, cropField))
                .addCommand(super.getClosePopupMenuCommand());
        buttons.add(new ButtonController(harvestButton, harvestButtonCommand));
    }

    @Override
    protected List<Label> getLabels() {
        List<Label> labels = super.getLabels();

        addCropTypeLabel(labels);
        addQuantityLabel(labels);

        return labels;
    }

    private void addCropTypeLabel(List<Label> labels) {
        labels.add(new Label(
                new Position(1, 4),
                () -> "CROP: " + cropField.getState().getCrop().getName()
        ));
    }

    private void addQuantityLabel(List<Label> labels) {
        labels.add( new Label(
                new Position(1, 5),
                () -> "QUANTITY: " + cropField.getHarvestAmount()
        ));
    }

    @Override
    protected int getHeight() {
        return 11;
    }

    @Override
    protected int getWidth() {
        return 22;
    }

    @Override
    protected String getTitle() {
        return "READY TO HARVEST";
    }
}
