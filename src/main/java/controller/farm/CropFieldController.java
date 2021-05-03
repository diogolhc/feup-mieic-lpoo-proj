package controller.farm;

import controller.GameController;
import controller.menu.MenuController;
import model.farm.crop_field.CropField;
import model.Position;
import model.menu.Button;
import model.menu.Menu;
import viewer.GameViewer;
import viewer.menu.MenuViewer;

public class CropFieldController implements InteractionListener {
    private final GameController controller;
    private final GameViewer viewer;
    private final CropField cropField;

    public CropFieldController(GameController controller, GameViewer viewer, CropField cropField) {
        this.controller = controller;
        this.viewer = viewer;
        this.cropField = cropField;
    }

    @Override
    public void notifyInteraction(Position position) {
        if (cropField.contains(position)) {
            Menu menu = cropField.getState().getInteractionMenu();
            this.controller.getModel().setMenu(menu);
            this.viewer.setGameViewerState(new MenuViewer());
            this.controller.setGameControllerState(new MenuController(this.controller));
        }
    }
}