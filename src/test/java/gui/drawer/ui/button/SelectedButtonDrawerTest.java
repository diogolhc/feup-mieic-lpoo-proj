package gui.drawer.ui.button;

import gui.Color;
import gui.GUI;
import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SelectedButtonDrawerTest {
/*
    private GUI gui;
    private Color backgroundColors[][] = new Color[10][10];
    private Color foregroundColors[][] = new Color[10][10];
    private char characters[][] = new char[10][10];

    @BeforeEach
    void setUp() {
        this.gui = new GUIMockTestHelper(backgroundColors, foregroundColors, characters).mock();
    }

    @Test
    void drawSelectedButtonInPosition() {
        Color BLACK = Color.BLACK;
        Color WHITE = Color.WHITE;
        Color BACK = new Color("#cfd8dc");
        Color FRONT = new Color("#00c853");

        SelectedButtonDrawer buttonDrawer = new SelectedButtonDrawer(gui, "Hello", 7, 7);
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
