package controller.menu.builder;

import controller.GameController;
import controller.command.*;
import controller.menu.ButtonController;
import model.Position;
import model.farm.building.crop_field.CropField;
import model.menu.Button;

import java.util.List;

public class HarvestMenuControllerBuilder extends PopupMenuControllerBuilder {
    private CropField cropField;

    public HarvestMenuControllerBuilder(GameController controller, CropField cropField) {
        super(controller);
        this.cropField = cropField;
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        Button harvestButton = new Button(new Position(1, 5), "HARVEST");
        Command harvestButtonCommand = new CompoundCommand()
                .addCommand(new HarvestCropCommand(cropField))
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
