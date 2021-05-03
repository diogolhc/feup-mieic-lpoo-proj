package controller.farm.building;

import controller.GameController;
import controller.command.*;
import controller.farm.building.BuildingController;
import controller.menu.MenuController;
import model.farm.building.crop_field.CropField;
import model.Position;
import model.farm.building.crop_field.crop.Crop;
import model.farm.building.crop_field.crop.Wheat;
import model.farm.building.crop_field.state.NotPlanted;
import model.farm.building.crop_field.state.Planted;
import model.farm.building.crop_field.state.ReadyToHarvest;
import model.menu.Button;
import model.menu.Menu;
import model.menu.label.Label;
import model.menu.label.LabelText;
import viewer.GameViewer;
import viewer.menu.MenuViewer;

public class CropFieldController extends BuildingController<CropField> {
    private final GameController controller;

    public CropFieldController(GameController controller) {
        this.controller = controller;
    }

    private Menu getNotPlantedMenu(CropField cropField) {
        Menu menu = new Menu("PLANT", new Position(1, 1), 20, 10);
        Button closeMenuButton = new Button(new Position(20-3, 0), "X");
        closeMenuButton.setCommand(new SetControllerStateCommand(controller, controller.getGameControllerState()));
        menu.addButton(closeMenuButton);

        Crop crops[] = {new Wheat()}; // TODO this is only for experimenting
        int x = 1;
        int y = 5;
        for (Crop crop: crops) {
            Button plantCropButton = new Button(new Position(x, y), crop.toString());
            Command plantCropButtonCommand = new CompoundCommand()
                    .addCommand(new PlantCropCommand(cropField, crop))
                    .addCommand(new SetControllerStateCommand(controller, controller.getGameControllerState()));
            plantCropButton.setCommand(plantCropButtonCommand);
            menu.addButton(plantCropButton);
            x+=10;
        }

        return menu;
    }

    private Menu getPlantedMenu(CropField cropField) {
        Menu menu = new Menu("GROWING", new Position(1, 1), 30, 10);
        Button closeMenuButton = new Button(new Position(30-3, 0), "X");
        closeMenuButton.setCommand(new SetControllerStateCommand(controller, controller.getGameControllerState()));
        menu.addButton(closeMenuButton);

        Label label = new Label(
                new Position(1, 4),
                () -> "REMAINING TIME: " + cropField.getRemainingTime().toCountdownString()
        );
        menu.addLabel(label);

        // TODO experimental
        Button debugButton = new Button(new Position(1, 6), "TIME TRAVEL");
        debugButton.setCommand(new CompoundCommand()
                .addCommand(() -> cropField.setState(new ReadyToHarvest(cropField, new Wheat())))
                .addCommand(new SetControllerStateCommand(controller, controller.getGameControllerState())));
        menu.addButton(debugButton);

        return menu;
    }

    private Menu getReadyToHarvest(CropField cropField) {
        Menu menu = new Menu("READY TO HARVEST", new Position(1, 1), 30, 10);
        Button closeMenuButton = new Button(new Position(30-3, 0), "X");
        closeMenuButton.setCommand(new SetControllerStateCommand(controller, controller.getGameControllerState()));
        menu.addButton(closeMenuButton);

        Button harvestButton = new Button(new Position(1, 5), "HARVEST");
        harvestButton.setCommand(new CompoundCommand()
                .addCommand(new HarvestCropCommand(cropField))
                .addCommand(new SetControllerStateCommand(controller, controller.getGameControllerState())));

        menu.addButton(harvestButton);

        return menu;
    }

    @Override
    public Command getInteractionCommand(CropField cropField) {
        Menu menu;
        if (cropField.getState() instanceof NotPlanted) {
            menu = getNotPlantedMenu(cropField);
        } else if (cropField.getState() instanceof Planted) {
            menu = getPlantedMenu(cropField);
        } else if (cropField.getState() instanceof ReadyToHarvest) {
            menu = getReadyToHarvest(cropField);
        } else {
            // This should never happen
            // TODO is throwing a RuntimeException ok?
            throw new RuntimeException(
                    "LOGIC ERROR: Unhandled CropFieldState: " + cropField.getState().getClass().toString());
        }

        return new OpenPopupMenuCommand(this.controller, menu);
    }
}
