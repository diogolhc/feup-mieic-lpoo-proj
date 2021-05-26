package viewer.menu;

import gui.Color;
import gui.GUI;
import gui.drawer.shape.HorizontalLineDrawer;
import gui.drawer.string.StringDrawer;
import gui.drawer.string.UnderlinedStringDrawer;
import model.Position;
import model.menu.Menu;
import model.menu.label.Label;

public class MenuTitleViewer {
    private static final Color TITLE_COLOR = new Color("#aaaaaa");
    private static final char UNDERLINE_CHARACTER = '-';

    public void draw(Menu menu, Position position, GUI gui) {
        UnderlinedStringDrawer drawer = new UnderlinedStringDrawer(
                gui, MenuViewer.MENU_BACKGROUND_COLOR, this.TITLE_COLOR, this.UNDERLINE_CHARACTER);

        drawer.draw(position.getTranslated(menu.getTopLeftPosition()), menu.getTitle());
    }
}
