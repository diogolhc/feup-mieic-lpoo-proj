package viewer.farm.element.entity;

import gui.Color;
import gui.GUI;
import model.Position;
import model.farm.building.Building;
import model.farm.building.BuildingSet;
import model.farm.entity.Entity;
import model.region.RectangleRegion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class DemolishMarkerViewerTest {
    private GUI gui;
    private Color backgroundColors[][] = new Color[10][10];
    private Color foregroundColors[][] = new Color[10][10];
    private char characters[][] = new char[10][10];
    private Color currentBackgroundColor = Color.BLACK;
    private Color currentForegroundColor = Color.WHITE;
    private BuildingSet buildings;

    @BeforeEach
    void setUp() {
        this.gui = Mockito.mock(GUI.class);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.backgroundColors[i][j] = this.currentBackgroundColor;
                this.foregroundColors[i][j] = this.currentForegroundColor;
                this.characters[i][j] = ' ';
            }
        }

        Mockito.doAnswer(invocation -> {
            this.currentBackgroundColor = invocation.getArgument(0);
            return null;
        }).when(gui).setBackgroundColor(Mockito.any());

        Mockito.doAnswer(invocation -> {
            this.currentForegroundColor = invocation.getArgument(0);
            return null;
        }).when(gui).setForegroundColor(Mockito.any());

        Mockito.doAnswer(invocation -> {
            int x = invocation.getArgument(0);
            int y = invocation.getArgument(1);
            char c = invocation.getArgument(2);
            this.backgroundColors[y][x] = currentBackgroundColor;
            this.foregroundColors[y][x] = currentForegroundColor;
            this.characters[y][x] = c;
            return null;
        }).when(gui).drawChar(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyChar());

        Mockito.doAnswer(invocation -> {
            String s = invocation.getArgument(2);
            int x = invocation.getArgument(0);
            int y = invocation.getArgument(1);
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                this.backgroundColors[y][x] = currentBackgroundColor;
                this.foregroundColors[y][x] = currentForegroundColor;
                this.characters[y][x] = c;
                x += 1;
            }
            return null;
        }).when(gui).drawString(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString());

        Mockito.doAnswer(invocation -> {
            int x = invocation.getArgument(0);
            int y = invocation.getArgument(1);
            return this.backgroundColors[y][x];
        }).when(gui).getBackgroundColor(Mockito.anyInt(), Mockito.anyInt());

        Mockito.doAnswer(invocation -> {
            int x = invocation.getArgument(0);
            int y = invocation.getArgument(1);
            return this.foregroundColors[y][x];
        }).when(gui).getForegroundColor(Mockito.anyInt(), Mockito.anyInt());


        this.buildings = Mockito.mock(BuildingSet.class);
        List<Building> buildings = new ArrayList<>();
        Building building = Mockito.mock(Building.class);
        buildings.add(building);
        Mockito.when(building.getHeight()).thenReturn(3);
        Mockito.when(building.getWidth()).thenReturn(3);
        Mockito.when(building.getTopLeftPosition()).thenReturn(new Position(1,1));
        Mockito.when(building.getOccupiedRegion()).thenReturn(new RectangleRegion(new Position(1,1), 3, 3));
        Mockito.when(this.buildings.getDemolishableBuildings()).thenReturn(buildings);
    }

    @Test
    void drawNotTarget() {
        Color BLACK = Color.BLACK;
        Color WHITE = Color.WHITE;
        Color MARKER_COLOR = DemolishMarkerViewer.MARKER_COLOR;
        char MARKER_CHAR = DemolishMarkerViewer.MARKER_CHAR;

        Entity marker = Mockito.mock(Entity.class);
        Mockito.when(marker.getPosition()).thenReturn(new Position(6,6));

        DemolishMarkerViewer demolishMarkerViewer = new DemolishMarkerViewer();
        demolishMarkerViewer.draw(this.buildings, marker, this.gui);


        Color expectedBg[][] = {
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, MARKER_COLOR, BLACK, BLACK, BLACK},
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
                {' ', ' ', ' ', ' ', ' ', ' ', MARKER_CHAR, ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        };

        for (int i = 0; i < 10; i++) {
            Assertions.assertArrayEquals(expectedBg[i], this.backgroundColors[i]);
            Assertions.assertArrayEquals(expectedChars[i], this.characters[i]);
        }

        Assertions.assertEquals(WHITE, this.foregroundColors[6][6]);
    }

    @Test
    void drawTarget() {
        Color BLACK = Color.BLACK;
        Color WHITE = Color.WHITE;
        Color MARKER_COLOR = DemolishMarkerViewer.MARKER_COLOR;
        Color TARGET_COLOR = DemolishMarkerViewer.TARGETED_COLOR;
        char MARKER_CHAR = DemolishMarkerViewer.MARKER_CHAR;

        Entity marker = Mockito.mock(Entity.class);
        Mockito.when(marker.getPosition()).thenReturn(new Position(1,1));

        DemolishMarkerViewer demolishMarkerViewer = new DemolishMarkerViewer();
        demolishMarkerViewer.draw(this.buildings, marker, this.gui);


        Color expectedBg[][] = {
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, MARKER_COLOR, TARGET_COLOR, TARGET_COLOR, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, TARGET_COLOR, TARGET_COLOR, TARGET_COLOR, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, TARGET_COLOR, TARGET_COLOR, TARGET_COLOR, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
        };

        char expectedChars[][] = {
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', MARKER_CHAR, ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
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

        Assertions.assertEquals(WHITE, this.foregroundColors[1][1]);
    }

}
