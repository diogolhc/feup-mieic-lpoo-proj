package viewer.menu;

import gui.Color;
import gui.GUI;
import gui.drawer.string.StringDrawer;
import model.menu.Menu;
import model.menu.label.Label;

public class LabelViewer {
    private static final Color LABEL_TEXT_COLOR = new Color("#aaaaaa");

    public void draw(Menu menu, Label label, GUI gui) {
        StringDrawer drawer = new StringDrawer(gui, MenuViewer.MENU_BACKGROUND_COLOR, LABEL_TEXT_COLOR);
        drawer.draw(label.getTopLeft().getTranslated(menu.getTopLeftPosition()), label.getString());
    }
}
