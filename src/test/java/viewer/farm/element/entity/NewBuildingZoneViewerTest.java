package viewer.farm.element.entity;

import gui.Color;
import gui.GUI;
import gui.GUIMockTestHelper;
import model.Position;
import model.farm.building.Building;
import model.farm.building.BuildingSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


class NewBuildingZoneViewerTest {
    private GUI gui;
    private Color backgroundColors[][] = new Color[10][10];
    private Color foregroundColors[][] = new Color[10][10];
    private char characters[][] = new char[10][10];
    private BuildingSet buildings;
    private Building newBuilding;

    @BeforeEach
    void setUp() {
        this.gui = new GUIMockTestHelper(backgroundColors, foregroundColors, characters).mock();

        this.buildings = Mockito.mock(BuildingSet.class);
        this.newBuilding = Mockito.mock(Building.class);
        Mockito.when(this.newBuilding.getWidth()).thenReturn(3);
        Mockito.when(this.newBuilding.getHeight()).thenReturn(4);
        Mockito.when(this.newBuilding.getTopLeftPosition()).thenReturn(new Position(1,1));
    }

    @Test
    void drawAvailable() {
        Mockito.when(this.buildings.isOccupied(Mockito.any())).thenReturn(false);

        Color BLACK = Color.BLACK;
        Color ZONE = NewBuildingZoneViewer.AVAILABLE_ZONE_COLOR;


        NewBuildingZoneViewer newBuildingZoneViewer = new NewBuildingZoneViewer();
        newBuildingZoneViewer.draw(this.buildings, this.newBuilding, this.gui);


        Color expectedBg[][] = {
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, ZONE, ZONE, ZONE, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, ZONE, ZONE, ZONE, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, ZONE, ZONE, ZONE, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, ZONE, ZONE, ZONE, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
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

    }


    @Test
    void drawUnavailable() {
        Mockito.when(this.buildings.isOccupied(Mockito.any())).thenReturn(true);

        Color BLACK = Color.BLACK;
        Color ZONE = NewBuildingZoneViewer.UNAVAILABLE_ZONE_COLOR;

        NewBuildingZoneViewer newBuildingZoneViewer = new NewBuildingZoneViewer();
        newBuildingZoneViewer.draw(this.buildings, this.newBuilding, this.gui);


        Color expectedBg[][] = {
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, ZONE, ZONE, ZONE, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, ZONE, ZONE, ZONE, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, ZONE, ZONE, ZONE, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, ZONE, ZONE, ZONE, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
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

    }

}
