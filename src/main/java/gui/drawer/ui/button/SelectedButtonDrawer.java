package gui.drawer.ui.button;

import gui.GUI;

public class SelectedButtonDrawer extends ButtonDrawer {
    private static final String BUTTON_COLOR = "#00c853";
    private static final String BUTTON_BACKGROUND = "#cfd8dc";

    public SelectedButtonDrawer(GUI gui, String title, int width, int height) {
        super(gui, title, width, height, BUTTON_BACKGROUND, BUTTON_COLOR);
    }
}
