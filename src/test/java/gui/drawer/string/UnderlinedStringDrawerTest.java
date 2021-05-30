package gui.drawer.string;

import gui.Color;
import gui.GUI;
import gui.GUIMockTestHelper;
import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnderlinedStringDrawerTest {
    private GUI gui;
    private Color backgroundColors[][] = new Color[10][10];
    private Color foregroundColors[][] = new Color[10][10];
    private char characters[][] = new char[10][10];

    @BeforeEach
    void setUp() {
        this.gui = new GUIMockTestHelper(this.backgroundColors, this.foregroundColors, this.characters).mock();
    }

    @Test
    void draw() {
        Color BACK = new Color("#123456");
        Color BLACK = Color.BLACK;
        Color FRONT = new Color("#FEDCBA");
        Color WHITE = Color.WHITE;

        UnderlinedStringDrawer drawer = new UnderlinedStringDrawer(this.gui, BACK, FRONT, '_');
        drawer.draw(new Position(5, 7), "HELLO");

        Color expectedBg[][] = {
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BACK, BACK, BACK, BACK, BACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BACK, BACK, BACK, BACK, BACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK}
        };

        Color expectedFg[][] = {
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, FRONT, FRONT, FRONT, FRONT, FRONT},
                {WHITE, WHITE, WHITE, WHITE, WHITE, FRONT, FRONT, FRONT, FRONT, FRONT},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE}
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
    void drawMultiple() {
        Color BLACK = Color.BLACK;
        Color WHITE = Color.WHITE;
        Color BACK1 = new Color("#123456");
        Color BACK2 = new Color("#144436");
        Color BACK3 = new Color("#123999");
        Color FRONT1 = new Color("#FEDCAA");
        Color FRONT2 = new Color("#FEDCBA");
        Color FRONT3 = new Color("#FEDEBA");

        char c1 = '_';
        char c2 = '-';
        char c3 = '=';

        UnderlinedStringDrawer drawer = new UnderlinedStringDrawer(this.gui, BACK1, FRONT1, c1);
        UnderlinedStringDrawer drawer2 = new UnderlinedStringDrawer(this.gui, BACK2, FRONT2, c2);
        UnderlinedStringDrawer drawer3 = new UnderlinedStringDrawer(this.gui, BACK3, FRONT3, c3);

        drawer.draw(new Position(5, 7), "HELLO");
        drawer2.draw(new Position(0, 0), "HI");
        drawer3.draw(new Position(3, 3), "OIE");

        Color expectedBg[][] = {
                {BACK2, BACK2, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BACK2, BACK2, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BACK3, BACK3, BACK3, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BACK3, BACK3, BACK3, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BACK1, BACK1, BACK1, BACK1, BACK1},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BACK1, BACK1, BACK1, BACK1, BACK1},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK}
        };

        Color expectedFg[][] = {
                {FRONT2, FRONT2, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {FRONT2, FRONT2, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, FRONT3, FRONT3, FRONT3, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, FRONT3, FRONT3, FRONT3, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, FRONT1, FRONT1, FRONT1, FRONT1, FRONT1},
                {WHITE, WHITE, WHITE, WHITE, WHITE, FRONT1, FRONT1, FRONT1, FRONT1, FRONT1},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE}
        };

        char expectedChars[][] = {
                {'H', 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {c2, c2, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'O', 'I', 'E', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', c3, c3, c3, ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'H', 'E', 'L', 'L', 'O'},
                {' ', ' ', ' ', ' ', ' ', c1, c1, c1, c1, c1},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        };

        for (int i = 0; i < 10; i++) {
            Assertions.assertArrayEquals(expectedBg[i], this.backgroundColors[i]);
            Assertions.assertArrayEquals(expectedChars[i], this.characters[i]);
            Assertions.assertArrayEquals(expectedFg[i], this.foregroundColors[i]);
        }
    }
}

