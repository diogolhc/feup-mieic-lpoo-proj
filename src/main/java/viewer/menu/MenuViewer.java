package viewer.menu;

import gui.GUI;
import gui.drawer.ui.TitleDrawer;
import model.GameModel;
import model.Position;
import model.menu.Button;
import viewer.GameViewerState;

public class MenuViewer implements GameViewerState {
    /*
    TitleDrawer titleDrawer = new TitleDrawer(gui, "#000000", "#aaaaaa");
        titleDrawer.draw(new Position(1, 1), "PLANT");

    ButtonViewer buttonViewer = new ButtonViewer(gui, "WHEAT", 7);
        buttonViewer.draw(new Position(1, 5));
        */
    @Override
    public void draw(GameModel model, GUI gui) {
        TitleDrawer titleDrawer = new TitleDrawer(gui, "#000000", "#aaaaaa");
        titleDrawer.draw(new Position(1, 1), model.getMenu().getTitle());

        for (Button button: model.getMenu().getButtons()) {
            ButtonViewer buttonViewer = new ButtonViewer();
            buttonViewer.draw(button, gui);
        }
    }
}
