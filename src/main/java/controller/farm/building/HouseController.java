package controller.farm.building;

import controller.GameController;
import controller.command.Command;
import controller.command.NoOperationCommand;
import controller.menu.MenuController;
import model.Position;
import model.farm.building.House;
import model.farm.building.crop_field.CropField;
import model.menu.Button;
import model.menu.Menu;
import viewer.menu.MenuViewer;

public class HouseController extends BuildingController<House> {
    private final GameController controller;

    public HouseController(GameController controller) {
        this.controller = controller;
    }

    @Override
    public Command getInteractionCommand(House house) {
        // TODO
        System.out.println("House interaction not implemented");
        return new NoOperationCommand();
    }
}
