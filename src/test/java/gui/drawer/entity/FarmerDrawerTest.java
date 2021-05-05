package gui.drawer.entity;

import gui.Color;
import gui.GUI;
import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class FarmerDrawerTest {
    private GUI gui;
    private Color backgroundColors[][] = new Color[10][10];
    private Color foregroundColors[][] = new Color[10][10];
    private char characters[][] = new char[10][10];
    private Color currentBackgroundColor = new Color("#000000");
    private Color currentForegroundColor = new Color("#FFFFFF");

    private Color[][] cloneSquareBidimensionalArray(Color[][] array) {
        Color newArray[][] = new Color[array.length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                newArray[j][i] = array[j][i];
            }
        }
        return newArray;
    }

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
    void draw() {
        Color WHITE = new Color("#FFFFFF");
        Color FARMER_COLOR = new Color("#223366");

        Color expectedBg[][] = cloneSquareBidimensionalArray(this.backgroundColors);

        FarmerDrawer drawer = new FarmerDrawer(gui);
        drawer.draw(new Position(2, 3));

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 2 && j == 3) {
                    Assertions.assertEquals('@', this.characters[j][i]);
                    Assertions.assertEquals(FARMER_COLOR, this.foregroundColors[j][i]);
                } else {
                    Assertions.assertEquals(' ', this.characters[j][i]);
                    Assertions.assertEquals(WHITE, this.foregroundColors[j][i]);
                }

                Assertions.assertEquals(expectedBg[j][i], this.backgroundColors[j][i]);
            }
        }
    }

    @Test
    void drawOnBackground() {
        Color WHITE = new Color("#FFFFFF");
        Color RED = new Color("#FF0000");
        Color BLUE = new Color("#0000FF");
        Color FARMER_COLOR = new Color("#223366");

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 4 && j == 6) {
                    this.backgroundColors[j][i] = RED;
                } else {
                    this.backgroundColors[j][i] = BLUE;
                }
            }
        }

        Color expectedBg[][] = cloneSquareBidimensionalArray(this.backgroundColors);

        FarmerDrawer drawer = new FarmerDrawer(gui);
        drawer.draw(new Position(4, 6));

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 4 && j == 6) {
                    Assertions.assertEquals('@', this.characters[j][i]);
                    Assertions.assertEquals(FARMER_COLOR, this.foregroundColors[j][i]);
                } else {
                    Assertions.assertEquals(' ', this.characters[j][i]);
                    Assertions.assertEquals(WHITE, this.foregroundColors[j][i]);
                }

                Assertions.assertEquals(expectedBg[j][i], this.backgroundColors[j][i]);
            }
        }
    }
}
