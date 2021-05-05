package gui.drawer.string;

import gui.Color;
import gui.GUI;
import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StringDrawerTest {
    private GUI gui;


    @BeforeEach
    void setUp() {
        this.gui = Mockito.mock(GUI.class);

    }

    @Test
    void draw() {
        Color BACK = new Color("#123456");
        Color FRONT = new Color("#FEDCBA");
        StringDrawer drawer = new StringDrawer(gui, BACK, FRONT);
        drawer.draw(new Position(5, 7), "HELLO WORLD");

        Mockito.verify(gui, Mockito.times(1)).setBackgroundColor(BACK);
        Mockito.verify(gui, Mockito.times(1)).setForegroundColor(FRONT);
        Mockito.verify(gui, Mockito.times(1)).drawString(5, 7, "HELLO WORLD");
    }

    @Test
    void drawMultiple() {
        Color BLACK = new Color("#000000");
        Color WHITE = new Color("#FFFFFF");
        Color BACK1 = new Color("#123456");
        Color FRONT1 = new Color("#FEDCBA");
        Color BACK2 = new Color("#123457");
        Color FRONT2 = new Color("#FEDCBB");
        Color BACK3 = new Color("#123458");
        Color FRONT3 = new Color("#FEDCBC");

        StringDrawer drawer1 = new StringDrawer(gui, BACK1, FRONT1);
        StringDrawer drawer2 = new StringDrawer(gui, BACK2, FRONT2);
        StringDrawer drawer3 = new StringDrawer(gui, BACK3, FRONT3);
        drawer1.draw(new Position(5, 7), "HELLO WORLD");
        drawer1.draw(new Position(8, 7), "HELLO WORLD");
        Mockito.verify(gui, Mockito.times(2)).setBackgroundColor(BACK1);
        Mockito.verify(gui, Mockito.times(2)).setForegroundColor(FRONT1);
        Mockito.verify(gui, Mockito.times(1)).drawString(5, 7, "HELLO WORLD");
        Mockito.verify(gui, Mockito.times(1)).drawString(8, 7, "HELLO WORLD");
        drawer2.draw(new Position(0, 0), "HI");
        drawer3.draw(new Position(0, 0), "C");
        drawer1.draw(new Position(1, 2), "foo");
        drawer2.draw(new Position(1, 2), "foo");
        drawer2.draw(new Position(5, 7), "foo");
        Mockito.verify(gui, Mockito.times(3)).setBackgroundColor(BACK1);
        Mockito.verify(gui, Mockito.times(3)).setForegroundColor(FRONT1);
        Mockito.verify(gui, Mockito.times(3)).setBackgroundColor(BACK2);
        Mockito.verify(gui, Mockito.times(3)).setForegroundColor(FRONT2);
        Mockito.verify(gui, Mockito.times(1)).setBackgroundColor(BACK3);
        Mockito.verify(gui, Mockito.times(1)).setForegroundColor(FRONT3);
        Mockito.verify(gui, Mockito.times(2)).drawString(Mockito.eq(0), Mockito.eq(0), Mockito.anyString());
        Mockito.verify(gui, Mockito.times(3)).drawString(Mockito.anyInt(), Mockito.anyInt(), Mockito.eq("foo"));
        Mockito.verify(gui, Mockito.times(2)).drawString(1, 2, "foo");
    }
}
