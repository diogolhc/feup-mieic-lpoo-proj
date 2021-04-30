package gui.drawer;

import gui.GUI;
import model.Position;

// TODO experimental
public class CropFieldDrawer {
    private GUI gui;
    private final static String PATH_COLOR = "#be9b7b";
    private final static String SOIL_COLOR = "#372201";
    private final static String CROP_COLOR = "#aabb01";
    private final static char CROP_CHAR = '#';

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
