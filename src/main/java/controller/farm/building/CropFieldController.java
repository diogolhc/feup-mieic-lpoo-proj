package controller.farm.building;

import controller.GameController;
import controller.command.*;
import model.InGameTime;
import model.farm.building.crop_field.CropField;
import model.Position;
import model.farm.building.crop_field.state.NotPlanted;
import model.farm.building.crop_field.state.Planted;
import model.farm.building.crop_field.state.ReadyToHarvest;
import controller.menu.builder.CropFieldGrowingMenuBuilder;
import controller.menu.builder.HarvestMenuBuilder;
import controller.menu.builder.MenuBuilder;
import controller.menu.builder.PlantCropMenuBuilder;

public class CropFieldController extends BuildingController<CropField> {
    private final GameController controller;

    public CropFieldController(GameController controller) {
        this.controller = controller;
    }

    @Override
    public Command getInteractionCommand(CropField cropField) {
        MenuBuilder menuBuilder;

        if (cropField.getState() instanceof NotPlanted) {
            menuBuilder = new PlantCropMenuBuilder(this.controller, cropField);
        } else if (cropField.getState() instanceof Planted) {
            menuBuilder = new CropFieldGrowingMenuBuilder(this.controller, cropField);
        } else if (cropField.getState() instanceof ReadyToHarvest) {
            menuBuilder = new HarvestMenuBuilder(this.controller, cropField);
        } else {
            // This should never happen
            throw new RuntimeException(
                    "LOGIC ERROR: Unhandled CropFieldState: " + cropField.getState().getClass().toString());
        }

        return new OpenPopupMenuCommand(this.controller, menuBuilder.buildMenu(new Position(1, 1)));
    }

    public void reactTimePassed(CropField cropField, InGameTime elapsedTime) {
        cropField.setRemainingTime(cropField.getRemainingTime().subtract(elapsedTime));
    }

}
