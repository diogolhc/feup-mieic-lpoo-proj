package viewer.menu.element;

import gui.Color;
import gui.GUI;
import gui.drawer.shape.BoxDrawer;
import gui.drawer.shape.FilledRectangleDrawer;
import gui.drawer.string.StringDrawer;
import model.Position;
import model.menu.Button;

public class ButtonViewer {
    public static final Color SELECTED_BUTTON_FOREGROUND = new Color("#00c853");
    public static final Color SELECTED_BUTTON_BACKGROUND = new Color("#cfd8dc");
    public static final Color UNSELECTED_BUTTON_FOREGROUND = new Color("#263238");
    public static final Color UNSELECTED_BUTTON_BACKGROUND = new Color("#90a4ae");

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

        drawButtonBackground(position, button, backgroundColor, gui);
        drawButtonEdges(position, button, backgroundColor, foregroundColor, gui);
        drawButtonTitle(position, button, backgroundColor, foregroundColor, gui);
    }

    private void drawButtonBackground(Position position, Button button, Color backgroundColor, GUI gui) {
        FilledRectangleDrawer rectangleDrawer = new FilledRectangleDrawer(gui, backgroundColor);
        rectangleDrawer.draw(position, button.getWidth(), button.getHeight());
    }

    private void drawButtonEdges(Position position, Button button,
                                 Color backgroundColor, Color foregroundColor, GUI gui) {
        BoxDrawer boxDrawer = new BoxDrawer(gui, backgroundColor, foregroundColor);
        boxDrawer.draw(position, button.getWidth(), button.getHeight());
    }

    private void drawButtonTitle(Position position, Button button,
                                 Color backgroundColor, Color foregroundColor, GUI gui) {
        Position centerOffset = new Position((button.getWidth() - button.getTitle().length())/2, 1);
        Position titlePosition = position.getTranslated(centerOffset);
        StringDrawer stringDrawer = new StringDrawer(gui, backgroundColor, foregroundColor);
        stringDrawer.draw(titlePosition, button.getTitle());
    }
}
