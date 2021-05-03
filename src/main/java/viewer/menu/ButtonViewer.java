package viewer.menu;

import gui.GUI;
import gui.drawer.shape.BoxDrawer;
import gui.drawer.ui.button.ButtonDrawer;
import gui.drawer.ui.button.SelectedButtonDrawer;
import gui.drawer.ui.button.UnselectedButtonDrawer;
import model.Position;
import model.menu.Button;
import model.menu.Menu;

public class ButtonViewer {
    public void draw(Menu menu, Button button, GUI gui) {
        ButtonDrawer buttonDrawer;
        if (button.isSelected()) {
            buttonDrawer = new SelectedButtonDrawer(gui, button.getTitle(), button.getWidth(), button.getHeight());
        } else {
            buttonDrawer = new UnselectedButtonDrawer(gui, button.getTitle(), button.getWidth(), button.getHeight());
        }

        buttonDrawer.draw(button.getTopLeft().getTranslated(menu.getTopLeftPosition()));
    }
}
