package gui.drawer.entity;

import gui.Color;
import gui.GUI;
import gui.drawer.shape.FilledRectangleDrawer;
import gui.drawer.shape.RectangleDrawer;
import model.Position;

// TODO experimental
public class CropFieldDrawer {
    private GUI gui;
    private final static Color PATH_COLOR = new Color("#be9b7b");
    private final static Color SOIL_COLOR = new Color("#372201");
    private final static Color CROP_COLOR = new Color("#aabb01");
    private final static char CROP_CHAR = ';';

    public CropFieldDrawer(GUI gui) {
        this.gui = gui;
    }

    public void draw(Position position) {
        RectangleDrawer pathDrawer = new RectangleDrawer(gui, PATH_COLOR, PATH_COLOR, ' ');
        FilledRectangleDrawer cropDrawer = new FilledRectangleDrawer(gui, SOIL_COLOR, CROP_COLOR, CROP_CHAR);
        pathDrawer.draw(position, 4, 4);
        cropDrawer.draw(position.getRight().getDown(), 2 ,2);
    }
}
