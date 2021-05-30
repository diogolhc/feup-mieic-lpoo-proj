package viewer.menu;

import gui.GUI;
import model.Position;
import model.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

public class PopupMenuViewerTest {
    private GUI gui;
    private PopupMenuViewer viewer;
    private MenuViewer backViewer;
    private Menu menu;

    @BeforeEach
    void setUp() {
        this.gui = Mockito.mock(GUI.class);
        this.backViewer = Mockito.mock(MenuViewer.class);
        this.menu = Mockito.mock(Menu.class);
        Mockito.when(this.menu.getTopLeftPosition()).thenReturn(new Position(0, 0));
        Mockito.when(this.menu.getTitle()).thenReturn("");
        this.viewer = new PopupMenuViewer(this.menu, this.backViewer);
    }

    @Test
    void drawBackViewerBeforePopup() {
        this.viewer.draw(this.gui);

        // Menu is accessed when drawing the popup
        // so this access must happen after drawing the backViewer
        InOrder verifier = Mockito.inOrder(this.menu, this.backViewer);
        verifier.verify(this.backViewer).draw(this.gui);
        verifier.verify(this.menu).getTopLeftPosition();
    }
}
