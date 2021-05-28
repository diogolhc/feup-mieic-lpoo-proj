package controller.farm.element.building.edifice;

import controller.GameController;
import controller.command.Command;
import controller.command.controller_state.OpenPopupMenuCommand;
import controller.farm.element.building.BuildingController;
import controller.menu.builder.info.AlertMenuControllerBuilder;
import model.farm.building.Edifice;

public abstract class EdificeController extends BuildingController<Edifice> {
    protected final GameController controller;

    public EdificeController(GameController controller) {
        this.controller = controller;
    }

    @Override
    public Command getDemolishCommand(Edifice edifice) {
        return new OpenPopupMenuCommand(this.controller, new AlertMenuControllerBuilder(this.controller,
                edifice.getName() + " CANNOT BE DEMOLISHED"));
    }
}
