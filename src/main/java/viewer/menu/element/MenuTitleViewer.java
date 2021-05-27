package viewer.menu.element;

import gui.Color;
import gui.GUI;
import gui.drawer.string.UnderlinedStringDrawer;
import model.Position;
import model.menu.Menu;

public class MenuTitleViewer {
    public static final Color TITLE_COLOR = new Color("#aaaaaa");
    public static final char UNDERLINE_CHARACTER = '-';

    public void draw(Menu menu, Position position, GUI gui) {
        UnderlinedStringDrawer drawer = new UnderlinedStringDrawer(
                gui, menu.getColor(), this.TITLE_COLOR, this.UNDERLINE_CHARACTER);

        drawer.draw(position.getTranslated(menu.getTopLeftPosition()), menu.getTitle());
    }
}
