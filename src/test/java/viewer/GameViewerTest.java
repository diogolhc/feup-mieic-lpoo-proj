package viewer;

import gui.GUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.io.IOException;

public class GameViewerTest {
    private GUI gui;
    private GameViewer viewer;

    @BeforeEach
    void setUp() throws IOException {
        gui = Mockito.mock(GUI.class);
        viewer = Mockito.mock(GameViewer.class);
        Mockito.doCallRealMethod().when(viewer).drawScreen(gui);
    }

    @Test
    void drawScreen() throws IOException {
        viewer.drawScreen(gui);
        InOrder verifier = Mockito.inOrder(gui, viewer);
        verifier.verify(gui).clear();
        verifier.verify(viewer).draw(gui);
        verifier.verify(gui).refresh();
        verifier.verifyNoMoreInteractions();
    }
}
