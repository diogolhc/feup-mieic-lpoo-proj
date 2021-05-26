package controller.menu.builder.crop_field;

import controller.GameController;
import controller.command.Command;
import controller.command.CompoundCommand;
import controller.command.OpenPopupMenuCommand;
import controller.command.PlantCropCommand;
import controller.menu.ButtonController;
import controller.menu.builder.info.AlertMenuControllerBuilder;
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
        int y = 4;
        for (Crop crop: this.farm.getCrops()) {
            Button plantCropButton = new Button(new Position(x, y), crop.getName(), 9);
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

            if (y == 4) {
                y += 7;
            } else {
                y = 4;
                x += COLUMN_WIDTH + 3;
            }
        }

        return buttons;
    }

    @Override
    protected List<Label> getLabels() {
        List<Label> labels = super.getLabels();

        int x = 1;
        int y = 4;
        for (Crop crop: this.farm.getCrops()) {
            labels.add(new Label(new Position(x + 10, y), "SEED"));
            labels.add(new Label(new Position(x + 10, y+1), "COST"));
            labels.add(new Label(
                    new Position(x + 10, y+2),
                    () -> String.format("%1$4s", crop.getPlantPrice().toString())
            ));
            labels.add(new Label(
                    new Position(x, y+4),
                    () -> String.format("%1$s %2$3s",
                            crop.getGrowTime().toCountdownString(),
                            "x" + crop.getBaseHarvestAmount())
            ));

            if (y == 4) {
                y += 7;
            } else {
                y = 4;
                x += COLUMN_WIDTH + 3;
            }
        }

        return labels;
    }

    @Override
    protected int getHeight() {
        return 17;
    }

    @Override
    protected int getWidth() {
        return this.farm.getCrops().size()/2 * COLUMN_WIDTH + 5;
    }

    @Override
    protected String getTitle() {
        return "PLANT";
    }
}
