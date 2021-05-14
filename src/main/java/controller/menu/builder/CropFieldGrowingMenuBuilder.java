package controller.menu.builder;

import controller.GameController;
import controller.command.CompoundCommand;
import controller.command.RemoveCropCommand;
import model.Position;
import model.farm.building.crop_field.CropField;
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
                () -> "CROP: " + cropField.getState().getCrop().getName()
        ));

        menu.addLabel(new Label(
                new Position(1, 5),
                () -> "REMAINING TIME: " + cropField.getRemainingTime().toCountdownString()
        ));

        Button removeCropButton = new Button(new Position(1, 7), "REMOVE CROP");
        removeCropButton.setCommand(new CompoundCommand()
                .addCommand(new RemoveCropCommand(cropField))
                .addCommand(super.getClosePopupMenuCommand())
        );
        menu.addButton(removeCropButton);
    }

    @Override
    protected int getHeight() {
        return 11;
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
