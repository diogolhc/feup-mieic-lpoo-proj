package controller.farm;

import gui.GUI;
import model.Position;
import model.farm.Farm;
import model.farm.Farmer;
import model.farm.building.House;
import model.farm.building.crop_field.CropField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FarmerControllerTest {
    private Farm farm;
    private FarmerController controller;

    @BeforeEach
    public void setUp() {
        farm = new Farm(20, 20);
        farm.getBuildings().setHouse(new House(new Position(1, 1)));
        farm.setFarmer(new Farmer(new Position(10, 10)));
        controller = new FarmerController(farm);
    }

    @Test
    public void moveUp() {
        controller.doAction(GUI.ACTION.MOVE_UP);
        Assertions.assertEquals(new Position(10, 9), farm.getFarmer().getPosition());
    }

    @Test
    public void moveDown() {
        controller.doAction(GUI.ACTION.MOVE_DOWN);
        Assertions.assertEquals(new Position(10, 11), farm.getFarmer().getPosition());
    }

    @Test
    public void moveLeft() {
        controller.doAction(GUI.ACTION.MOVE_LEFT);
        Assertions.assertEquals(new Position(9, 10), farm.getFarmer().getPosition());
    }

    @Test
    public void moveRight() {
        controller.doAction(GUI.ACTION.MOVE_RIGHT);
        Assertions.assertEquals(new Position(11, 10), farm.getFarmer().getPosition());
    }

    @Test
    public void cantMove() {
        farm.getBuildings().setHouse(new House(new Position(10, 10)));
        controller.doAction(GUI.ACTION.MOVE_DOWN);
        Assertions.assertEquals(new Position(10, 10), farm.getFarmer().getPosition());
        controller.doAction(GUI.ACTION.MOVE_RIGHT);
        Assertions.assertEquals(new Position(10, 10), farm.getFarmer().getPosition());
    }

    @Test
    public void moveUntilObstacle() {
        farm.getBuildings().addCropField(new CropField(new Position(15, 9)));

        Position lastPosition;
        do {
            lastPosition = farm.getFarmer().getPosition();
            controller.doAction(GUI.ACTION.MOVE_RIGHT);
        } while (farm.getFarmer().getPosition() != lastPosition);

        Assertions.assertEquals(new Position(15, 10), farm.getFarmer().getPosition());
    }
}
