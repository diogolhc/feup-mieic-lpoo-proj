package model.menu;

import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ButtonTest {
    private Button button;

    @Test
    public void getWidth() {
        button = new Button(new Position(0, 0), "");
        Assertions.assertEquals(2, button.getWidth());
        button = new Button(new Position(0, 0), "a");
        Assertions.assertEquals(3, button.getWidth());
        button = new Button(new Position(0, 0), "BFA");
        Assertions.assertEquals(5, button.getWidth());
        button = new Button(new Position(0, 0), "fafj");
        Assertions.assertEquals(6, button.getWidth());
        button = new Button(new Position(0, 0), "foobar");
        Assertions.assertEquals(8, button.getWidth());
    }

    @Test
    public void contains() {
        button = new Button(new Position(6, 1), "test");
        Assertions.assertTrue(button.contains(new Position(6, 1)));
        Assertions.assertTrue(button.contains(new Position(6, 2)));
        Assertions.assertTrue(button.contains(new Position(6, 3)));
        Assertions.assertTrue(button.contains(new Position(8, 2)));
        Assertions.assertTrue(button.contains(new Position(11, 1)));

        Assertions.assertFalse(button.contains(new Position(5, 1)));
        Assertions.assertFalse(button.contains(new Position(6, 0)));
        Assertions.assertFalse(button.contains(new Position(6, 4)));
        Assertions.assertFalse(button.contains(new Position(0, 0)));
        Assertions.assertFalse(button.contains(new Position(12, 1)));
    }
}
