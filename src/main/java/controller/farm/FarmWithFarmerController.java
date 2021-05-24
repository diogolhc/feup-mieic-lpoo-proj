package controller.farm;

import controller.GameController;
import controller.farm.building.CropFieldController;
import controller.farm.building.HouseController;
import controller.farm.building.MarketController;
import controller.farm.building.WarehouseController;
import gui.GUI;
import model.InGameTime;
import model.Position;
import model.farm.Farm;
import model.farm.building.BuildingSet;
import model.farm.building.CropField;
import viewer.GameViewer;
import viewer.farm.FarmWithFarmerViewer;

public class FarmWithFarmerController extends FarmController {
    public FarmWithFarmerController(FarmController farmController) {
        super(farmController);
    }

    public FarmWithFarmerController(Farm farm, GameController controller, long realSecToGameMinutesRate) {
        super(farm, controller, realSecToGameMinutesRate);
    }

    @Override
    public void reactKeyboard(GUI.ACTION action) {
        if (action == GUI.ACTION.INTERACT) reactInteraction();
        FarmerController farmerController = new FarmerController(this.farm);
        farmerController.doAction(action);
    }

    @Override
    public void reactMouseMovement(Position position) {}

    @Override
    public void reactMouseClick(Position position) {}

    private void reactInteraction() {
        Position farmerPosition = this.farm.getFarmer().getPosition();
        BuildingSet farmBuildings = this.farm.getBuildings();

        CropFieldController cropFieldController = new CropFieldController(this.controller, this.farm);
        for (CropField cropField: farmBuildings.getCropFields()) {
            cropFieldController.reactInteraction(cropField, farmerPosition);
        }

        HouseController houseController = new HouseController(this.controller, this.realTimeToInGameTimeConverter);
        houseController.reactInteraction(farmBuildings.getHouse(), farmerPosition);

        MarketController marketController = new MarketController(this.controller, this.farm);
        marketController.reactInteraction(farmBuildings.getMarket(), farmerPosition);

        WarehouseController warehouseController = new WarehouseController(this.controller, this.farm);
        warehouseController.reactInteraction(farmBuildings.getWarehouse(), farmerPosition);
    }

    @Override
    public void reactTimePassed(long elapsedTimeSinceLastFrame) {
        InGameTime elapsedTime = this.realTimeToInGameTimeConverter.convert(elapsedTimeSinceLastFrame);

        this.farm.setTime(this.farm.getTime().add(elapsedTime));

        CropFieldController cropFieldController = new CropFieldController(this.controller, this.farm);
        for (CropField cropField : this.farm.getBuildings().getCropFields()) {
            cropFieldController.reactTimePassed(cropField, elapsedTime);
        }

        this.weatherController.reactTimePassed(elapsedTime);
    }

    @Override
    public GameViewer getViewer() {
        return new FarmWithFarmerViewer(this.farm);
    }
}
