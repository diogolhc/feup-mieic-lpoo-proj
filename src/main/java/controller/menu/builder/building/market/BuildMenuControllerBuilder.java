package controller.menu.builder.building.market;

import controller.GameController;
import controller.command.Command;
import controller.command.ConditionalCommand;
import controller.command.controller_state.OpenPopupMenuCommand;
import controller.command.controller_state.SetControllerStateCommand;
import controller.farm.FarmController;
import controller.farm.FarmDemolishController;
import controller.farm.FarmNewBuildingController;
import controller.menu.element.ButtonController;
import controller.menu.builder.info.AlertMenuControllerBuilder;
import controller.menu.builder.PopupMenuControllerBuilder;
import model.Position;
import model.farm.Farm;
import model.farm.data.Livestock;
import model.farm.building.Buildable;
import model.farm.building.crop_field.CropField;
import model.farm.building.stockyard.Stockyard;
import model.menu.Button;
import model.menu.label.Label;

import java.util.ArrayList;
import java.util.List;

public class BuildMenuControllerBuilder extends PopupMenuControllerBuilder {
    private final Farm farm;
    private FarmController farmController;
    private List<Buildable> buildingCatalog;
    private static final Position NEW_BUILDING_STARTING_POSITION = new Position(1, 1);

    public BuildMenuControllerBuilder(GameController controller, FarmController farmController) {
        super(controller);
        this.farmController = farmController;
        this.farm = farmController.getFarm();

        this.buildingCatalog = new ArrayList<>();
        this.buildingCatalog.add(new CropField(NEW_BUILDING_STARTING_POSITION));
        for (Livestock livestockType: this.farm.getLivestockTypes()) {
            this.buildingCatalog.add(new Stockyard(NEW_BUILDING_STARTING_POSITION, livestockType));
        }
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        addAllBuildButtons(buttons);
        addDemolishButton(buttons, new Position(24, 4));

        return buttons;
    }

    private void addAllBuildButtons(List<ButtonController> buttons) {
        int y = 4;
        for (Buildable building: this.buildingCatalog) {
            addBuildBuildingButton(buttons, new Position(1, y), building);
            y += 4;
        }
    }

    private void addBuildBuildingButton(List<ButtonController> buttons, Position position, Buildable building) {
        FarmController buildController = new FarmNewBuildingController(this.farmController, building);
        PopupMenuControllerBuilder notEnoughMoneyAlert = new AlertMenuControllerBuilder(this.controller,
                "NOT ENOUGH MONEY");

        Button buildButton = new Button(position, building.getName());
        Command buildCommand = new ConditionalCommand(() -> this.farm.getWallet().canBuy(building.getBuildPrice()))
                .ifTrue(new SetControllerStateCommand(this.controller, buildController))
                .ifFalse(new OpenPopupMenuCommand(this.controller, notEnoughMoneyAlert));
        buttons.add(new ButtonController(buildButton, buildCommand));
    }

    private void addDemolishButton(List<ButtonController> buttons, Position position) {
        FarmController demolishController = new FarmDemolishController(this.farmController);

        Button demolishButton = new Button(position, "DEMOLISH");
        Command demolishCommand;
        demolishCommand = new SetControllerStateCommand(this.controller, demolishController);
        buttons.add(new ButtonController(demolishButton, demolishCommand));
    }

    @Override
    protected List<Label> getLabels() {
        List<Label> labels = super.getLabels();

        addAllBuildPriceLabels(labels);

        return labels;
    }

    private void addAllBuildPriceLabels(List<Label> labels) {
        int y = 5;
        for (Buildable building: this.buildingCatalog) {
            addBuildPriceLabel(labels, new Position(16, y), building);

            y += 4;
        }
    }

    private void addBuildPriceLabel(List<Label> labels, Position position, Buildable building) {
        labels.add(new Label(position, () -> building.getBuildPrice().toString()));
    }

    @Override
    protected int getHeight() {
        return 16;
    }

    @Override
    protected int getWidth() {
        return 35;
    }

    @Override
    protected String getTitle() {
        return "BUILD";
    }
}
