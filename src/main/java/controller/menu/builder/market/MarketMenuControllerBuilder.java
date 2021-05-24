package controller.menu.builder.market;

import controller.GameController;
import controller.GameControllerState;
import controller.command.Command;
import controller.command.CompoundCommand;
import controller.command.SetControllerStateCommand;
import controller.command.SetTimeRateCommand;
import controller.farm.FarmController;
import controller.menu.ButtonController;
import controller.menu.builder.PopupMenuControllerBuilder;
import controller.menu.builder.house.StopSleepMenuControllerBuilder;
import model.Position;
import model.farm.Farm;
import model.farm.Inventory;
import model.farm.item.Item;
import model.menu.Button;
import model.menu.label.Label;

import java.util.ArrayList;
import java.util.List;

public class MarketMenuControllerBuilder extends PopupMenuControllerBuilder {
    private final Farm farm;
    private FarmController farmController;

    public MarketMenuControllerBuilder(GameController controller, Farm farm, FarmController farmController) {
        super(controller);
        this.farm = farm;
        this.farmController = farmController;
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        Button buildButton = new Button(new Position(1, 4), "BUILD");
        Command buildCommand = new SetControllerStateCommand(this.controller, this.getBuildMenuState());
        buttons.add(new ButtonController(buildButton, buildCommand));

        Button sellButton = new Button(new Position(1, 8), "SELL");
        Command sellCommand = new SetControllerStateCommand(this.controller, this.getSellMenuState());
        buttons.add(new ButtonController(sellButton, sellCommand));

        return buttons;
    }

    private GameControllerState getSellMenuState() {
        PopupMenuControllerBuilder popupMenuControllerBuilder;
        popupMenuControllerBuilder = new SellMenuControllerBuilder(this.controller, this.farm);
        return popupMenuControllerBuilder.buildMenu(new Position(1,1));
    }

    private GameControllerState getBuildMenuState() {
        PopupMenuControllerBuilder popupMenuControllerBuilder;
        popupMenuControllerBuilder = new BuildMenuControllerBuilder(this.controller, this.farmController, this.farm);
        return popupMenuControllerBuilder.buildMenu(new Position(1,1));
    }

    @Override
    protected int getHeight() {
        return 12;
    }

    @Override
    protected int getWidth() {
        return 18;
    }

    @Override
    protected String getTitle() {
        return "MARKET";
    }
}
