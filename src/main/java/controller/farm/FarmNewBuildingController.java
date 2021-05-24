package controller.farm;

import controller.menu.builder.AlertMenuControllerBuilder;
import gui.GUI;
import model.Position;
import model.farm.Currency;
import model.farm.building.Building;
import model.farm.building.CropField;
import viewer.GameViewer;
import viewer.farm.FarmNewBuildingViewer;

public class FarmNewBuildingController extends FarmController {
    private Building newBuilding;
    private Currency price;

    public FarmNewBuildingController(FarmController farmController, Building newBuilding, Currency price) {
        super(farmController);
        this.newBuilding = newBuilding;
        this.price = price;
    }

    @Override
    public void reactKeyboard(GUI.ACTION action) {
        if (action == GUI.ACTION.BACK) returnToFarmerController();
        if (action == GUI.ACTION.INTERACT) chooseBuildingPlace();
        NewBuildingController newBuildingController = new NewBuildingController(this.farm, this.newBuilding);
        newBuildingController.doAction(action);
    }

    @Override
    public void reactMouseMovement(Position position) {}

    @Override
    public void reactMouseClick(Position position) {}

    private void returnToFarmerController() {
        this.controller.setGameControllerState(new FarmWithFarmerController(this));
    }

    private void chooseBuildingPlace() {
        if (farm.getBuildings().isOccupied(this.newBuilding.getOccupiedRegion())) {
            this.controller.setGameControllerState(new AlertMenuControllerBuilder(this.controller,
                    "CHOSEN PLACE IS ALREADY OCCUPIED").buildMenu(new Position(1, 1)));
        } else {
            if (this.newBuilding instanceof CropField) {
                this.farm.getBuildings().addCropField((CropField) this.newBuilding);
            } else {
                // This should never happen
                throw new RuntimeException(
                        "LOGIC ERROR: Unhandled new building type: " + this.newBuilding.getClass().toString());
            }

            this.farm.setCurrency(this.farm.getCurrency().subtract(this.price));

            returnToFarmerController();
        }
    }

    @Override
    public GameViewer getViewer() {
        return new FarmNewBuildingViewer(this.farm, this.newBuilding);
    }
}
