package controller.menu.builder.building.crop_field;

import controller.GameController;
import controller.command.Command;
import controller.command.CompoundCommand;
import controller.command.ConditionalCommand;
import controller.command.controller_state.OpenPopupMenuCommand;
import controller.command.farm.crop_field.PlantCropCommand;
import controller.menu.element.ButtonController;
import controller.menu.builder.info.AlertMenuControllerBuilder;
import controller.menu.builder.PopupMenuControllerBuilder;
import model.Position;
import model.farm.Farm;
import model.farm.building.crop_field.CropField;
import model.farm.data.item.Crop;
import model.menu.Button;
import model.menu.label.Label;

import java.util.List;

public class PlantCropMenuControllerBuilder extends PopupMenuControllerBuilder {
    private Farm farm;
    private CropField cropField;

    public PlantCropMenuControllerBuilder(GameController controller, Farm farm, CropField cropField) {
        super(controller);
        this.farm = farm;
        this.cropField = cropField;
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        addAllPlantCropButtons(buttons);

        return buttons;
    }

    private void addAllPlantCropButtons(List<ButtonController> buttons) {
        int x = 1;
        int y = 4;
        for (Crop crop: this.farm.getCropTypes()) {
            addPlantCropButton(buttons, new Position(x, y), crop);

            if (y == 4) {
                y += 7;
            } else {
                y = 4;
                x += 17;
            }
        }
    }

    private void addPlantCropButton(List<ButtonController> buttons, Position position, Crop crop) {
        PopupMenuControllerBuilder notEnoughMoneyAlert = new AlertMenuControllerBuilder(this.controller,
                "NOT ENOUGH MONEY");

        Button plantCropButton = new Button(position, crop.getName(), 9);
        Command plantCropButtonCommand = new ConditionalCommand(() -> this.farm.getWallet().canBuy(crop.getPlantPrice()))
                .ifTrue(new CompoundCommand()
                        .addCommand(new PlantCropCommand(this.farm.getWallet(), this.cropField, crop))
                        .addCommand(super.getClosePopupMenuCommand()))
                .ifFalse(new OpenPopupMenuCommand(this.controller, notEnoughMoneyAlert));

        buttons.add(new ButtonController(plantCropButton, plantCropButtonCommand));
    }

    @Override
    protected List<Label> getLabels() {
        List<Label> labels = super.getLabels();

        addAllCropTypesLabels(labels);

        return labels;
    }

    private void addAllCropTypesLabels(List<Label> labels) {
        int x = 1;
        int y = 4;
        for (Crop crop: this.farm.getCropTypes()) {
            addCropTypeLabels(labels, new Position(x, y), crop);

            if (y == 4) {
                y += 7;
            } else {
                y = 4;
                x += 17;
            }
        }
    }

    private void addCropTypeLabels(List<Label> labels, Position position, Crop crop) {
        addHarvestInfoLabel(labels, position.getTranslated(new Position(0, 4)), crop);
        addSeedCostLabels(labels, position, crop);
    }

    private void addSeedCostLabels(List<Label> labels, Position position, Crop crop) {
        position = position.getTranslated(new Position(10, 0));
        labels.add(new Label(position, "SEED"));

        position = position.getDown();
        labels.add(new Label(position, "COST"));

        position = position.getDown();
        labels.add(new Label(position,
                () -> String.format("%1$4s", crop.getPlantPrice().toString())
        ));
    }

    private void addHarvestInfoLabel(List<Label> labels, Position position, Crop crop) {
        labels.add(new Label(position,
                () -> String.format("%1$s %2$3s",
                        crop.getGrowTime().getTimerString(),
                        "x" + crop.getBaseHarvestAmount()
                )
        ));
    }

    @Override
    protected int getHeight() {
        return 17;
    }

    @Override
    protected int getWidth() {
        return 33;
    }

    @Override
    public String getTitle() {
        return "PLANT";
    }
}
