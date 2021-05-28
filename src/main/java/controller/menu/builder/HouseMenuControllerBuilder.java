package controller.menu.builder;

import controller.GameController;
import controller.command.*;
import controller.command.controller_state.SetControllerStateCommand;
import controller.command.controller_state.SetTimeRateCommand;
import controller.command.game.SaveGameCommand;
import controller.farm.FarmController;
import controller.farm.FarmRestingController;
import controller.menu.element.ButtonController;
import model.Position;
import model.farm.building.Edifice;
import model.menu.Button;

import java.util.List;

public class HouseMenuControllerBuilder extends PopupMenuControllerBuilder {
    private Edifice house;
    private int sleepRateMultiplier;
    private FarmController farmController;

    public HouseMenuControllerBuilder(GameController controller, FarmController farmController, Edifice house,
                                      int sleepRateMultiplier) {
        super(controller);
        this.farmController = farmController;
        this.house = house;
        this.sleepRateMultiplier = sleepRateMultiplier;
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        Button restButton = new Button(new Position(1, 4), "REST");
        Command restCommand = new CompoundCommand()
                .addCommand(new SetTimeRateCommand(this.farmController.getTimeConverter(), this.sleepRateMultiplier))
                .addCommand(new SetControllerStateCommand(this.controller, new FarmRestingController(this.farmController)));

        buttons.add(new ButtonController(restButton, restCommand));

        Button saveGame = new Button(new Position(1,8), "SAVE GAME");
        // TODO where is the best place to put a SAVE_FILE_NAME constant?
        Command saveCommand = new SaveGameCommand(this.controller, this.farmController.getFarm(), "save.data");
        buttons.add(new ButtonController(saveGame, saveCommand));

        return buttons;
    }

    @Override
    protected int getHeight() {
        return 12;
    }

    @Override
    protected int getWidth() {
        return 13;
    }

    @Override
    protected String getTitle() {
        return house.getName();
    }
}
