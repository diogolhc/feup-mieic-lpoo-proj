package controller.farm;

import controller.GameControllerState;
import gui.GUI;
import model.GameModel;
import viewer.GameViewer;

public class FarmController implements GameControllerState {
    private final InteractionController interactionController;
    private final GameViewer viewer;
    private final GameModel model;
    private final CropFieldController cropFieldController;

    public FarmController(GameViewer viewer, GameModel model) {
        this.viewer = viewer;
        this.model = model;
        this.interactionController = new InteractionController();
        this.cropFieldController = new CropFieldController(viewer, model.getFarm().getCropField());
        this.interactionController.addInteractionListener(this.cropFieldController);
    }

    @Override
    public void doAction(GUI.ACTION action) {
        if (action == GUI.ACTION.INTERACT) this.interactionController.notifyInteraction(this.model.getFarm().getFarmer().getPosition());
        FarmerController farmerController = new FarmerController(this.model.getFarm());
        farmerController.doAction(action);
    }
}
