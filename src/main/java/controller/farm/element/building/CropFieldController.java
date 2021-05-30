package controller.farm.element.building;

import controller.GameController;
import controller.GameControllerState;
import controller.command.*;
import controller.command.controller_state.OpenPopupMenuCommand;
import controller.command.controller_state.SetControllerStateCommand;
import controller.farm.FarmDemolishController;
import controller.farm.FarmWithFarmerController;
import controller.menu.builder.info.AlertMenuControllerBuilder;
import controller.menu.builder.PopupMenuControllerBuilder;
import model.InGameTime;
import model.farm.Farm;
import model.farm.building.crop_field.CropField;
import model.farm.building.crop_field.state.NotPlanted;
import model.farm.building.crop_field.state.Planted;
import model.farm.building.crop_field.state.ReadyToHarvest;
import controller.menu.builder.building.crop_field.CropFieldGrowingMenuControllerBuilder;
import controller.menu.builder.building.crop_field.HarvestMenuControllerBuilder;
import controller.menu.builder.building.crop_field.PlantCropMenuControllerBuilder;

public class CropFieldController extends BuildingController<CropField> {
    private final GameController controller;
    private Farm farm;

    public CropFieldController(GameController controller, Farm farm) {
        this.controller = controller;
        this.farm = farm;
    }

    @Override
    public Command getInteractionCommand(CropField cropField) {
        PopupMenuControllerBuilder menuControllerBuilder;

        if (cropField.getState() instanceof NotPlanted) {
            menuControllerBuilder = new PlantCropMenuControllerBuilder(this.controller, this.farm, cropField);
        } else if (cropField.getState() instanceof Planted) {
            menuControllerBuilder = new CropFieldGrowingMenuControllerBuilder(this.controller, this.farm, cropField);
        } else if (cropField.getState() instanceof ReadyToHarvest) {
            menuControllerBuilder = new HarvestMenuControllerBuilder(this.controller, this.farm.getInventory(), cropField);
        } else {
            // This should never happen
            throw new RuntimeException(
                    "LOGIC ERROR: Unhandled CropFieldState: " + cropField.getState().getClass().toString());
        }

        return new OpenPopupMenuCommand(this.controller, menuControllerBuilder);
    }

    @Override
    public Command getDemolishCommand(CropField cropField) {
        if (this.farm.getBuildings().getCropFields().size() == 1) {
            return new OpenPopupMenuCommand(this.controller, new AlertMenuControllerBuilder(this.controller,
                    "CANNOT DEMOLISH LAST\nCROPFIELD: MUST HAVE\nAT LEAST ONE"));
        } else {
            GameControllerState gameControllerState = this.controller.getGameControllerState();
            if (gameControllerState instanceof FarmDemolishController) {
                gameControllerState = new FarmWithFarmerController((FarmDemolishController) gameControllerState);
            }

            return new CompoundCommand()
                    .addCommand(() -> this.farm.getBuildings().removeCropField(cropField))
                    .addCommand(new SetControllerStateCommand(this.controller, gameControllerState));
        }
    }

    public void reactTimePassed(CropField cropField, InGameTime elapsedTime) {
        cropField.setRemainingTime(cropField.getRemainingTime().subtract(elapsedTime));
        cropField.changeHarvestAmount(this.farm.getCurrentWeather().getEffect(elapsedTime));
    }
}
