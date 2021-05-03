package controller.farm;

import controller.GameController;
import controller.GameControllerState;
import gui.GUI;
import model.GameModel;
import viewer.GameViewer;

public class FarmController implements GameControllerState {
    private final InteractionController interactionController;
    private final GameViewer viewer;
    private final GameModel model;
    private final CropFieldController cropFieldController;
    private final HouseController houseController;

    public FarmController(GameController controller) {
        this.viewer = controller.getViewer();
        this.model = controller.getModel();
        this.interactionController = new InteractionController();
        this.cropFieldController = new CropFieldController(controller, viewer, model.getFarm().getCropField());
        this.interactionController.addInteractionListener(this.cropFieldController);
        this.houseController = new HouseController(controller, viewer, model.getFarm().getHouse());
        this.interactionController.addInteractionListener(this.houseController);
    }

    @Override
    public void doAction(GUI.ACTION action) {
        if (action == GUI.ACTION.INTERACT)
            this.interactionController.notifyInteraction(this.model.getFarm().getFarmer().getPosition());

        FarmerController farmerController = new FarmerController(this.model.getFarm());
        farmerController.doAction(action);
    }
}
