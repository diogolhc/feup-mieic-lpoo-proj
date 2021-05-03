package controller.farm;

import controller.GameController;
import controller.menu.MenuController;
import model.Position;
import model.farm.House;
import model.menu.Button;
import model.menu.Menu;
import viewer.GameViewer;
import viewer.menu.MenuViewer;

public class HouseController implements InteractionListener {

    private final GameController controller;
    private final GameViewer viewer;
    private final House house;

    public HouseController(GameController controller, GameViewer viewer, House house) {
        this.controller = controller;
        this.viewer = viewer;
        this.house = house;
    }

    @Override
    public void notifyInteraction(Position position) {

        int houseX = house.getPosition().getX();
        int houseY = house.getPosition().getY();
        int houseSize = house.getSize();
        if (position.getX() >= houseX && position.getX() <= houseX + houseSize &&
                position.getY() >= houseY && position.getY() <= houseY + houseSize) {
            Menu menu = new Menu("HOUSE");
            menu.addButton(new Button(new Position(1, 5), "UPGRADE", 10));

            this.controller.getModel().setMenu(menu);
            this.viewer.setGameViewerState(new MenuViewer());
            this.controller.setGameControllerState(new MenuController(this.controller));
        }
    }
}
