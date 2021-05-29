package viewer.farm.element.building;

import gui.Color;
import gui.GUI;
import gui.GUIMockTestHelper;
import model.InGameTime;
import model.Position;
import model.farm.building.crop_field.CropField;
import model.farm.building.crop_field.state.CropFieldState;
import model.farm.building.crop_field.state.ReadyToHarvest;
import model.farm.data.item.Crop;
import model.farm.data.item.CropGrowthStage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


class CropFieldViewerTest {
    private GUI gui;
    private Color backgroundColors[][] = new Color[10][10];
    private Color foregroundColors[][] = new Color[10][10];
    private char characters[][] = new char[10][10];
    private CropField cropField;

    @BeforeEach
    void setUp() {
        this.gui = new GUIMockTestHelper(backgroundColors, foregroundColors, characters).mock();
    }


    @Test
    void drawNoCropAtOrigin() {
        this.cropField = new CropField(new Position(0, 0));
        Color BLACK = Color.BLACK;
        Color PATH = Color.HIGHLIGHTED_FLOOR;
        Color SOIL_COLOR = CropFieldViewer.SOIL_COLOR;

        CropFieldViewer viewer = new CropFieldViewer();
        viewer.draw(cropField, gui);

        Color expectedBg[][] = {
                {PATH, PATH, PATH, PATH, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {PATH, SOIL_COLOR, SOIL_COLOR, PATH, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {PATH, SOIL_COLOR, SOIL_COLOR, PATH, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {PATH, PATH, PATH, PATH, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
        };

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Assertions.assertEquals(' ', this.characters[j][i]);
                Assertions.assertEquals(expectedBg[j][i], this.backgroundColors[j][i]);
            }
        }
    }

    @Test
    void drawNoCropAtPosition() {
        this.cropField = new CropField(new Position(2, 2));
        Color BLACK = Color.BLACK;
        Color PATH = Color.HIGHLIGHTED_FLOOR;
        Color SOIL_COLOR = CropFieldViewer.SOIL_COLOR;

        CropFieldViewer viewer = new CropFieldViewer();
        viewer.draw(cropField, gui);

        Color expectedBg[][] = {
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, PATH, PATH, PATH, PATH, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, PATH, SOIL_COLOR, SOIL_COLOR, PATH, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, PATH, SOIL_COLOR, SOIL_COLOR, PATH, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, PATH, PATH, PATH, PATH, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
        };


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Assertions.assertEquals(' ', this.characters[j][i]);
                Assertions.assertEquals(expectedBg[j][i], this.backgroundColors[j][i]);
            }
        }
    }

    @Test
    void drawWithCrop() {
        Color BLACK = Color.BLACK;
        Color PATH = Color.HIGHLIGHTED_FLOOR;
        Color SOIL_COLOR = CropFieldViewer.SOIL_COLOR;
        Color STAGE_COLOR = new Color("#696606");

        Crop crop = new Crop("WHEAT");
        crop.addGrowthStage(new CropGrowthStage(new InGameTime(0), '#', STAGE_COLOR));
        CropFieldState state = new ReadyToHarvest(cropField, crop, 5);
        this.cropField = new CropField(new Position(2, 2));
        this.cropField.setState(state);

        CropFieldViewer viewer = new CropFieldViewer();
        viewer.draw(cropField, gui);

        Color expectedBg[][] = {
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, PATH, PATH, PATH, PATH, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, PATH, SOIL_COLOR, SOIL_COLOR, PATH, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, PATH, SOIL_COLOR, SOIL_COLOR, PATH, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, PATH, PATH, PATH, PATH, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
        };

        char expectedChars[][] = {
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', '#', '#', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', '#', '#', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        };

        for (int i = 0; i < 10; i++) {
            Assertions.assertArrayEquals(expectedBg[i], this.backgroundColors[i]);
            Assertions.assertArrayEquals(expectedChars[i], this.characters[i]);
        }

        Assertions.assertEquals(STAGE_COLOR, this.foregroundColors[3][3]);
        Assertions.assertEquals(STAGE_COLOR, this.foregroundColors[3][4]);
        Assertions.assertEquals(STAGE_COLOR, this.foregroundColors[4][3]);
        Assertions.assertEquals(STAGE_COLOR, this.foregroundColors[4][4]);
    }

}
