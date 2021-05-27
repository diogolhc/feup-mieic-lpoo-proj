package viewer.menu;

import gui.GUI;
import gui.drawer.shape.FilledRectangleDrawer;
import model.menu.Button;
import model.menu.Menu;
import model.menu.label.Label;
import viewer.GameViewer;
import viewer.menu.element.ButtonViewer;
import viewer.menu.element.LabelViewer;

public abstract class MenuViewer extends GameViewer {
    protected final Menu menu;

    public MenuViewer(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void draw(GUI gui) {
        drawBackground(gui);
        drawTitle(gui);
        drawButtons(gui);
        drawLabels(gui);
    }

    private void drawBackground(GUI gui) {
        FilledRectangleDrawer backgroundDrawer = new FilledRectangleDrawer(
                gui, this.menu.getColor(), this.menu.getColor(), ' ');
        backgroundDrawer.draw(this.menu.getTopLeftPosition(), this.menu.getWidth(), this.menu.getHeight());
    }

    protected abstract void drawTitle(GUI gui);

    private void drawButtons(GUI gui) {
        ButtonViewer buttonViewer = new ButtonViewer();
        for (Button button: this.menu.getButtons()) {
            buttonViewer.draw(this.menu.getTopLeftPosition(), button, gui);
        }
    }

    private void drawLabels(GUI gui) {
        LabelViewer labelViewer = new LabelViewer();
        for (Label label: this.menu.getLabels()) {
            labelViewer.draw(this.menu, label, gui);
        }
    }
}
