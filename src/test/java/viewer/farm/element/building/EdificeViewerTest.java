package viewer.farm.element.building;

import gui.Color;
import gui.GUI;
import gui.GUIMockTestHelper;
import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


class EdificeViewerTest {
    private GUI gui;
    private Color backgroundColors[][] = new Color[10][10];
    private Color foregroundColors[][] = new Color[10][10];
    private char characters[][] = new char[10][10];

    @BeforeEach
    void setUp() {
        this.gui = new GUIMockTestHelper(backgroundColors, foregroundColors, characters).mock();
    }


    @Test
    void drawAtOrigin() {
        Color BLACK = Color.BLACK;
        Color PATH = Color.HIGHLIGHTED_FLOOR;
        Color FLOOR = EdificeViewer.FLOOR_COLOR;
        Color DOOR = EdificeViewer.DOOR_COLOR;
        Color WALL = EdificeViewer.WALL_COLOR;
        Color ROOF = new Color("#c20000");


        EdificeViewer viewer = new EdificeViewer(ROOF);
        viewer.draw(new Position(0, 0), gui);

        Color expectedBg[][] = {
                {BLACK, ROOF, ROOF, ROOF, ROOF, ROOF, BLACK, BLACK, BLACK, BLACK},
                {ROOF, ROOF, ROOF, ROOF, ROOF, ROOF, ROOF, BLACK, BLACK, BLACK},
                {WALL, WALL, WALL, WALL, WALL, WALL, WALL, BLACK, BLACK, BLACK},
                {WALL, WALL, WALL, WALL, WALL, WALL, WALL, BLACK, BLACK, BLACK},
                {WALL, WALL, WALL, WALL, DOOR, WALL, WALL, BLACK, BLACK, BLACK},
                {WALL, WALL, WALL, WALL, DOOR, WALL, WALL, BLACK, BLACK, BLACK},
                {FLOOR, FLOOR, FLOOR, FLOOR, PATH, FLOOR, FLOOR, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
        };

        char expectedChars[][] = {
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', '\'', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        };

        for (int i = 0; i < 10; i++) {
            Assertions.assertArrayEquals(expectedBg[i], this.backgroundColors[i]);
            Assertions.assertArrayEquals(expectedChars[i], this.characters[i]);
        }

        Assertions.assertEquals(BLACK, this.foregroundColors[5][4]);
    }

    @Test
    void drawOtherPosition() {
        Color BLACK = Color.BLACK;
        Color PATH = Color.HIGHLIGHTED_FLOOR;
        Color FLOOR = EdificeViewer.FLOOR_COLOR;
        Color DOOR = EdificeViewer.DOOR_COLOR;
        Color WALL = EdificeViewer.WALL_COLOR;
        Color ROOF = new Color("#c20000");


        EdificeViewer viewer = new EdificeViewer( ROOF);
        viewer.draw(new Position(2, 2), gui);

        Color expectedBg[][] = {
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, ROOF, ROOF, ROOF, ROOF, ROOF, BLACK, BLACK},
                {BLACK, BLACK, ROOF, ROOF, ROOF, ROOF, ROOF, ROOF, ROOF, BLACK},
                {BLACK, BLACK, WALL, WALL, WALL, WALL, WALL, WALL, WALL, BLACK},
                {BLACK, BLACK, WALL, WALL, WALL, WALL, WALL, WALL, WALL, BLACK},
                {BLACK, BLACK, WALL, WALL, WALL, WALL, DOOR, WALL, WALL, BLACK},
                {BLACK, BLACK, WALL, WALL, WALL, WALL, DOOR, WALL, WALL, BLACK},
                {BLACK, BLACK, FLOOR, FLOOR, FLOOR, FLOOR, PATH, FLOOR, FLOOR, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
        };

        char expectedChars[][] = {
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', '\'', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        };

        for (int i = 0; i < 10; i++) {
            Assertions.assertArrayEquals(expectedBg[i], this.backgroundColors[i]);
            Assertions.assertArrayEquals(expectedChars[i], this.characters[i]);
        }

        Assertions.assertEquals(BLACK, this.foregroundColors[7][6]);
    }
}
