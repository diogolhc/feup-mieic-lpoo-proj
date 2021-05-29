package gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

class LanternaGUITest {
    private Terminal terminal;
    private TerminalScreen screen;
    private LanternaGUI gui;
    private TextGraphics tg;
    private TextColor backgroundColors[][] = new TextColor[10][10];
    private TextColor foregroundColors[][] = new TextColor[10][10];
    private char characters[][] = new char[10][10];
    private TextColor currentBackgroundColor = TextColor.Factory.fromString("#000000");
    private TextColor currentForegroundColor = TextColor.Factory.fromString("#FFFFFF");

    @BeforeEach
    void setUp() throws IOException {
        this.terminal = Mockito.mock(Terminal.class);
        Mockito.when(this.terminal.getTerminalSize()).thenReturn(new TerminalSize(10, 10));
        this.screen = Mockito.mock(TerminalScreen.class);
        this.tg = Mockito.mock(TextGraphics.class);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.backgroundColors[i][j] = this.currentBackgroundColor;
                this.foregroundColors[i][j] = this.currentForegroundColor;
                this.characters[i][j] = ' ';
            }
        }

        Mockito.when(screen.newTextGraphics()).thenReturn(tg);

        Mockito.doAnswer(invocation -> {
            this.currentBackgroundColor = invocation.getArgument(0);
            return tg;
        }).when(tg).setBackgroundColor(Mockito.any());

        Mockito.doAnswer(invocation -> {
            this.currentForegroundColor = invocation.getArgument(0);
            return tg;
        }).when(tg).setForegroundColor(Mockito.any());

        Mockito.doAnswer(invocation -> {
            int x = invocation.getArgument(0);
            int y = invocation.getArgument(1);
            char c = invocation.getArgument(2);
            this.backgroundColors[x][y] = currentBackgroundColor;
            this.foregroundColors[x][y] = currentForegroundColor;
            this.characters[x][y] = c;
            return tg;
        }).when(tg).setCharacter(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyChar());

        Mockito.when(tg.getCharacter(Mockito.anyInt(), Mockito.anyInt())).thenAnswer(invocation -> {
            int x = invocation.getArgument(0);
            int y = invocation.getArgument(1);
            TextColor bg = this.backgroundColors[x][y];
            TextColor fg = this.foregroundColors[x][y];
            char c = this.characters[x][y];
            return TextCharacter.fromCharacter(c, fg, bg)[0];
        });

        Mockito.when(this.terminal.getTerminalSize()).thenReturn(new TerminalSize(50, 50));
        gui = new LanternaGUI(terminal, screen);
    }

    @Test
    void backgroundColorMatches() {
        gui.setBackgroundColor(new Color("#ff0000"));
        gui.drawChar(0, 0, ' ');
        gui.setBackgroundColor(new Color("#0000ff"));
        gui.drawChar(5, 0, ' ');
        gui.drawChar(5, 1, '.');
        gui.drawChar(5, 2, ';');
        gui.setBackgroundColor(new Color("#00ff00"));
        gui.drawChar(6, 1, ' ');
        gui.drawChar(5, 1, '=');

        Assertions.assertEquals("#ff0000", gui.getBackgroundColor(0, 0).toString());
        Assertions.assertEquals("#00ff00", gui.getBackgroundColor(5, 1).toString());
        Assertions.assertEquals("#0000ff", gui.getBackgroundColor(5, 2).toString());

        Mockito.verify(tg, Mockito.times(3)).setBackgroundColor(Mockito.any());
        Mockito.verify(tg, Mockito.times(6)).setCharacter(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyChar());
    }

    @Test
    void foregroundColorMatches() {
        gui.setForegroundColor(new Color("#ff0000"));
        gui.drawChar(0, 0, '?');
        gui.setForegroundColor(new Color("#0000ff"));
        gui.drawChar(5, 0, '?');
        gui.drawChar(5, 1, '.');
        gui.drawChar(5, 2, ';');
        gui.setForegroundColor(new Color("#00ff00"));
        gui.drawChar(6, 1, '?');
        gui.drawChar(5, 1, '=');

        Assertions.assertEquals("#ff0000", gui.getForegroundColor(0, 0).toString());
        Assertions.assertEquals("#00ff00", gui.getForegroundColor(5, 1).toString());
        Assertions.assertEquals("#0000ff", gui.getForegroundColor(5, 2).toString());

        Mockito.verify(tg, Mockito.times(3)).setForegroundColor(Mockito.any());
        Mockito.verify(tg, Mockito.times(6)).setCharacter(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyChar());
    }

    @Test
    void getNextAction() throws IOException {
        Mockito.when(screen.pollInput()).thenReturn(KeyStroke.fromString("w"));
        Assertions.assertEquals(GUI.KEYBOARD_ACTION.MOVE_UP, gui.getNextKeyboardAction());
        Mockito.when(screen.pollInput()).thenReturn(KeyStroke.fromString("s"));
        Assertions.assertEquals(GUI.KEYBOARD_ACTION.MOVE_DOWN, gui.getNextKeyboardAction());
        Mockito.when(screen.pollInput()).thenReturn(KeyStroke.fromString("a"));
        Assertions.assertEquals(GUI.KEYBOARD_ACTION.MOVE_LEFT, gui.getNextKeyboardAction());
        Mockito.when(screen.pollInput()).thenReturn(KeyStroke.fromString("d"));
        Assertions.assertEquals(GUI.KEYBOARD_ACTION.MOVE_RIGHT, gui.getNextKeyboardAction());
        Mockito.when(screen.pollInput()).thenReturn(KeyStroke.fromString("<Space>"));
        Assertions.assertEquals(GUI.KEYBOARD_ACTION.INTERACT, gui.getNextKeyboardAction());
        Mockito.when(screen.pollInput()).thenReturn(null);
        Assertions.assertEquals(GUI.KEYBOARD_ACTION.NONE, gui.getNextKeyboardAction());
        Mockito.when(screen.pollInput()).thenReturn(KeyStroke.fromString("."));
        Assertions.assertEquals(GUI.KEYBOARD_ACTION.NONE, gui.getNextKeyboardAction());
        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(KeyType.EOF));
        Assertions.assertEquals(GUI.KEYBOARD_ACTION.QUIT, gui.getNextKeyboardAction());
    }

    private MouseEvent getMouseEvent(int x, int y) {
        MouseEvent e = Mockito.mock(MouseEvent.class);
        Mockito.when(e.getX()).thenReturn(x * LanternaGUI.CHAR_SIZE);
        Mockito.when(e.getY()).thenReturn(y * LanternaGUI.CHAR_SIZE);

        return e;
    }

    @Test
    void mouseAdapter() {
        MouseListener mouseListener = Mockito.mock(MouseListener.class);
        MouseAdapter mouseAdapter = gui.getMouseAdapter(mouseListener);

        mouseAdapter.mouseMoved(getMouseEvent(4, 4));
        Mockito.verify(mouseListener, Mockito.times(1)).onMouseMovement(4, 4);
        Assertions.assertEquals(4, gui.getMouseX());
        Assertions.assertEquals(4, gui.getMouseY());

        mouseAdapter.mouseClicked(getMouseEvent(3, 20));
        Mockito.verify(mouseListener, Mockito.times(1)).onMouseClick(3, 20);
        Assertions.assertEquals(3, gui.getMouseX());
        Assertions.assertEquals(20, gui.getMouseY());

        mouseAdapter.mouseReleased(getMouseEvent(1, 2));
        Mockito.verify(mouseListener, Mockito.times(1)).onMouseMovement(1, 2);
        Assertions.assertEquals(1, gui.getMouseX());
        Assertions.assertEquals(2, gui.getMouseY());

        Mockito.verifyNoMoreInteractions(mouseListener);
    }
}
