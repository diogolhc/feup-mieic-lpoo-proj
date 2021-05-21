package controller.farm.building;

import controller.GameController;
import controller.command.Command;
import controller.command.SetControllerStateCommand;
import controller.menu.builder.SleepMenuControllerBuilder;
import controller.menu.builder.MenuControllerBuilder;
import model.Position;
import model.farm.building.House;

public class HouseController extends BuildingController<House> {
    private final GameController controller;

    public HouseController(GameController controller) {
        this.controller = controller;
    }

    @Override
    public Command getInteractionCommand(House house) {
        MenuControllerBuilder menuControllerBuilder;
        menuControllerBuilder = new SleepMenuControllerBuilder(this.controller, house);

        return new SetControllerStateCommand(this.controller, menuControllerBuilder.buildMenu(new Position(1,1)));
    }
}
