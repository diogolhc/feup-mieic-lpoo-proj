package viewer.menu;

import gui.Color;
import gui.GUI;
import gui.drawer.string.UnderlinedStringDrawer;
import model.Position;
import model.menu.Menu;

// TODO too similar with MenuTitleViewer
public class MainMenuTitleViewer {
    private static final Color TITLE_COLOR = new Color("#aaaaaa");
    private static final char UNDERLINE_CHARACTER = '=';
    private static final Position TITLE_POSITION = new Position(15, 2);

    public void draw(Menu menu, GUI gui) {
        UnderlinedStringDrawer drawer = new UnderlinedStringDrawer(
                gui, MenuViewer.MENU_BACKGROUND_COLOR, this.TITLE_COLOR, this.UNDERLINE_CHARACTER);

        drawer.draw(this.TITLE_POSITION.getTranslated(menu.getTopLeftPosition()), menu.getTitle());
    }
}
