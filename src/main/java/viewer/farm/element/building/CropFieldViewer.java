package viewer.farm.element.building;

import gui.Color;
import gui.GUI;
import gui.drawer.shape.FilledRectangleDrawer;
import gui.drawer.shape.RectangleDrawer;
import model.Position;
import model.farm.building.CropField;
import model.farm.data.item.CropGrowthStage;

public class CropFieldViewer {
    public static final Color SOIL_COLOR = new Color("#372201");

    public void draw(CropField cropField, GUI gui) {
        CropGrowthStage growthStage = cropField.getCropGrowthStage();
        Position position = cropField.getTopLeftPosition();
        Color cropColor = growthStage.getStageColor();
        char cropChar = growthStage.getStageChar();

        drawInteractivePath(gui, position);
        drawSoil(gui, position.getTranslated(new Position(1, 1)), cropColor, cropChar);
    }

    private void drawInteractivePath(GUI gui, Position position) {
        RectangleDrawer pathDrawer = new RectangleDrawer(gui, Color.HIGHLIGHTED_FLOOR);
        pathDrawer.draw(position, 4, 4);
    }

    private void drawSoil(GUI gui, Position position, Color cropColor, char cropChar) {
        FilledRectangleDrawer cropDrawer = new FilledRectangleDrawer(gui, SOIL_COLOR, cropColor, cropChar);
        cropDrawer.draw(position, 2 ,2);
    }
}
