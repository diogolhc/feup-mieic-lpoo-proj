package viewer.menu.element;

import gui.Color;
import gui.GUI;
import gui.GUIMockTestHelper;
import model.Position;
import model.menu.Button;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ButtonViewerTest {
    private GUI gui;
    private Color backgroundColors[][] = new Color[10][10];
    private Color foregroundColors[][] = new Color[10][10];
    private char characters[][] = new char[10][10];
    private Button button;

    @BeforeEach
    void setUp() {
        this.gui = new GUIMockTestHelper(this.backgroundColors, this.foregroundColors, this.characters).mock();


        this.button = Mockito.mock(Button.class);
        Mockito.when(this.button.getTopLeft()).thenReturn(new Position(1,0));
        Mockito.when(this.button.getTitle()).thenReturn("BT");
        Mockito.when(this.button.getHeight()).thenReturn(Button.BUTTON_HEIGHT);
        Mockito.when(this.button.getWidth()).thenReturn(8);
    }

    @Test
    void drawSelected() {
        Mockito.when(this.button.isSelected()).thenReturn(true);

        Color BLACK = Color.BLACK;
        Color WHITE = Color.WHITE;
        Color FOREGROUND = ButtonViewer.SELECTED_BUTTON_FOREGROUND;
        Color BACKGROUND = ButtonViewer.SELECTED_BUTTON_BACKGROUND;

        ButtonViewer viewer = new ButtonViewer();
        viewer.draw(new Position(0,2), this.button, this.gui);


        Color expectedBg[][] = {
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BLACK},
                {BLACK, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BLACK},
                {BLACK, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
        };

        Color expectedFg[][] = {
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, FOREGROUND, FOREGROUND, FOREGROUND, FOREGROUND, FOREGROUND, FOREGROUND, FOREGROUND, FOREGROUND, WHITE},
                {WHITE, FOREGROUND, null, null, FOREGROUND, FOREGROUND, null, null, FOREGROUND, WHITE},
                {WHITE, FOREGROUND, FOREGROUND, FOREGROUND, FOREGROUND, FOREGROUND, FOREGROUND, FOREGROUND, FOREGROUND, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
        };

        char expectedChars[][] = {
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', '+', '-', '-', '-', '-', '-', '-', '+', ' '},
                {' ', '|', ' ', ' ', 'B', 'T', ' ', ' ', '|', ' '},
                {' ', '+', '-', '-', '-', '-', '-', '-', '+', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        };

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Assertions.assertEquals(expectedBg[i][j], this.backgroundColors[i][j]);
                Assertions.assertEquals(expectedChars[i][j], this.characters[i][j]);

                if (expectedFg[i][j] != null) {
                    Assertions.assertEquals(expectedFg[i][j], this.foregroundColors[i][j]);
                }
            }
        }

    }

    @Test
    void drawUnselected() {
        Mockito.when(this.button.isSelected()).thenReturn(false);

        Color BLACK = Color.BLACK;
        Color WHITE = Color.WHITE;
        Color FOREGROUND = ButtonViewer.UNSELECTED_BUTTON_FOREGROUND;
        Color BACKGROUND = ButtonViewer.UNSELECTED_BUTTON_BACKGROUND;

        ButtonViewer viewer = new ButtonViewer();
        viewer.draw(new Position(0,2), this.button, this.gui);


        Color expectedBg[][] = {
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BLACK},
                {BLACK, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BLACK},
                {BLACK, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
        };

        Color expectedFg[][] = {
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, FOREGROUND, FOREGROUND, FOREGROUND, FOREGROUND, FOREGROUND, FOREGROUND, FOREGROUND, FOREGROUND, WHITE},
                {WHITE, FOREGROUND, null, null, FOREGROUND, FOREGROUND, null, null, FOREGROUND, WHITE},
                {WHITE, FOREGROUND, FOREGROUND, FOREGROUND, FOREGROUND, FOREGROUND, FOREGROUND, FOREGROUND, FOREGROUND, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
        };

        char expectedChars[][] = {
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', '+', '-', '-', '-', '-', '-', '-', '+', ' '},
                {' ', '|', ' ', ' ', 'B', 'T', ' ', ' ', '|', ' '},
                {' ', '+', '-', '-', '-', '-', '-', '-', '+', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        };

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Assertions.assertEquals(expectedBg[i][j], this.backgroundColors[i][j]);
                Assertions.assertEquals(expectedChars[i][j], this.characters[i][j]);

                if (expectedFg[i][j] != null) {
                    Assertions.assertEquals(expectedFg[i][j], this.foregroundColors[i][j]);
                }
            }
        }


    }
}
