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
import model.menu.builder.CropFieldGrowingMenuBuilder;
import model.menu.builder.HarvestMenuBuilder;
import model.menu.builder.MenuBuilder;
import model.menu.builder.PlantCropMenuBuilder;
import model.menu.label.Label;
import model.menu.label.LabelText;
import viewer.GameViewer;
import viewer.menu.MenuViewer;

public class CropFieldController extends BuildingController<CropField> {
    private final GameController controller;

    public CropFieldController(GameController controller) {
        this.controller = controller;
    }

    @Override
    public Command getInteractionCommand(CropField cropField) {
        MenuBuilder menuBuilder;
        if (cropField.getState() instanceof NotPlanted) {
            menuBuilder = new PlantCropMenuBuilder(this.controller, cropField);
        } else if (cropField.getState() instanceof Planted) {
            menuBuilder = new CropFieldGrowingMenuBuilder(this.controller, cropField);
        } else if (cropField.getState() instanceof ReadyToHarvest) {
            menuBuilder = new HarvestMenuBuilder(this.controller, cropField);
        } else {
            // This should never happen
            // TODO is throwing a RuntimeException ok?
            throw new RuntimeException(
                    "LOGIC ERROR: Unhandled CropFieldState: " + cropField.getState().getClass().toString());
        }

        return new OpenPopupMenuCommand(this.controller, menuBuilder.buildMenu(new Position(1, 1)));
    }
}
