package viewer.menu.element;

import gui.Color;
import gui.GUI;
import gui.GUIMockTestHelper;
import model.Position;
import model.menu.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MenuTitleViewerTest {
    private GUI gui;
    private Color backgroundColors[][] = new Color[10][10];
    private Color foregroundColors[][] = new Color[10][10];
    private char characters[][] = new char[10][10];
    private Menu menu;
    private Color menuColor;

    @BeforeEach
    void setUp() {
        this.gui = new GUIMockTestHelper(backgroundColors, foregroundColors, characters).mock();

        menuColor = new Color("#012345");
        menu = Mockito.mock(Menu.class);
        Mockito.when(menu.getColor()).thenReturn(menuColor);
        Mockito.when(menu.getTitle()).thenReturn("TITLE");
        Mockito.when(menu.getTopLeftPosition()).thenReturn(new Position(2,0));
    }

    @Test
    void draw() {
        Color BLACK = Color.BLACK;
        Color WHITE = Color.WHITE;
        Color TITLE = MenuTitleViewer.TITLE_COLOR;

        MenuTitleViewer viewer = new MenuTitleViewer();
        viewer.draw(this.menu, new Position(0,0), this.gui);

        Color expectedBg[][] = {
                {BLACK, BLACK, menuColor, menuColor, menuColor, menuColor, menuColor, BLACK, BLACK, BLACK},
                {BLACK, BLACK, menuColor, menuColor, menuColor, menuColor, menuColor, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
        };

        Color expectedFg[][] = {
                {WHITE, WHITE, TITLE, TITLE, TITLE, TITLE, TITLE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, TITLE, TITLE, TITLE, TITLE, TITLE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
        };

        char expectedChars[][] = {
                {' ', ' ', 'T', 'I', 'T', 'L', 'E', ' ', ' ', ' '},
                {' ', ' ', '-', '-', '-', '-', '-', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        };

        for (int i = 0; i < 10; i++) {
            Assertions.assertArrayEquals(expectedBg[i], this.backgroundColors[i]);
            Assertions.assertArrayEquals(expectedFg[i], this.foregroundColors[i]);
            Assertions.assertArrayEquals(expectedChars[i], this.characters[i]);
        }

    }

}
