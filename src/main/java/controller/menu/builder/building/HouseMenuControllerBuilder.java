package controller.menu.builder.building;

import controller.GameController;
import controller.command.*;
import controller.command.controller_state.SetControllerStateCommand;
import controller.command.controller_state.SetTimeRateCommand;
import controller.command.game.SaveGameCommand;
import controller.farm.FarmController;
import controller.farm.FarmRestingController;
import controller.menu.builder.PopupMenuControllerBuilder;
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

        addRestButton(buttons);
        addSaveGameButton(buttons);

        return buttons;
    }

    private void addRestButton(List<ButtonController> buttons) {
        FarmController restController = new FarmRestingController(this.farmController);

        Button restButton = new Button(new Position(1, 4), "REST");
        Command restCommand = new CompoundCommand()
                .addCommand(new SetTimeRateCommand(this.farmController.getTimeConverter(), this.sleepRateMultiplier))
                .addCommand(new SetControllerStateCommand(this.controller, restController));
        buttons.add(new ButtonController(restButton, restCommand));
    }

    private void addSaveGameButton(List<ButtonController> buttons) {
        Button saveGameButton = new Button(new Position(1,8), "SAVE GAME");
        Command saveCommand = new SaveGameCommand(this.controller, this.farmController.getFarm(), "save.data");
        buttons.add(new ButtonController(saveGameButton, saveCommand));
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
