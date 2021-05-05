package gui.drawer.string;

import gui.Color;
import gui.GUI;
import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UnderlinedStringDrawerTest {
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
    void drawUnderlined() {
        Color BACK = new Color("#123456");
        Color DEFAULT = new Color("#000000");
        Color FRONT = new Color("#FEDCBA");
        Color BLACK = new Color("#FFFFFF");

        char character = '_';
        UnderlinedStringDrawer drawer = new UnderlinedStringDrawer(gui, BACK, FRONT, character);
        drawer.draw(new Position(5, 7), "HELLO");

        Color expectedBg[][] = {
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, BACK, BACK, BACK, BACK, BACK},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, BACK, BACK, BACK, BACK, BACK},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT}
        };

        Color expectedFg[][] = {
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, FRONT, FRONT, FRONT, FRONT, FRONT},
                {BLACK, BLACK, BLACK, BLACK, BLACK, FRONT, FRONT, FRONT, FRONT, FRONT},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK}
        };

        char expectedChars[][] = {
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'H', 'E', 'L', 'L', 'O'},
                {' ', ' ', ' ', ' ', ' ', '_', '_', '_', '_', '_'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        };

        for (int i = 0; i < 10; i++) {
            Assertions.assertArrayEquals(expectedBg[i], this.backgroundColors[i]);
            Assertions.assertArrayEquals(expectedChars[i], this.characters[i]);
            Assertions.assertArrayEquals(expectedFg[i], this.foregroundColors[i]);
        }
    }

    @Test
    void drawMultipleUnderlined() {
        Color BACK = new Color("#123456");
        Color BACK2 = new Color("#144436");
        Color BACK3 = new Color("#123999");

        Color DEFAULT = new Color("#000000");

        Color FRONT = new Color("#FEDCAA");
        Color FRONT2 = new Color("#FEDCBA");
        Color FRONT3 = new Color("#FEDEBA");

        Color BLACK = new Color("#FFFFFF");

        char character = '_';
        char character1 = '-';
        char character2 = '=';

        UnderlinedStringDrawer drawer = new UnderlinedStringDrawer(gui, BACK, FRONT, character);
        UnderlinedStringDrawer drawer2 = new UnderlinedStringDrawer(gui, BACK2, FRONT2, character1);
        UnderlinedStringDrawer drawer3 = new UnderlinedStringDrawer(gui, BACK3, FRONT3, character2);

        drawer.draw(new Position(5, 7), "HELLO");
        drawer2.draw(new Position(0, 0), "HI");
        drawer3.draw(new Position(3, 3), "OIE");

        Color expectedBg[][] = {
                {BACK2, BACK2, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {BACK2, BACK2, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, BACK3, BACK3, BACK3, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, BACK3, BACK3, BACK3, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, BACK, BACK, BACK, BACK, BACK},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, BACK, BACK, BACK, BACK, BACK},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT}
        };

        Color expectedFg[][] = {
                {FRONT2, FRONT2, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {FRONT2, FRONT2, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, FRONT3, FRONT3, FRONT3, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, FRONT3, FRONT3, FRONT3, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, FRONT, FRONT, FRONT, FRONT, FRONT},
                {BLACK, BLACK, BLACK, BLACK, BLACK, FRONT, FRONT, FRONT, FRONT, FRONT},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK}
        };

        char expectedChars[][] = {
                {'H', 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {character1, character1, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'O', 'I', 'E', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', character2, character2, character2, ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'H', 'E', 'L', 'L', 'O'},
                {' ', ' ', ' ', ' ', ' ', character, character, character, character, character},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        };

        for (int i = 0; i < 10; i++) {
            Assertions.assertArrayEquals(expectedBg[i], this.backgroundColors[i]);
            Assertions.assertArrayEquals(expectedChars[i], this.characters[i]);
            Assertions.assertArrayEquals(expectedFg[i], this.foregroundColors[i]);
        }


    }
}
