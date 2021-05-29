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
    public void getWidthFixed() {
        button = new Button(new Position(0, 0), "", 5);
        Assertions.assertEquals(5, button.getWidth());
        button = new Button(new Position(0, 0), "a", 5);
        Assertions.assertEquals(5, button.getWidth());
        button = new Button(new Position(0, 0), "BFA", 5);
        Assertions.assertEquals(5, button.getWidth());
        button = new Button(new Position(0, 0), "fafj", 5);
        Assertions.assertEquals(6, button.getWidth());
        button = new Button(new Position(0, 0), "foobar", 5);
        Assertions.assertEquals(8, button.getWidth());
    }
}
