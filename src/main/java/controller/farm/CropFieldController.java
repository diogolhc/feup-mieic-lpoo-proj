package controller.farm;

import model.CropField;
import model.Position;
import viewer.GameViewer;
import viewer.menu.CropFieldMenuViewer;
import viewer.menu.MenuViewer;

public class CropFieldController implements InteractionListener {
    private final GameViewer viewer;
    private final CropField cropField;

    public CropFieldController(GameViewer viewer, CropField cropField) {
        this.viewer = viewer;
        this.cropField = cropField;
    }

    @Override
    public void notifyInteraction(Position position) {
        viewer.setGameViewerState(new CropFieldMenuViewer());
    }
}
