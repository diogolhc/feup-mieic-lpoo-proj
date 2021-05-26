package controller.menu.builder.market;

import controller.GameController;
import controller.command.Command;
import controller.command.OpenPopupMenuCommand;
import controller.command.SetControllerStateCommand;
import controller.farm.FarmController;
import controller.farm.FarmDemolishController;
import controller.farm.FarmNewBuildingController;
import controller.menu.ButtonController;
import controller.menu.builder.info.AlertMenuControllerBuilder;
import controller.menu.builder.PopupMenuControllerBuilder;
import model.Position;
import model.farm.Farm;
import model.farm.Livestock;
import model.farm.building.Buildable;
import model.farm.building.CropField;
import model.farm.building.Stockyard;
import model.menu.Button;
import model.menu.label.Label;

import java.util.ArrayList;
import java.util.List;

public class BuildMenuControllerBuilder extends PopupMenuControllerBuilder {
    private final Farm farm;
    private FarmController farmController;
    private List<Buildable> buildingCatalog;
    private static final Position NEW_BUILDING_STARTING_POSITION = new Position(1, 1);

    public BuildMenuControllerBuilder(GameController controller, FarmController farmController, Farm farm) {
        super(controller);
        this.farmController = farmController;
        this.farm = farm;
        this.buildingCatalog = new ArrayList<>();
        this.buildingCatalog.add(new CropField(NEW_BUILDING_STARTING_POSITION));
        for (Livestock livestockType: this.farm.getLivestockTypes()) {
            this.buildingCatalog.add(new Stockyard(NEW_BUILDING_STARTING_POSITION, livestockType));
        }
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        int y = 4;
        int x = 1;

        Button demolishButton = new Button(new Position(x, y), "DEMOLISH");
        Command demolishCommand;
        demolishCommand = new SetControllerStateCommand(this.controller, new FarmDemolishController(this.farmController));
        buttons.add(new ButtonController(demolishButton, demolishCommand));

        y += 4;

        for (Buildable building: this.buildingCatalog) {
            Button buildButton = new Button(new Position(x, y), building.getName());
            Command buildCommand;
            if (this.farm.getCurrency().canBuy(building.getBuildPrice())) {
                buildCommand = new SetControllerStateCommand(this.controller, new FarmNewBuildingController(
                        this.farmController, building));
            } else {
                buildCommand = new OpenPopupMenuCommand(this.controller,
                        new AlertMenuControllerBuilder(this.controller, "NOT ENOUGH MONEY"));
            }
            buttons.add(new ButtonController(buildButton, buildCommand));

            y += 4;
            if (y >= 16) {
                y = 8;
                x += 20;
            }
        }

        return buttons;
    }

    @Override
    protected List<Label> getLabels() {
        List<Label> labels = super.getLabels();

        int x = 14;
        int y = 9;
        for (Buildable building: this.buildingCatalog) {
            labels.add(new Label(
                    new Position(x, y),
                    () -> building.getBuildPrice().toString()
            ));

            y += 4;
            if (y >= 17) {
                y = 9;
                x += 17;
            }
        }

        return labels;
    }

    @Override
    protected int getHeight() {
        return 16;
    }

    @Override
    protected int getWidth() {
        return 37;
    }

    @Override
    protected String getTitle() {
        return "BUILD";
    }
}
