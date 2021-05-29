package model.farm.building;

import model.Position;
import model.region.PositionRegion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EdificeTest {
    @Test
    public void getInteractiveRegion() {
        Edifice edifice = new Edifice(new Position(0, 0), "");
        Assertions.assertTrue(edifice.getInteractiveRegion() instanceof PositionRegion);
        Assertions.assertTrue(edifice.getInteractiveRegion().contains(new Position(4, 6)));
        edifice = new Edifice(new Position(6, 4), "");
        Assertions.assertTrue(edifice.getInteractiveRegion().contains(new Position(10, 10)));
    }
}
