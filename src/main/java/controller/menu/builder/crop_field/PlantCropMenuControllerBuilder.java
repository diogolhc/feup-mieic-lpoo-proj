package controller.menu.builder.crop_field;

import controller.GameController;
import controller.command.Command;
import controller.command.CompoundCommand;
import controller.command.OpenPopupMenuCommand;
import controller.command.PlantCropCommand;
import controller.menu.ButtonController;
import controller.menu.builder.AlertMenuControllerBuilder;
import controller.menu.builder.PopupMenuControllerBuilder;
import model.Position;
import model.farm.Farm;
import model.farm.building.CropField;
import model.farm.item.Crop;
import model.menu.Button;
import model.menu.label.Label;

import java.util.List;

public class PlantCropMenuControllerBuilder extends PopupMenuControllerBuilder {
    private Farm farm;
    private CropField cropField;
    private static int COLUMN_WIDTH = 14;

    public PlantCropMenuControllerBuilder(GameController controller, Farm farm, CropField cropField) {
        super(controller);
        this.farm = farm;
        this.cropField = cropField;
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        int x = 1;
        int y = 5;
        for (Crop crop: this.farm.getCrops()) {
            Button plantCropButton = new Button(new Position(x, y), crop.getName());
            Command plantCropButtonCommand;
            if (this.farm.getCurrency().canBuy(crop.getPlantPrice())) {
                plantCropButtonCommand = new CompoundCommand()
                        .addCommand(new PlantCropCommand(this.farm, this.cropField, crop))
                        .addCommand(super.getClosePopupMenuCommand());
            } else {
                plantCropButtonCommand = new OpenPopupMenuCommand(this.controller,
                        new AlertMenuControllerBuilder(this.controller, "NOT ENOUGH MONEY"));
            }


            buttons.add(new ButtonController(plantCropButton, plantCropButtonCommand));

            if (y == 5) {
                y += 4;
            } else {
                y = 5;
                x += COLUMN_WIDTH + 2;
            }
        }

        return buttons;
    }

    @Override
    protected List<Label> getLabels() {
        List<Label> labels = super.getLabels();

        int x = 11;
        int y = 6;
        for (Crop crop: this.farm.getCrops()) {
            labels.add(new Label(
                    new Position(x, y),
                    () -> String.format("%1$3s", crop.getPlantPrice().toString())
            ));

            if (y == 6) {
                y += 4;
            } else {
                y = 6;
                x += COLUMN_WIDTH + 2;
            }
        }

        return labels;
    }

    @Override
    protected int getHeight() {
        return 13;
    }

    @Override
    protected int getWidth() {
        return this.farm.getCrops().size()/2 * COLUMN_WIDTH + 4;
    }

    @Override
    protected String getTitle() {
        return "PLANT";
    }
}
