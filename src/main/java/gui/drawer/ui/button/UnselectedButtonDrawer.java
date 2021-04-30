package gui.drawer.ui.button;

import gui.GUI;

public class UnselectedButtonDrawer extends ButtonDrawer {
    private static final String BUTTON_COLOR = "#263238";
    private static final String BUTTON_BACKGROUND = "#90a4ae";

    public UnselectedButtonDrawer(GUI gui, String title, int width, int height) {
        super(gui, title, width, height, BUTTON_BACKGROUND, BUTTON_COLOR);
    }
}
