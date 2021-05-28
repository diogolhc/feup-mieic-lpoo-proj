package controller.menu.builder.building.market;

import controller.GameController;
import controller.command.*;
import controller.command.controller_state.OpenPopupMenuCommand;
import controller.farm.FarmController;
import controller.menu.element.ButtonController;
import controller.menu.builder.PopupMenuControllerBuilder;
import model.Position;
import model.farm.Farm;
import model.menu.Button;

import java.util.List;

public class MarketMenuControllerBuilder extends PopupMenuControllerBuilder {
    private final Farm farm;
    private FarmController farmController;

    public MarketMenuControllerBuilder(GameController controller, FarmController farmController) {
        super(controller);
        this.farm = farmController.getFarm();
        this.farmController = farmController;
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        addBuildButton(buttons);
        addSellButton(buttons);

        return buttons;
    }

    private void addBuildButton(List<ButtonController> buttons) {
        PopupMenuControllerBuilder buildMenu = new BuildMenuControllerBuilder(this.controller, this.farmController);

        Button buildButton = new Button(new Position(1, 4), "BUILD");
        Command buildCommand = new CompoundCommand()
                .addCommand(super.getClosePopupMenuCommand())
                .addCommand(new OpenPopupMenuCommand(this.controller, buildMenu));
        buttons.add(new ButtonController(buildButton, buildCommand));
    }

    private void addSellButton(List<ButtonController> buttons) {
        PopupMenuControllerBuilder sellMenu = new SellMenuControllerBuilder(this.controller, this.farm);

        Button sellButton = new Button(new Position(1, 8), "SELL");
        Command sellCommand = new CompoundCommand()
                .addCommand(super.getClosePopupMenuCommand())
                .addCommand(new OpenPopupMenuCommand(this.controller, sellMenu));
        buttons.add(new ButtonController(sellButton, sellCommand));
    }

    @Override
    protected int getHeight() {
        return 12;
    }

    @Override
    protected int getWidth() {
        return 12;
    }

    @Override
    protected String getTitle() {
        return "MARKET";
    }
}
