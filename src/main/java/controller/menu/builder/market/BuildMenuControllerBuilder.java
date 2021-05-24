package controller.menu.builder.market;

import controller.GameController;
import controller.GameControllerState;
import controller.command.Command;
import controller.command.OpenPopupMenuCommand;
import controller.command.SellItemCommand;
import controller.command.SetControllerStateCommand;
import controller.farm.FarmController;
import controller.farm.FarmNewBuildingController;
import controller.menu.ButtonController;
import controller.menu.builder.AlertMenuControllerBuilder;
import controller.menu.builder.PopupMenuControllerBuilder;
import model.Position;
import model.farm.Currency;
import model.farm.Farm;
import model.farm.building.CropField;
import model.farm.item.Item;
import model.menu.Button;
import model.menu.label.Label;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildMenuControllerBuilder extends PopupMenuControllerBuilder {
    private final Farm farm;
    private FarmController farmController;

    // TODO should this be here?
    private static final Currency CROPFIELD_BUILD_PRICE = new Currency(1500);

    public BuildMenuControllerBuilder(GameController controller, FarmController farmController, Farm farm) {
        super(controller);
        this.farmController = farmController;
        this.farm = farm;
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        int y = 4;
        int x = 1;

        Button buildCropFieldButton = new Button(new Position(x, y), "CROPFIELD");
        Command buildCropFieldCommand;
        if (this.farm.getCurrency().canBuy(CROPFIELD_BUILD_PRICE)) {
            buildCropFieldCommand = new SetControllerStateCommand(this.controller, new FarmNewBuildingController(
                    this.farmController, new CropField(new Position(1, 1)), CROPFIELD_BUILD_PRICE));
        } else {
            buildCropFieldCommand = new OpenPopupMenuCommand(this.controller,
                    new AlertMenuControllerBuilder(this.controller, "NOT ENOUGH MONEY"));
        }

        buttons.add(new ButtonController(buildCropFieldButton, buildCropFieldCommand));

        //y+=5;

        return buttons;
    }

    @Override
    protected List<Label> getLabels() {
        List<Label> labels = super.getLabels();

        int y = 5;
        labels.add(new Label(
                new Position(14, y),
                () -> CROPFIELD_BUILD_PRICE.toString()
        ));
        //y += 4;

        return labels;
    }

    @Override
    protected int getHeight() {
        return 8;
    }

    @Override
    protected int getWidth() {
        return 21;
    }

    @Override
    protected String getTitle() {
        return "BUILD";
    }
}
