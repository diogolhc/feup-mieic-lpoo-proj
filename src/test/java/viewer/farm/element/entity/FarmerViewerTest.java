package viewer.farm.element.entity;

import gui.Color;
import gui.GUI;
import gui.GUIMockTestHelper;
import model.Position;
import model.farm.entity.Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class FarmerViewerTest {
    private GUI gui;
    private Color backgroundColors[][] = new Color[10][10];
    private Color foregroundColors[][] = new Color[10][10];
    private char characters[][] = new char[10][10];

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
    void draw() {
        Color WHITE = Color.WHITE;
        Color FARMER_COLOR = FarmerViewer.FARMER_COLOR;

        Color expectedBg[][] = cloneSquareBidimensionalArray(this.backgroundColors);
        Entity farmer = new Entity(new Position(2, 3));
        FarmerViewer viewer = new FarmerViewer();
        viewer.draw(farmer, this.gui);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 2 && j == 3) {
                    Assertions.assertEquals(FarmerViewer.FARMER_CHAR, this.characters[j][i]);
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
        Color WHITE = Color.WHITE;
        Color RED = new Color("#FF0000");
        Color BLUE = new Color("#0000FF");
        Color FARMER_COLOR = FarmerViewer.FARMER_COLOR;

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

        Entity farmer = new Entity(new Position(4, 6));
        FarmerViewer viewer = new FarmerViewer();
        viewer.draw(farmer, this.gui);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 4 && j == 6) {
                    Assertions.assertEquals(FarmerViewer.FARMER_CHAR, this.characters[j][i]);
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
