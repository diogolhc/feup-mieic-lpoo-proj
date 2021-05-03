package viewer.menu;

import gui.Color;
import gui.GUI;
import gui.drawer.shape.FilledRectangleDrawer;
import gui.drawer.shape.RectangleDrawer;
import gui.drawer.ui.TitleDrawer;
import model.Position;
import model.menu.Button;
import model.menu.Menu;
import model.menu.label.Label;
import viewer.GameViewer;

public class MenuViewer extends GameViewer {
    public static final Color MENU_BACKGROUND_COLOR = new Color("#222222");

    private Menu menu;

    public MenuViewer(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void drawScreen(GUI gui) {
        FilledRectangleDrawer backgroundDrawer = new FilledRectangleDrawer(
                gui, MENU_BACKGROUND_COLOR, MENU_BACKGROUND_COLOR, ' ');
        backgroundDrawer.draw(this.menu.getTopLeftPosition(), this.menu.getWidth(), this.menu.getHeight());

        TitleDrawer titleDrawer = new TitleDrawer(gui);
        Position titlePosition = this.menu.getTopLeftPosition().getRight().getDown();
        titleDrawer.draw(titlePosition, this.menu.getTitle());

        ButtonViewer buttonViewer = new ButtonViewer();
        for (Button button: this.menu.getButtons()) {
            buttonViewer.draw(this.menu, button, gui);
        }

        LabelViewer labelViewer = new LabelViewer();
        for (Label label: this.menu.getLabels()) {
            labelViewer.draw(this.menu, label, gui);
        }
    }
}
