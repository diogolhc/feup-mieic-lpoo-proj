package gui.drawer.shape;

import gui.Color;
import gui.GUI;
import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HorizontalLineDrawerTest {
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

        HorizontalLineDrawer drawer = new HorizontalLineDrawer(gui, BACK, FRONT, 'x');
        drawer.draw(new Position(0, 0), 5);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0 && i <= 4) {
                    Assertions.assertEquals(BACK, backgroundColors[j][i]);
                    Assertions.assertEquals(FRONT, foregroundColors[j][i]);
                    Assertions.assertEquals('x', characters[j][i]);
                } else {
                    Assertions.assertEquals(BLACK, backgroundColors[j][i]);
                    Assertions.assertEquals(WHITE, foregroundColors[j][i]);
                    Assertions.assertEquals(' ', characters[j][i]);
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

        HorizontalLineDrawer drawer = new HorizontalLineDrawer(gui, BACK, FRONT, 'x');
        drawer.draw(new Position(6, 3), 2);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 3 && i >= 6 && i <= 7) {
                    Assertions.assertEquals(BACK, backgroundColors[j][i]);
                    Assertions.assertEquals(FRONT, foregroundColors[j][i]);
                    Assertions.assertEquals('x', characters[j][i]);
                } else {
                    Assertions.assertEquals(BLACK, backgroundColors[j][i]);
                    Assertions.assertEquals(WHITE, foregroundColors[j][i]);
                    Assertions.assertEquals(' ', characters[j][i]);
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

        HorizontalLineDrawer drawer = new HorizontalLineDrawer(gui, BACK, FRONT, 'x');
        drawer.draw(new Position(2, 3), 1);
        drawer.draw(new Position(4, 2), 6);
        drawer.draw(new Position(4, 7), 3);
        drawer.draw(new Position(4, 7), 6);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((i >= 4 && i <= 9 && (j == 2 || j == 7)) || (i == 2 && j == 3)) {
                    Assertions.assertEquals(BACK, backgroundColors[j][i]);
                    Assertions.assertEquals(FRONT, foregroundColors[j][i]);
                    Assertions.assertEquals('x', characters[j][i]);
                } else {
                    Assertions.assertEquals(BLACK, backgroundColors[j][i]);
                    Assertions.assertEquals(WHITE, foregroundColors[j][i]);
                    Assertions.assertEquals(' ', characters[j][i]);
                }
            }
        }
    }

    @Test
    void drawDifferent() {
        Color BLACK = new Color("#000000");
        Color WHITE = new Color("#FFFFFF");
        Color BACK1 = new Color("#123456");
        Color FRONT1 = new Color("#FEDCBA");
        Color BACK2 = new Color("#123457");
        Color FRONT2 = new Color("#FEDCBB");
        Color BACK3 = new Color("#123458");
        Color FRONT3 = new Color("#FEDCBC");

        HorizontalLineDrawer drawer1 = new HorizontalLineDrawer(gui, BACK1, FRONT1, 'x');
        HorizontalLineDrawer drawer2 = new HorizontalLineDrawer(gui, BACK2, FRONT2, 'y');
        HorizontalLineDrawer drawer3 = new HorizontalLineDrawer(gui, BACK3, FRONT3, 'z');
        drawer1.draw(new Position(0, 0), 10);
        drawer2.draw(new Position(0, 1), 10);
        drawer3.draw(new Position(0, 2), 10);
        drawer1.draw(new Position(1, 3), 5);
        drawer1.draw(new Position(1, 4), 5);
        drawer2.draw(new Position(6, 3), 4);
        drawer2.draw(new Position(2, 4), 1);
        drawer3.draw(new Position(4, 4), 3);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0 || (j == 3 && i >= 1 && i <= 5) || (j == 4 && (i == 1 || i == 3))) {
                    Assertions.assertEquals(BACK1, backgroundColors[j][i]);
                    Assertions.assertEquals(FRONT1, foregroundColors[j][i]);
                    Assertions.assertEquals('x', characters[j][i]);
                } else if (j == 1 || (j == 3 && i >= 6) || (j == 4 && i == 2)) {
                    Assertions.assertEquals(BACK2, backgroundColors[j][i]);
                    Assertions.assertEquals(FRONT2, foregroundColors[j][i]);
                    Assertions.assertEquals('y', characters[j][i]);
                } else if (j == 2 || (j == 4 && i >= 4 && i <= 6)) {
                    Assertions.assertEquals(BACK3, backgroundColors[j][i]);
                    Assertions.assertEquals(FRONT3, foregroundColors[j][i]);
                    Assertions.assertEquals('z', characters[j][i]);
                } else {
                    Assertions.assertEquals(BLACK, backgroundColors[j][i]);
                    Assertions.assertEquals(WHITE, foregroundColors[j][i]);
                    Assertions.assertEquals(' ', characters[j][i]);
                }
            }
        }
    }
}
