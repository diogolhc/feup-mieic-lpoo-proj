package controller.farm.building;

import controller.GameController;
import controller.command.*;
import model.InGameTime;
import model.farm.building.crop_field.CropField;
import model.Position;
import model.farm.building.crop_field.state.NotPlanted;
import model.farm.building.crop_field.state.Planted;
import model.farm.building.crop_field.state.ReadyToHarvest;
import controller.menu.builder.CropFieldGrowingMenuControllerBuilder;
import controller.menu.builder.HarvestMenuControllerBuilder;
import controller.menu.builder.MenuControllerBuilder;
import controller.menu.builder.PlantCropMenuControllerBuilder;
import model.farm.crop.Crop;

import java.util.List;

public class CropFieldController extends BuildingController<CropField> {
    private final GameController controller;
    private List<Crop> crops;

    public CropFieldController(GameController controller, List<Crop> crops) {
        this.controller = controller;
        this.crops = crops;
    }

    @Override
    public Command getInteractionCommand(CropField cropField) {
        MenuControllerBuilder menuControllerBuilder;

        if (cropField.getState() instanceof NotPlanted) {
            menuControllerBuilder = new PlantCropMenuControllerBuilder(this.controller, crops, cropField);
        } else if (cropField.getState() instanceof Planted) {
            menuControllerBuilder = new CropFieldGrowingMenuControllerBuilder(this.controller, cropField);
        } else if (cropField.getState() instanceof ReadyToHarvest) {
            menuControllerBuilder = new HarvestMenuControllerBuilder(this.controller, cropField);
        } else {
            // This should never happen
            throw new RuntimeException(
                    "LOGIC ERROR: Unhandled CropFieldState: " + cropField.getState().getClass().toString());
        }

        return new SetControllerStateCommand(this.controller, menuControllerBuilder.buildMenu(new Position(1, 1)));
    }

    public void reactTimePassed(CropField cropField, InGameTime elapsedTime) {
        cropField.setRemainingTime(cropField.getRemainingTime().subtract(elapsedTime));
    }

}
