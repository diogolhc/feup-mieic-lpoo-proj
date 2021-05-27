package gui.drawer.ui.button;

import gui.Color;
import gui.GUI;
import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UnselectedButtonDrawerTest {
/*
    private GUI gui;
    private Color backgroundColors[][] = new Color[10][10];
    private Color foregroundColors[][] = new Color[10][10];
    private char characters[][] = new char[10][10];
    private Color currentBackgroundColor = Color.BLACK;
    private Color currentForegroundColor = Color.WHITE;

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
    void drawUnselectedButtonAtPosition() {
        Color BLACK = Color.BLACK;
        Color WHITE = Color.WHITE;
        Color BACK = new Color("#90a4ae");
        Color FRONT = new Color("#263238");

        UnselectedButtonDrawer buttonDrawer = new UnselectedButtonDrawer(gui, "Hello", 7, 7);
        buttonDrawer.draw(new Position(2, 2));

        char HORIZONTAL_LINE = '-';
        char VERTICAL_LINE = '|';
        char CORNER_LINE = '+';

        Color expectedBg[][] = {
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BACK, BACK, BACK, BACK, BACK, BACK, BACK, BLACK},
                {BLACK, BLACK, BACK, BACK, BACK, BACK, BACK, BACK, BACK, BLACK},
                {BLACK, BLACK, BACK, BLACK, BLACK, BLACK, BLACK, BLACK, BACK, BLACK},
                {BLACK, BLACK, BACK, BLACK, BLACK, BLACK, BLACK, BLACK, BACK, BLACK},
                {BLACK, BLACK, BACK, BLACK, BLACK, BLACK, BLACK, BLACK, BACK, BLACK},
                {BLACK, BLACK, BACK, BLACK, BLACK, BLACK, BLACK, BLACK, BACK, BLACK},
                {BLACK, BLACK, BACK, BACK, BACK, BACK, BACK, BACK, BACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK}
        };

        Color expectedFg[][] = {
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, FRONT, FRONT, FRONT, FRONT, FRONT, FRONT, FRONT, WHITE},
                {WHITE, WHITE, FRONT, FRONT, FRONT, FRONT, FRONT, FRONT, FRONT, WHITE},
                {WHITE, WHITE, FRONT, WHITE, WHITE, WHITE, WHITE, WHITE, FRONT, WHITE},
                {WHITE, WHITE, FRONT, WHITE, WHITE, WHITE, WHITE, WHITE, FRONT, WHITE},
                {WHITE, WHITE, FRONT, WHITE, WHITE, WHITE, WHITE, WHITE, FRONT, WHITE},
                {WHITE, WHITE, FRONT, WHITE, WHITE, WHITE, WHITE, WHITE, FRONT, WHITE},
                {WHITE, WHITE, FRONT, FRONT, FRONT, FRONT, FRONT, FRONT, FRONT, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE}
        };

        char expectedChars[][] = {
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', CORNER_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, CORNER_LINE, ' '},
                {' ', ' ', VERTICAL_LINE, 'H', 'e', 'l', 'l', 'o', VERTICAL_LINE, ' '},
                {' ', ' ', VERTICAL_LINE, ' ', ' ', ' ', ' ', ' ', VERTICAL_LINE, ' '},
                {' ', ' ', VERTICAL_LINE, ' ', ' ', ' ', ' ', ' ', VERTICAL_LINE, ' '},
                {' ', ' ', VERTICAL_LINE, ' ', ' ', ' ', ' ', ' ', VERTICAL_LINE, ' '},
                {' ', ' ', VERTICAL_LINE, ' ', ' ', ' ', ' ', ' ', VERTICAL_LINE, ' '},
                {' ', ' ', CORNER_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, CORNER_LINE, ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        };

        for (int i = 0; i < 10; i++) {
            Assertions.assertArrayEquals(expectedBg[i], this.backgroundColors[i]);
            Assertions.assertArrayEquals(expectedChars[i], this.characters[i]);
            Assertions.assertArrayEquals(expectedFg[i], this.foregroundColors[i]);
        }
    }

 */
}
