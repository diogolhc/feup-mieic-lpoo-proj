package gui.drawer.shape;

import gui.Color;
import gui.GUI;
import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;

public class BoxDrawerTest {
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
    void drawBox() {
        Color DEFAULT = new Color("#000000");
        Color WHITE = new Color("#FFFFFF");
        Color BACK = new Color("#123456");
        Color FRONT = new Color("#FEDCBA");

        char HORIZONTAL_LINE = '-';
        char VERTICAL_LINE = '|';
        char CORNER_LINE = '+';
    
        BoxDrawer boxDrawer = new BoxDrawer(gui, BACK, FRONT);
        boxDrawer.draw(new Position(0, 0), 5, 5);

        Color expectedBg[][] = {
                {BACK, BACK, BACK, BACK, BACK, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {BACK, DEFAULT, DEFAULT, DEFAULT, BACK, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {BACK, DEFAULT, DEFAULT, DEFAULT, BACK, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {BACK, DEFAULT, DEFAULT, DEFAULT, BACK, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {BACK, BACK, BACK, BACK, BACK, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT}
        };

        Color expectedFg[][] = {
                {FRONT, FRONT, FRONT, FRONT, FRONT, WHITE, WHITE, WHITE, WHITE, WHITE},
                {FRONT, WHITE, WHITE, WHITE, FRONT, WHITE, WHITE, WHITE, WHITE, WHITE},
                {FRONT, WHITE, WHITE, WHITE, FRONT, WHITE, WHITE, WHITE, WHITE, WHITE},
                {FRONT, WHITE, WHITE, WHITE, FRONT, WHITE, WHITE, WHITE, WHITE, WHITE},
                {FRONT, FRONT, FRONT, FRONT, FRONT, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE}
        };

        char expectedChars[][] = {
                {CORNER_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, CORNER_LINE, ' ', ' ', ' ', ' ', ' '},
                {VERTICAL_LINE, ' ', ' ', ' ', VERTICAL_LINE, ' ', ' ', ' ', ' ', ' '},
                {VERTICAL_LINE, ' ', ' ', ' ', VERTICAL_LINE, ' ', ' ', ' ', ' ', ' '},
                {VERTICAL_LINE, ' ', ' ', ' ', VERTICAL_LINE, ' ', ' ', ' ', ' ', ' '},
                {CORNER_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, CORNER_LINE, ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        };

        for (int i = 0; i < 10; i++) {
            Assertions.assertArrayEquals(expectedBg[i], this.backgroundColors[i]);
            Assertions.assertArrayEquals(expectedChars[i], this.characters[i]);
            Assertions.assertArrayEquals(expectedFg[i], this.foregroundColors[i]);
        }
    }

    @Test
    void drawMultipleBoxes() {
        Color DEFAULT = new Color("#000000");
        Color WHITE = new Color("#FFFFFF");
        Color BACK = new Color("#123456");
        Color BACK2 = new Color("#144778");
        Color FRONT = new Color("#FEDCBA");
        Color FRONT2 = new Color("#FEFBAD");

        char HORIZONTAL_LINE = '-';
        char VERTICAL_LINE = '|';
        char CORNER_LINE = '+';

        BoxDrawer boxDrawer = new BoxDrawer(gui, BACK, FRONT);
        BoxDrawer boxDrawer2 = new BoxDrawer(gui, BACK2, FRONT2);

        boxDrawer.draw(new Position(0, 0), 5, 5);
        boxDrawer2.draw(new Position(3, 3), 4, 4);

        Color expectedBg[][] = {
                {BACK, BACK, BACK, BACK, BACK, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {BACK, DEFAULT, DEFAULT, DEFAULT, BACK, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {BACK, DEFAULT, DEFAULT, DEFAULT, BACK, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {BACK, DEFAULT, DEFAULT, BACK2, BACK2, BACK2, BACK2, DEFAULT, DEFAULT, DEFAULT},
                {BACK, BACK, BACK, BACK2, BACK, DEFAULT, BACK2, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, BACK2, DEFAULT, DEFAULT, BACK2, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, BACK2, BACK2, BACK2, BACK2, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT}
        };

        Color expectedFg[][] = {
                {FRONT, FRONT, FRONT, FRONT, FRONT, WHITE, WHITE, WHITE, WHITE, WHITE},
                {FRONT, WHITE, WHITE, WHITE, FRONT, WHITE, WHITE, WHITE, WHITE, WHITE},
                {FRONT, WHITE, WHITE, WHITE, FRONT, WHITE, WHITE, WHITE, WHITE, WHITE},
                {FRONT, WHITE, WHITE, FRONT2, FRONT2, FRONT2, FRONT2, WHITE, WHITE, WHITE},
                {FRONT, FRONT, FRONT, FRONT2, FRONT, WHITE, FRONT2, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, FRONT2, WHITE, WHITE, FRONT2, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, FRONT2, FRONT2, FRONT2, FRONT2, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE}
        };

        char expectedChars[][] = {
                {CORNER_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, CORNER_LINE, ' ', ' ', ' ', ' ', ' '},
                {VERTICAL_LINE, ' ', ' ', ' ', VERTICAL_LINE, ' ', ' ', ' ', ' ', ' '},
                {VERTICAL_LINE, ' ', ' ', ' ', VERTICAL_LINE, ' ', ' ', ' ', ' ', ' '},
                {VERTICAL_LINE, ' ', ' ', CORNER_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, CORNER_LINE, ' ', ' ', ' '},
                {CORNER_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, VERTICAL_LINE, CORNER_LINE, ' ', VERTICAL_LINE, ' ', ' ', ' '},
                {' ', ' ', ' ', VERTICAL_LINE, ' ', ' ', VERTICAL_LINE, ' ', ' ', ' '},
                {' ', ' ', ' ', CORNER_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, CORNER_LINE, ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        };

        for (int i = 0; i < 10; i++) {
            Assertions.assertArrayEquals(expectedBg[i], this.backgroundColors[i]);
            Assertions.assertArrayEquals(expectedChars[i], this.characters[i]);
            Assertions.assertArrayEquals(expectedFg[i], this.foregroundColors[i]);
        }
    }

}
