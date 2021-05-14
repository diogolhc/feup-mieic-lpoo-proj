package controller.menu.builder;

import controller.GameController;
import controller.command.CompoundCommand;
import controller.command.PlantCropCommand;
import model.Position;
import model.farm.Farm;
import model.farm.building.crop_field.CropField;
import model.farm.crop.Crop;
import model.menu.Button;
import model.menu.Menu;

import java.util.List;

public class PlantCropMenuBuilder extends PopupMenuBuilder {
    private List<Crop> crops;
    private CropField cropField;

    public PlantCropMenuBuilder(GameController controller, List<Crop> crops, CropField cropField) {
        super(controller);
        this.cropField = cropField;
        this.crops = crops;
    }

    @Override
    protected void addButtonsAndLabels(Menu menu) {
        super.addButtonsAndLabels(menu);

        int x = 1;
        int y = 5;
        for (Crop crop: this.crops) {
            Button plantCropButton = new Button(new Position(x, y), crop.getName());
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
