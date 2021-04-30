package viewer.menu;

import gui.GUI;
import gui.drawer.BoxDrawer;
import model.Position;

public class ButtonViewer {
    private GUI gui;
    private String title;
    private int width;

    public ButtonViewer(GUI gui, String title, int width) {

        this.gui = gui;
        this.title = title;
        this.width = width;
    }

    public void draw(Position position) {
        BoxDrawer boxDrawer = new BoxDrawer(this.gui, "#000000", "#444444");
        // TODO wrap button if title > width
        boxDrawer.draw(position, this.width, 3);
        Position titlePosition = position.getRight().getDown();
        this.gui.drawString(titlePosition.getX(), titlePosition.getY(), this.title);
    }
}
