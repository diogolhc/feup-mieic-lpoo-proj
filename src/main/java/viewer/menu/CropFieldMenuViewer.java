package viewer.menu;

import gui.GUI;
import gui.drawer.TitleDrawer;
import model.GameModel;
import model.Position;

public class CropFieldMenuViewer extends MenuViewer {
    @Override
    public void draw(GameModel model, GUI gui) {
        TitleDrawer titleDrawer = new TitleDrawer(gui, "#000000", "#aaaaaa");
        titleDrawer.draw(new Position(1, 1), "PLANT");

        ButtonViewer buttonViewer = new ButtonViewer(gui, "WHEAT", 7);
        buttonViewer.draw(new Position(1, 5));
    }
}
