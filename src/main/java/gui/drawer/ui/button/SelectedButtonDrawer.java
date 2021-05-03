package gui.drawer.ui.button;

import gui.Color;
import gui.GUI;

public class SelectedButtonDrawer extends ButtonDrawer {
    private static final Color BUTTON_COLOR = new Color("#00c853");
    private static final Color BUTTON_BACKGROUND = new Color("#cfd8dc");

    public SelectedButtonDrawer(GUI gui, String title, int width, int height) {
        super(gui, title, width, height, BUTTON_BACKGROUND, BUTTON_COLOR);
    }
}
