package gui.drawer.shape;

import gui.Color;
import gui.GUI;
import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class RectangleDrawerTest {

    private GUI gui;
    private Color backgroundColors[][] = new Color[10][10];
    private Color foregroundColors[][] = new Color[10][10];
    private char characters[][] = new char[10][10];
    private Color currentBackgroundColor = new Color("#000000");
    private Color currentForegroundColor = new Color("#FFFFFF");

    @BeforeEach
    void setUp() {
        this.gui = Mockito.mock(GUI.class);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.backgroundColors[i][j] = this.currentBackgroundColor;
                this.foregroundColors[i][j] = this.currentForegroundColor;
                this.characters[i][j] = ' ';
            }
        }

        Mockito.doAnswer(invocation -> {
            this.currentBackgroundColor = invocation.getArgument(0);
            return null;
        }).when(gui).setBackgroundColor(Mockito.any());
        Mockito.doAnswer(invocation -> {
            this.currentForegroundColor = invocation.getArgument(0);
            return null;
        }).when(gui).setForegroundColor(Mockito.any());
        Mockito.doAnswer(invocation -> {
            int x = invocation.getArgument(0);
            int y = invocation.getArgument(1);
            char c = invocation.getArgument(2);
            this.backgroundColors[y][x] = currentBackgroundColor;
            this.foregroundColors[y][x] = currentForegroundColor;
            this.characters[y][x] = c;
            return null;
        }).when(gui).drawChar(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyChar());
        Mockito.doAnswer(invocation -> {
            String s = invocation.getArgument(2);
            int x = invocation.getArgument(0);
            int y = invocation.getArgument(1);
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                this.backgroundColors[y][x] = currentBackgroundColor;
                this.foregroundColors[y][x] = currentForegroundColor;
                this.characters[y][x] = c;
                x += 1;
            }
            return null;
        }).when(gui).drawString(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString());
        Mockito.doAnswer(invocation -> {
            int x = invocation.getArgument(0);
            int y = invocation.getArgument(1);
            return this.backgroundColors[y][x];
        }).when(gui).getBackgroundColor(Mockito.anyInt(), Mockito.anyInt());
        Mockito.doAnswer(invocation -> {
            int x = invocation.getArgument(0);
            int y = invocation.getArgument(1);
            return this.foregroundColors[y][x];
        }).when(gui).getForegroundColor(Mockito.anyInt(), Mockito.anyInt());
    }

    @Test
    void drawAtOrigin() {
        Color BLACK = new Color("#000000");
        Color WHITE = new Color("#FFFFFF");
        Color BACK = new Color("#123456");
        Color FRONT = new Color("#FEDCBA");

        RectangleDrawer drawer = new RectangleDrawer(gui, BACK, FRONT, 'x');
        drawer.draw(new Position(0, 0), 5, 5);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((i == 0 || i == 4) && j <= 4) {
                    Assertions.assertEquals(BACK, backgroundColors[i][j]);
                    Assertions.assertEquals(FRONT, foregroundColors[i][j]);
                    Assertions.assertEquals('x', characters[i][j]);
                } else if ( i <= 4 && (j == 0 || j == 4) ) {
                    Assertions.assertEquals(BACK, backgroundColors[i][j]);
                    Assertions.assertEquals(FRONT, foregroundColors[i][j]);
                    Assertions.assertEquals('x', characters[i][j]);
                } else {
                    Assertions.assertEquals(BLACK, backgroundColors[i][j]);
                    Assertions.assertEquals(WHITE, foregroundColors[i][j]);
                    Assertions.assertEquals(' ', characters[i][j]);
                }
            }
        }
    }

    @Test
    void drawOtherPosition() {
        Color BLACK = new Color("#000000");
        Color WHITE = new Color("#FFFFFF");
        Color BACK = new Color("#123456");
        Color FRONT = new Color("#FEDCBA");

        RectangleDrawer drawer = new RectangleDrawer(gui, BACK, FRONT, 'x');
        drawer.draw(new Position(3, 3), 5, 3);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((i == 3 || i == 5) && (j >= 3 && j <= 7)) {
                    Assertions.assertEquals(BACK, backgroundColors[i][j]);
                    Assertions.assertEquals(FRONT, foregroundColors[i][j]);
                    Assertions.assertEquals('x', characters[i][j]);
                } else if ( (i >= 3 && i <= 5) && (j == 3 || j == 7) ) {
                    Assertions.assertEquals(BACK, backgroundColors[i][j]);
                    Assertions.assertEquals(FRONT, foregroundColors[i][j]);
                    Assertions.assertEquals('x', characters[i][j]);
                } else {
                    Assertions.assertEquals(BLACK, backgroundColors[i][j]);
                    Assertions.assertEquals(WHITE, foregroundColors[i][j]);
                    Assertions.assertEquals(' ', characters[i][j]);
                }
            }
        }
    }

    @Test
    void drawMultiple() {
        Color BLACK = new Color("#000000");
        Color WHITE = new Color("#FFFFFF");
        Color BACK = new Color("#123456");
        Color FRONT = new Color("#FEDCBA");
        Color BACK2 = new Color("#123457");
        Color FRONT2 = new Color("#FEDCBB");


        RectangleDrawer drawer = new RectangleDrawer(gui, BACK, FRONT, 'x');
        RectangleDrawer drawer2 = new RectangleDrawer(gui, BACK2, FRONT2, 'y');
        drawer.draw(new Position(6, 3), 3, 3);
        drawer2.draw(new Position(5, 2), 5, 2);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ( (i == 2 || i == 3) && (j >= 5 && j <= 9)) {
                    Assertions.assertEquals(BACK2, backgroundColors[i][j]);
                    Assertions.assertEquals(FRONT2, foregroundColors[i][j]);
                    Assertions.assertEquals('y', characters[i][j]);
                } else if ( (i >= 2 && i <= 3) && (j == 5 || j == 9)) {
                    Assertions.assertEquals(BACK2, backgroundColors[i][j]);
                    Assertions.assertEquals(FRONT2, foregroundColors[i][j]);
                    Assertions.assertEquals('y', characters[i][j]);
                } else if ((i == 3 || i == 5) && (j >= 6 && j <= 8)) {
                    Assertions.assertEquals(BACK, backgroundColors[i][j]);
                    Assertions.assertEquals(FRONT, foregroundColors[i][j]);
                    Assertions.assertEquals('x', characters[i][j]);
                } else if ( (i >= 3 && i <= 5) && (j == 6 || j == 8) ) {
                    Assertions.assertEquals(BACK, backgroundColors[i][j]);
                    Assertions.assertEquals(FRONT, foregroundColors[i][j]);
                    Assertions.assertEquals('x', characters[i][j]);
                } else {
                    Assertions.assertEquals(BLACK, backgroundColors[i][j]);
                    Assertions.assertEquals(WHITE, foregroundColors[i][j]);
                    Assertions.assertEquals(' ', characters[i][j]);
                }
            }
        }
    }
}
