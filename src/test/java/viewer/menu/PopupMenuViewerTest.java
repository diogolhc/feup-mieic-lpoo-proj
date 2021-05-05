package viewer.menu;

import gui.GUI;
import model.Position;
import model.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import viewer.GameViewer;

import java.io.IOException;

public class PopupMenuViewerTest {
    private GUI gui;
    private PopupMenuViewer viewer;
    private MenuViewer backViewer;
    private Menu menu;

    @BeforeEach
    void setUp() {
        gui = Mockito.mock(GUI.class);
        backViewer = Mockito.mock(MenuViewer.class);
        menu = Mockito.mock(Menu.class);
        Mockito.when(menu.getTopLeftPosition()).thenReturn(new Position(0, 0));
        Mockito.when(menu.getTitle()).thenReturn("");
        viewer = new PopupMenuViewer(menu, backViewer);
    }

    @Test
    void drawBackViewerBeforePopup() throws IOException {
        viewer.draw(gui);

        // Menu is accessed when drawing the popup
        // so this access must happen after drawing the backViewer
        InOrder verifier = Mockito.inOrder(menu, backViewer);
        verifier.verify(backViewer).draw(gui);
        verifier.verify(menu).getTopLeftPosition();
    }
}
