package viewer.menu;

import gui.Color;
import gui.GUI;
import gui.drawer.shape.FilledRectangleDrawer;
import gui.drawer.shape.RectangleDrawer;
import gui.drawer.ui.TitleDrawer;
import model.Position;
import model.menu.Button;
import model.menu.Menu;
import viewer.GameViewer;

public class MenuViewer extends GameViewer {
    private Menu menu;

    public MenuViewer(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void drawScreen(GUI gui) {
        FilledRectangleDrawer backgroundDrawer = new FilledRectangleDrawer(
                gui, new Color("#222222"), new Color("#222222"), ' ');
        backgroundDrawer.draw(this.menu.getTopLeftPosition(), this.menu.getWidth(), this.menu.getHeight());

        TitleDrawer titleDrawer = new TitleDrawer(gui);
        Position titlePosition = this.menu.getTopLeftPosition().getRight().getDown();
        titleDrawer.draw(titlePosition, this.menu.getTitle());

        ButtonViewer buttonViewer = new ButtonViewer();
        for (Button button: this.menu.getButtons()) {
            buttonViewer.draw(this.menu, button, gui);
        }
    }
}
