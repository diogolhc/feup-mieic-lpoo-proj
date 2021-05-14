package gui.drawer.entity;

import gui.Color;
import gui.GUI;
import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CropFieldDrawerTest {
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
    void drawNoCropAtOrigin() {
        Color BLACK = new Color("#000000");
        Color WHITE = new Color("#FFFFFF");
        Color PATH = new Color("#be9b7b");
        Color SOIL_COLOR = new Color("#372201");

        CropFieldDrawer drawer = new CropFieldDrawer(gui);
        drawer.draw(new Position(0, 0), WHITE, ' ');

        Color expectedBg[][] = {
                {PATH, PATH, PATH, PATH, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {PATH, SOIL_COLOR, SOIL_COLOR, PATH, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {PATH, SOIL_COLOR, SOIL_COLOR, PATH, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {PATH, PATH, PATH, PATH, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
        };

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Assertions.assertEquals(' ', this.characters[j][i]);
                Assertions.assertEquals(expectedBg[j][i], this.backgroundColors[j][i]);
            }
        }
    }

    @Test
    void drawNoCropAtPosition() {
        Color BLACK = new Color("#000000");
        Color WHITE = new Color("#FFFFFF");
        Color PATH = new Color("#be9b7b");
        Color SOIL_COLOR = new Color("#372201");

        CropFieldDrawer drawer = new CropFieldDrawer(gui);
        drawer.draw(new Position(2, 2), WHITE, ' ');

        Color expectedBg[][] = {
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, PATH, PATH, PATH, PATH, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, PATH, SOIL_COLOR, SOIL_COLOR, PATH, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, PATH, SOIL_COLOR, SOIL_COLOR, PATH, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, PATH, PATH, PATH, PATH, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
        };


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Assertions.assertEquals(' ', this.characters[j][i]);
                Assertions.assertEquals(expectedBg[j][i], this.backgroundColors[j][i]);
            }
        }
    }

    @Test
    void drawWithCrop() {
        Color BLACK = new Color("#000000");
        Color PATH = new Color("#be9b7b");
        Color SOIL_COLOR = new Color("#372201");
        Color STAGE_COLOR = new Color("#696606");

        CropFieldDrawer drawer = new CropFieldDrawer(gui);
        drawer.draw(new Position(2, 2), STAGE_COLOR, '#');

        Color expectedBg[][] = {
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, PATH, PATH, PATH, PATH, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, PATH, SOIL_COLOR, SOIL_COLOR, PATH, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, PATH, SOIL_COLOR, SOIL_COLOR, PATH, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, PATH, PATH, PATH, PATH, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
        };

        char expectedChars[][] = {
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', '#', '#', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', '#', '#', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        };

        for (int i = 0; i < 10; i++) {
            Assertions.assertArrayEquals(expectedBg[i], this.backgroundColors[i]);
            Assertions.assertArrayEquals(expectedChars[i], this.characters[i]);
        }

        Assertions.assertEquals(STAGE_COLOR, this.foregroundColors[3][3]);
        Assertions.assertEquals(STAGE_COLOR, this.foregroundColors[3][4]);
        Assertions.assertEquals(STAGE_COLOR, this.foregroundColors[4][3]);
        Assertions.assertEquals(STAGE_COLOR, this.foregroundColors[4][4]);
    }
}
