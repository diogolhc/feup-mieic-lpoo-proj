package viewer.menu;

import gui.Color;
import gui.GUI;
import gui.drawer.shape.BoxDrawer;
import gui.drawer.string.StringDrawer;
import model.Position;
import model.menu.Button;
import model.menu.Menu;

public class ButtonViewer {
    private static final Color SELECTED_BUTTON_FOREGROUND = new Color("#00c853");
    private static final Color SELECTED_BUTTON_BACKGROUND = new Color("#cfd8dc");
    private static final Color UNSELECTED_BUTTON_FOREGROUND = new Color("#263238");
    private static final Color UNSELECTED_BUTTON_BACKGROUND = new Color("#90a4ae");

    public void draw(Position menuPosition, Button button, GUI gui) {
        Position position = button.getTopLeft().getTranslated(menuPosition);
        Color backgroundColor, foregroundColor;

        if (button.isSelected()) {
            backgroundColor = SELECTED_BUTTON_BACKGROUND;
            foregroundColor = SELECTED_BUTTON_FOREGROUND;
        } else {
            backgroundColor = UNSELECTED_BUTTON_BACKGROUND;
            foregroundColor = UNSELECTED_BUTTON_FOREGROUND;
        }

        // TODO handle when title doesn't fit? (go to next line) (maybe this is overkill and won't be needed)
        BoxDrawer boxDrawer = new BoxDrawer(gui, backgroundColor, foregroundColor);
        boxDrawer.draw(position, button.getWidth(), button.getHeight());

        Position titlePosition = position.getRight().getDown();
        StringDrawer stringDrawer = new StringDrawer(gui, backgroundColor, foregroundColor);
        stringDrawer.draw(titlePosition, button.getTitle());
    }
}
