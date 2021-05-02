package controller;

import gui.GUI;
import gui.LanternaGUI;
import model.GameModel;
import model.Position;
import model.farm.Farmer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import viewer.GameViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

public class InteractiveElementsTest {
    private GameController gameController;
    private GameModel model;
    private GameViewer viewer;

    @BeforeEach
    public void setupHelper() throws FontFormatException, IOException, URISyntaxException {
        GUI gui = new LanternaGUI(40, 21);

        this.model = new GameModel();
        this.viewer = new GameViewer(gui);
        this.gameController = new GameController(this.viewer, this.model);
    }

    @Test
    public void testFarmerMovement() throws IOException {
        Farmer farmer = this.gameController.getModel().getFarm().getFarmer();

        assertEquals(new Position(3, 3), farmer.getPosition());
    }

    @Test
    public void testFarmerMovement2() throws IOException {
        Farmer farmer = this.gameController.getModel().getFarm().getFarmer();

        GameControllerState gameControllerState = this.gameController.getGameControllerState();
        GUI.ACTION action = GUI.ACTION.MOVE_RIGHT;

        gameControllerState.doAction(action);
        gameControllerState.doAction(action);

        assertEquals(new Position(5, 3), farmer.getPosition());
    }

    @Test
    public void testFarmerColisionWithCropField() throws IOException {
        Farmer farmer = this.gameController.getModel().getFarm().getFarmer();

        GameControllerState gameControllerState = this.gameController.getGameControllerState();
        GUI.ACTION action = GUI.ACTION.MOVE_RIGHT;

        gameControllerState.doAction(action);
        gameControllerState.doAction(action);
        gameControllerState.doAction(action);

        assertEquals(new Position(5, 3), farmer.getPosition());
    }


    @Test
    public void testFarmerColisionWithHouse() throws IOException {
        Farmer farmer = this.gameController.getModel().getFarm().getFarmer();

        GameControllerState gameControllerState = this.gameController.getGameControllerState();

        gameControllerState.doAction(GUI.ACTION.MOVE_RIGHT);
        gameControllerState.doAction(GUI.ACTION.MOVE_RIGHT);

        gameControllerState.doAction(GUI.ACTION.MOVE_DOWN);
        gameControllerState.doAction(GUI.ACTION.MOVE_DOWN);
        gameControllerState.doAction(GUI.ACTION.MOVE_DOWN);
        gameControllerState.doAction(GUI.ACTION.MOVE_DOWN);
        gameControllerState.doAction(GUI.ACTION.MOVE_DOWN);
        gameControllerState.doAction(GUI.ACTION.MOVE_DOWN);
        gameControllerState.doAction(GUI.ACTION.MOVE_DOWN);
        gameControllerState.doAction(GUI.ACTION.MOVE_DOWN);

        gameControllerState.doAction(GUI.ACTION.MOVE_RIGHT);

        assertEquals(new Position(5, 11), farmer.getPosition());
    }
}
