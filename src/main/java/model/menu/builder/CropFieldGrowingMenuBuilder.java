package model.menu.builder;

import controller.GameController;
import controller.command.CompoundCommand;
import model.Position;
import model.farm.building.crop_field.CropField;
import model.farm.building.crop_field.crop.Wheat;
import model.farm.building.crop_field.state.ReadyToHarvest;
import model.menu.Button;
import model.menu.Menu;
import model.menu.label.Label;

public class CropFieldGrowingMenuBuilder extends PopupMenuBuilder {
    private CropField cropField;

    public CropFieldGrowingMenuBuilder(GameController controller, CropField cropField) {
        super(controller);
        this.cropField = cropField;
    }

    @Override
    protected void addButtonsAndLabels(Menu menu) {
        super.addButtonsAndLabels(menu);

        menu.addLabel(new Label(
                new Position(1, 4),
                () -> "REMAINING TIME: " + cropField.getRemainingTime().toCountdownString()
        ));

        // TODO experimental
        Button debugButton = new Button(new Position(1, 6), "TIME TRAVEL");
        debugButton.setCommand(new CompoundCommand()
                .addCommand(() -> cropField.setState(new ReadyToHarvest(new Wheat())))
                .addCommand(super.getClosePopupMenuCommand())
        );
        menu.addButton(debugButton);
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
        return "GROWING";
    }
}
