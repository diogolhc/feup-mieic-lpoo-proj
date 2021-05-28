package controller.farm;

import controller.command.controller_state.OpenPopupMenuCommand;
import controller.farm.element.entity.keyboard_reactor.NewBuildingController;
import controller.menu.builder.PopupMenuControllerBuilder;
import controller.menu.builder.info.AlertMenuControllerBuilder;
import gui.GUI;
import model.farm.building.Buildable;
import model.farm.building.crop_field.CropField;
import model.farm.building.stockyard.Stockyard;
import viewer.GameViewer;
import viewer.farm.FarmNewBuildingViewer;

public class FarmNewBuildingController extends FarmController {
    private final Buildable newBuilding;

    public FarmNewBuildingController(FarmController farmController, Buildable newBuilding) {
        super(farmController);
        this.newBuilding = newBuilding;
    }

    @Override
    public void reactKeyboard(GUI.KEYBOARD_ACTION action) {
        if (action == GUI.KEYBOARD_ACTION.BACK) this.returnToFarmerController();
        if (action == GUI.KEYBOARD_ACTION.INTERACT) this.reactInteraction();
        NewBuildingController newBuildingController = new NewBuildingController(this.farm, this.newBuilding);
        newBuildingController.reactKeyboard(action);
    }

    private void returnToFarmerController() {
        this.controller.setGameControllerState(new FarmWithFarmerController(this));
    }

    private void reactInteraction() {
        if (this.farm.getBuildings().isOccupied(this.newBuilding.getOccupiedRegion())) {
            PopupMenuControllerBuilder occupiedAlert = new AlertMenuControllerBuilder(this.controller,
                    "CHOSEN PLACE IS ALREADY OCCUPIED");

            new OpenPopupMenuCommand(this.controller, occupiedAlert).execute();
        } else {
            if (this.newBuilding instanceof CropField) {
                this.farm.getBuildings().addCropField((CropField) this.newBuilding);
            } else if (this.newBuilding instanceof Stockyard) {
                this.farm.getBuildings().addStockyard((Stockyard) this.newBuilding);
            } else {
                // This should never happen
                throw new RuntimeException(
                        "LOGIC ERROR: Unhandled new building type: " + this.newBuilding.getClass().toString());
            }

            this.farm.getWallet().spend(this.newBuilding.getBuildPrice());

            this.returnToFarmerController();
        }
    }

    @Override
    public GameViewer getViewer() {
        return new FarmNewBuildingViewer(this.farm, this.newBuilding);
    }
}
