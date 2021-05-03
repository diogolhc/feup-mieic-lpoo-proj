package gui.drawer.ui.button;

import gui.Color;
import gui.GUI;

public class UnselectedButtonDrawer extends ButtonDrawer {
    private static final Color BUTTON_COLOR = new Color("#263238");
    private static final Color BUTTON_BACKGROUND = new Color("#90a4ae");

    public UnselectedButtonDrawer(GUI gui, String title, int width, int height) {
        super(gui, title, width, height, BUTTON_BACKGROUND, BUTTON_COLOR);
    }
}
