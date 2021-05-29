package viewer.farm.element.building;

import gui.Color;
import gui.GUI;
import gui.GUIMockTestHelper;
import model.Position;
import model.farm.building.stockyard.Stockyard;
import model.farm.data.Livestock;
import model.farm.entity.Animal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import viewer.farm.element.entity.AnimalViewer;


class StockyardViewerTest {
    private GUI gui;
    private Color backgroundColors[][] = new Color[10][10];
    private Color foregroundColors[][] = new Color[10][10];
    private char characters[][] = new char[10][10];
    private Stockyard stockyard;
    private Livestock livestock;

    private Color[][] cloneSquareBidimensionalArray(Color[][] array) {
        Color newArray[][] = new Color[array.length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                newArray[j][i] = array[j][i];
            }
        }
        return newArray;
    }

    private char[][] cloneSquareBidimensionalArray(char[][] array) {
        char newArray[][] = new char[array.length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                newArray[j][i] = array[j][i];
            }
        }
        return newArray;
    }

    @BeforeEach
    void setUp() {
        this.gui = new GUIMockTestHelper(backgroundColors, foregroundColors, characters).mock();


        this.livestock = Mockito.mock(Livestock.class);
        Mockito.when(this.livestock.getAnimalChar()).thenReturn('C');
        Mockito.when(this.livestock.getMaxNumAnimals()).thenReturn(5);
    }


    @Test
    void drawStockyardAtOrigin() {
        Mockito.when(this.livestock.getStockyardHeight()).thenReturn(6);
        Mockito.when(this.livestock.getStockyardWidth()).thenReturn(5);

        this.stockyard = new Stockyard(new Position(0,0), this.livestock);
        Color BLACK = Color.BLACK;
        Color WHITE = Color.WHITE;
        Color PATH = Color.HIGHLIGHTED_FLOOR;
        Color FENCE_DOOR = StockyardViewer.FENCE_DOOR_COLOR;
        Color GRASS = Color.GRASS;
        Color FENCE = Color.WOOD;

        StockyardViewer viewer = new StockyardViewer();
        viewer.draw(stockyard, gui);

        Color expectedFg[][] = {
                {WHITE, FENCE, FENCE, FENCE, FENCE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, FENCE, WHITE, WHITE, FENCE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, FENCE, WHITE, WHITE, FENCE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, FENCE_DOOR, WHITE, WHITE, FENCE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, FENCE, WHITE, WHITE, FENCE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, FENCE, FENCE, FENCE, FENCE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
        };

        Color expectedBg[][] = {
                {BLACK, GRASS, GRASS, GRASS, GRASS, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, GRASS, BLACK, BLACK, GRASS, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, GRASS, BLACK, BLACK, GRASS, BLACK, BLACK, BLACK, BLACK, BLACK},
                {PATH, GRASS, BLACK, BLACK, GRASS, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, GRASS, BLACK, BLACK, GRASS, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, GRASS, GRASS, GRASS, GRASS, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
        };

        char expectedChars[][] = {
                {' ', '+', '-', '-', '+', ' ', ' ', ' ', ' ', ' '},
                {' ', '|', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' '},
                {' ', '|', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' '},
                {' ', '|', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' '},
                {' ', '|', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' '},
                {' ', '+', '-', '-', '+', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        };

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Assertions.assertEquals(expectedChars[j][i], this.characters[j][i]);
                Assertions.assertEquals(expectedBg[j][i], this.backgroundColors[j][i]);
                if (!expectedFg[j][i].equals(WHITE)) {
                    Assertions.assertEquals(expectedFg[j][i], this.foregroundColors[j][i]);
                }
            }
        }
    }

    @Test
    void drawStockyardAtOtherPosition() {
        Mockito.when(this.livestock.getStockyardHeight()).thenReturn(6);
        Mockito.when(this.livestock.getStockyardWidth()).thenReturn(6);
        this.stockyard = new Stockyard(new Position(2,3), this.livestock);
        Color BLACK = Color.BLACK;
        Color WHITE = Color.WHITE;
        Color PATH = Color.HIGHLIGHTED_FLOOR;
        Color FENCE_DOOR = StockyardViewer.FENCE_DOOR_COLOR;
        Color GRASS = Color.GRASS;
        Color FENCE = Color.WOOD;

        StockyardViewer viewer = new StockyardViewer();
        viewer.draw(stockyard, gui);

        Color expectedFg[][] = {
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, FENCE, FENCE, FENCE, FENCE, FENCE, WHITE, WHITE},
                {WHITE, WHITE, null, FENCE, null, null, null, FENCE, WHITE, WHITE},
                {WHITE, WHITE, null, FENCE, null, null, null, FENCE, WHITE, WHITE},
                {WHITE, WHITE, null, FENCE_DOOR, null, null, null, FENCE, WHITE, WHITE},
                {WHITE, WHITE, null, FENCE, null, null, null, FENCE, WHITE, WHITE},
                {WHITE, WHITE, null, FENCE, FENCE, FENCE, FENCE, FENCE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
        };

        Color expectedBg[][] = {
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, GRASS, GRASS, GRASS, GRASS, GRASS, BLACK, BLACK},
                {BLACK, BLACK, BLACK, GRASS, BLACK, BLACK, BLACK, GRASS, BLACK, BLACK},
                {BLACK, BLACK, BLACK, GRASS, BLACK, BLACK, BLACK, GRASS, BLACK, BLACK},
                {BLACK, BLACK, PATH, GRASS, BLACK, BLACK, BLACK, GRASS, BLACK, BLACK},
                {BLACK, BLACK, BLACK, GRASS, BLACK, BLACK, BLACK, GRASS, BLACK, BLACK},
                {BLACK, BLACK, BLACK, GRASS, GRASS, GRASS, GRASS, GRASS, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
        };

        char expectedChars[][] = {
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', '+', '-', '-', '-', '+', ' ', ' '},
                {' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' '},
                {' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' '},
                {' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' '},
                {' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' '},
                {' ', ' ', ' ', '+', '-', '-', '-', '+', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        };

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Assertions.assertEquals(expectedChars[j][i], this.characters[j][i]);
                Assertions.assertEquals(expectedBg[j][i], this.backgroundColors[j][i]);
                if (expectedFg[j][i] != null) {
                    Assertions.assertEquals(expectedFg[j][i], this.foregroundColors[j][i]);
                }
            }
        }
    }


    @Test
    void drawAnimals() {
        Mockito.when(this.livestock.getStockyardHeight()).thenReturn(6);
        Mockito.when(this.livestock.getStockyardWidth()).thenReturn(6);
        this.stockyard = new Stockyard(new Position(2,3), this.livestock);
        this.stockyard.getAnimals().addAnimal(new Position(4, 4));
        this.stockyard.getAnimals().addAnimal(new Position(5, 4));

        Color expectedFg[][] = cloneSquareBidimensionalArray(this.foregroundColors);
        Color expectedBg[][] = cloneSquareBidimensionalArray(this.backgroundColors);
        char expectedChars[][] = cloneSquareBidimensionalArray(this.characters);

        StockyardViewer viewer = new StockyardViewer();
        viewer.draw(stockyard, gui);

        GUI expectedGUI = new GUIMockTestHelper(expectedBg, expectedFg, expectedChars).mock();
        AnimalViewer animalViewer = new AnimalViewer();
        animalViewer.draw(new Animal(new Position(4, 4)), 'C', this.stockyard.getAnimalColor(), expectedGUI);
        animalViewer.draw(new Animal(new Position(5, 4)), 'C', this.stockyard.getAnimalColor(), expectedGUI);

        for (int i = 4; i < 7; i++) {
            for (int j = 4; j < 7; j++) {
                Assertions.assertEquals(expectedChars[j][i], this.characters[j][i]);
                Assertions.assertEquals(expectedBg[j][i], this.backgroundColors[j][i]);
                if (expectedFg[j][i] != null) {
                    Assertions.assertEquals(expectedFg[j][i], this.foregroundColors[j][i]);
                }
            }
        }
    }

}
