package controller.menu.builder.crop_field;

import controller.GameController;
import controller.command.*;
import controller.menu.ButtonController;
import controller.menu.builder.PopupMenuControllerBuilder;
import model.Position;
import model.farm.Inventory;
import model.farm.building.CropField;
import model.menu.Button;

import java.util.List;

public class HarvestMenuControllerBuilder extends PopupMenuControllerBuilder {
    private Inventory inventory;
    private CropField cropField;

    public HarvestMenuControllerBuilder(GameController controller, Inventory inventory, CropField cropField) {
        super(controller);
        this.inventory = inventory;
        this.cropField = cropField;
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        Button harvestButton = new Button(new Position(1, 5), "HARVEST");
        Command harvestButtonCommand = new CompoundCommand()
                .addCommand(new HarvestCropCommand(inventory, cropField))
                .addCommand(super.getClosePopupMenuCommand());
        buttons.add(new ButtonController(harvestButton, harvestButtonCommand));

        return buttons;
    }

    @Override
    protected int getHeight() {
        return 10;
    }

    @Override
    protected int getWidth() {
        return 30;
    }

    @Override
    protected String getTitle() {
        return "READY TO HARVEST";
    }
}
