package model.menu.builder;

import controller.GameController;
import controller.command.*;
import model.Position;
import model.farm.building.crop_field.CropField;
import model.menu.Button;
import model.menu.Menu;

public class HarvestMenuBuilder extends PopupMenuBuilder {
    private CropField cropField;

    public HarvestMenuBuilder(GameController controller, CropField cropField) {
        super(controller);
        this.cropField = cropField;
    }

    @Override
    protected void addButtonsAndLabels(Menu menu) {
        super.addButtonsAndLabels(menu);

        Button harvestButton = new Button(new Position(1, 5), "HARVEST");
        harvestButton.setCommand(new CompoundCommand()
                .addCommand(new HarvestCropCommand(cropField))
                .addCommand(super.getClosePopupMenuCommand())
        );
        menu.addButton(harvestButton);

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
