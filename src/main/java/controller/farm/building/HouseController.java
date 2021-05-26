package controller.farm.building;

import controller.GameController;
import controller.RealTimeToInGameTimeConverter;
import controller.command.Command;
import controller.command.OpenPopupMenuCommand;
import controller.farm.FarmController;
import controller.menu.builder.info.AlertMenuControllerBuilder;
import controller.menu.builder.PopupMenuControllerBuilder;
import controller.menu.builder.house.HouseMenuControllerBuilder;
import model.farm.building.House;

public class HouseController extends BuildingController<House> {
    private final GameController controller;
    private RealTimeToInGameTimeConverter timeConverter;

    public HouseController(GameController controller, RealTimeToInGameTimeConverter timeConverter) {
        this.controller = controller;
        this.timeConverter = timeConverter;
    }

    @Override
    public Command getInteractionCommand(House house) {
        if (this.controller.getGameControllerState() instanceof FarmController) {
            FarmController farmController = (FarmController) this.controller.getGameControllerState();
            PopupMenuControllerBuilder menuControllerBuilder = new HouseMenuControllerBuilder(
                    this.controller, farmController, timeConverter, house);
            return new OpenPopupMenuCommand(this.controller, menuControllerBuilder);
        } else {
            // This never happens because the interaction command is retrieved after
            // a farmer interaction (which happens in FarmWithFarmerController)
            throw new RuntimeException(
                    "LOGIC ERROR: Open market in invalid state: " + this.controller.getGameControllerState().getClass().toString());
        }
    }

    @Override
    public Command getDemolishCommand(House house) {
        return new OpenPopupMenuCommand(this.controller, new AlertMenuControllerBuilder(this.controller,
                "HOUSE CANNOT BE DEMOLISHED"));
    }
}
