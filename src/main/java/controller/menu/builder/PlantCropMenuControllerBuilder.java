package controller.menu.builder;

import controller.GameController;
import controller.command.Command;
import controller.command.CompoundCommand;
import controller.command.PlantCropCommand;
import controller.menu.ButtonController;
import model.Position;
import model.farm.building.crop_field.CropField;
import model.farm.crop.Crop;
import model.menu.Button;

import java.util.List;

public class PlantCropMenuControllerBuilder extends PopupMenuControllerBuilder {
    private List<Crop> crops;
    private CropField cropField;

    public PlantCropMenuControllerBuilder(GameController controller, List<Crop> crops, CropField cropField) {
        super(controller);
        this.cropField = cropField;
        this.crops = crops;
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        int x = 1;
        int y = 5;
        for (Crop crop: this.crops) {
            Button plantCropButton = new Button(new Position(x, y), crop.getName());
            Command plantCropButtonCommand = new CompoundCommand()
                    .addCommand(new PlantCropCommand(this.cropField, crop))
                    .addCommand(super.getClosePopupMenuCommand());
            buttons.add(new ButtonController(plantCropButton, plantCropButtonCommand));
            y+=4;
        }

        return buttons;
    }

    @Override
    protected int getHeight() {
        return 10;
    }

    @Override
    protected int getWidth() {
        return 20;
    }

    @Override
    protected String getTitle() {
        return "PLANT";
    }
}
