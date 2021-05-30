package viewer.farm.element.entity;

import gui.Color;
import gui.GUI;
import gui.GUIMockTestHelper;
import model.Position;
import model.farm.entity.Animal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


class AnimalViewerTest {
    private GUI gui;
    private Color backgroundColors[][] = new Color[10][10];
    private Color foregroundColors[][] = new Color[10][10];
    private char characters[][] = new char[10][10];
    private Animal animal;

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
        this.gui = new GUIMockTestHelper(this.backgroundColors, this.foregroundColors, this.characters).mock();
    }

    @Test
    void drawSingle() {
        Color WHITE = Color.WHITE;
        Color BLACK = Color.BLACK;
        Color ANIMAL = new Color("#012345");
        this.animal = Mockito.mock(Animal.class);
        Mockito.when(this.animal.getPosition()).thenReturn(new Position(2,1));


        AnimalViewer viewer = new AnimalViewer();
        viewer.draw(this.animal, 'A', ANIMAL, this.gui);


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 2 && j == 1) {
                    Assertions.assertEquals('A', this.characters[j][i]);
                    Assertions.assertEquals(ANIMAL, this.foregroundColors[j][i]);
                } else {
                    Assertions.assertEquals(' ', this.characters[j][i]);
                    Assertions.assertEquals(WHITE, this.foregroundColors[j][i]);
                }
                Assertions.assertEquals(BLACK, this.backgroundColors[j][i]);
            }
        }
    }

    @Test
    void drawOnBackground() {
        Color WHITE = Color.WHITE;
        Color RED = new Color("#FF0000");
        Color BLUE = new Color("#0000FF");
        Color ANIMAL = new Color("#012345");

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

        this.animal = Mockito.mock(Animal.class);
        Mockito.when(this.animal.getPosition()).thenReturn(new Position(4,6));

        AnimalViewer viewer = new AnimalViewer();
        viewer.draw(this.animal, 'A', ANIMAL, this.gui);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 4 && j == 6) {
                    Assertions.assertEquals('A', this.characters[j][i]);
                    Assertions.assertEquals(ANIMAL, this.foregroundColors[j][i]);
                } else {
                    Assertions.assertEquals(' ', this.characters[j][i]);
                    Assertions.assertEquals(WHITE, this.foregroundColors[j][i]);
                }

                Assertions.assertEquals(expectedBg[j][i], this.backgroundColors[j][i]);
            }
        }
    }

    @Test
    void drawSeveral() {
        Color BLACK = Color.BLACK;
        Color WHITE = Color.WHITE;
        Color ANIMAL = new Color("#012345");
        this.animal = Mockito.mock(Animal.class);

        AnimalViewer viewer = new AnimalViewer();
        Mockito.when(this.animal.getPosition()).thenReturn(new Position(2,1));
        viewer.draw(this.animal, 'A', ANIMAL, this.gui);
        Mockito.when(this.animal.getPosition()).thenReturn(new Position(4,2));
        viewer.draw(this.animal, 'B', ANIMAL, this.gui);
        Mockito.when(this.animal.getPosition()).thenReturn(new Position(5,3));
        viewer.draw(this.animal, 'C', ANIMAL, this.gui);


        Color expectedBg[][] = {
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
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
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, ANIMAL, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, ANIMAL, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, ANIMAL, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
        };

        char expectedChars[][] = {
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'A', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'B', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'C', ' ', ' ', ' ', ' '},
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
