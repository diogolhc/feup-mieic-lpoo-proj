package viewer.farm;

import gui.Color;
import gui.GUI;
import gui.drawer.shape.FilledRectangleDrawer;
import gui.drawer.shape.RectangleDrawer;
import model.Position;
import model.farm.building.crop_field.CropField;
import model.farm.crop.GrowthStage;

public class CropFieldViewer {
    private final static Color PATH_COLOR = new Color("#be9b7b");
    private final static Color SOIL_COLOR = new Color("#372201");

    public void draw(CropField cropField, GUI gui) {
        GrowthStage growthStage = cropField.getCropGrowthStage();
        Position position = cropField.getTopLeftPosition();
        Color cropColor = growthStage.getStageColor();
        char cropChar = growthStage.getStageChar();

        RectangleDrawer pathDrawer = new RectangleDrawer(gui, PATH_COLOR, PATH_COLOR, ' ');
        FilledRectangleDrawer cropDrawer = new FilledRectangleDrawer(gui, SOIL_COLOR, cropColor, cropChar);
        pathDrawer.draw(position, 4, 4);
        cropDrawer.draw(position.getRight().getDown(), 2 ,2);
    }
}
