package model.menu.builder;

import controller.GameController;
import controller.command.Command;
import controller.command.CompoundCommand;
import controller.command.PlantCropCommand;
import controller.command.SetControllerStateCommand;
import model.Position;
import model.farm.building.crop_field.CropField;
import model.farm.building.crop_field.crop.Crop;
import model.farm.building.crop_field.crop.Wheat;
import model.menu.Button;
import model.menu.Menu;

public class PlantCropMenuBuilder extends PopupMenuBuilder {
    private CropField cropField;

    public PlantCropMenuBuilder(GameController controller, CropField cropField) {
        super(controller);
        this.cropField = cropField;
    }

    @Override
    protected void addButtonsAndLabels(Menu menu) {
        super.addButtonsAndLabels(menu);

        Crop crops[] = {new Wheat()}; // TODO this is only for experimenting
        int x = 1;
        int y = 5;
        for (Crop crop: crops) {
            Button plantCropButton = new Button(new Position(x, y), crop.toString());
            plantCropButton.setCommand(new CompoundCommand()
                    .addCommand(new PlantCropCommand(this.cropField, crop))
                    .addCommand(super.getClosePopupMenuCommand())
            );
            menu.addButton(plantCropButton);
            x+=10;
        }
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
