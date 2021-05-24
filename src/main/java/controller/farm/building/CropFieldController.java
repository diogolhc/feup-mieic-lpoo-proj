package controller.farm.building;

import controller.GameController;
import controller.command.*;
import model.InGameTime;
import model.farm.Farm;
import model.farm.building.CropField;
import model.Position;
import model.farm.building.crop_field_state.NotPlanted;
import model.farm.building.crop_field_state.Planted;
import model.farm.building.crop_field_state.ReadyToHarvest;
import controller.menu.builder.crop_field.CropFieldGrowingMenuControllerBuilder;
import controller.menu.builder.crop_field.HarvestMenuControllerBuilder;
import controller.menu.builder.MenuControllerBuilder;
import controller.menu.builder.crop_field.PlantCropMenuControllerBuilder;

public class CropFieldController extends BuildingController<CropField> {
    private final GameController controller;
    private Farm farm;

    public CropFieldController(GameController controller, Farm farm) {
        this.controller = controller;
        this.farm = farm;
    }

    @Override
    public Command getInteractionCommand(CropField cropField) {
        MenuControllerBuilder menuControllerBuilder;

        if (cropField.getState() instanceof NotPlanted) {
            menuControllerBuilder = new PlantCropMenuControllerBuilder(this.controller, this.farm.getCrops(), cropField);
        } else if (cropField.getState() instanceof Planted) {
            menuControllerBuilder = new CropFieldGrowingMenuControllerBuilder(this.controller, this.farm, cropField);
        } else if (cropField.getState() instanceof ReadyToHarvest) {
            menuControllerBuilder = new HarvestMenuControllerBuilder(this.controller, farm.getInventory(), cropField);
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
