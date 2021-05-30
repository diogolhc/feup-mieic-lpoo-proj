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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

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

        Mockito.when(this.screen.newTextGraphics()).thenReturn(this.tg);

        Mockito.doAnswer(invocation -> {
            this.currentBackgroundColor = invocation.getArgument(0);
            return this.tg;
        }).when(this.tg).setBackgroundColor(Mockito.any());

        Mockito.doAnswer(invocation -> {
            this.currentForegroundColor = invocation.getArgument(0);
            return this.tg;
        }).when(this.tg).setForegroundColor(Mockito.any());

        Mockito.doAnswer(invocation -> {
            int x = invocation.getArgument(0);
            int y = invocation.getArgument(1);
            char c = invocation.getArgument(2);
            this.backgroundColors[x][y] = this.currentBackgroundColor;
            this.foregroundColors[x][y] = this.currentForegroundColor;
            this.characters[x][y] = c;
            return this.tg;
        }).when(this.tg).setCharacter(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyChar());

        Mockito.when(this.tg.getCharacter(Mockito.anyInt(), Mockito.anyInt())).thenAnswer(invocation -> {
            int x = invocation.getArgument(0);
            int y = invocation.getArgument(1);
            TextColor bg = this.backgroundColors[x][y];
            TextColor fg = this.foregroundColors[x][y];
            char c = this.characters[x][y];
            return TextCharacter.fromCharacter(c, fg, bg)[0];
        });

        Mockito.when(this.terminal.getTerminalSize()).thenReturn(new TerminalSize(50, 50));
        this.gui = new LanternaGUI(this.terminal, this.screen);
    }

    @Test
    void backgroundColorMatches() {
        this.gui.setBackgroundColor(new Color("#ff0000"));
        this.gui.drawChar(0, 0, ' ');
        this.gui.setBackgroundColor(new Color("#0000ff"));
        this.gui.drawChar(5, 0, ' ');
        this.gui.drawChar(5, 1, '.');
        this.gui.drawChar(5, 2, ';');
        this.gui.setBackgroundColor(new Color("#00ff00"));
        this.gui.drawChar(6, 1, ' ');
        this.gui.drawChar(5, 1, '=');

        Assertions.assertEquals("#ff0000", this.gui.getBackgroundColor(0, 0).toString());
        Assertions.assertEquals("#00ff00", this.gui.getBackgroundColor(5, 1).toString());
        Assertions.assertEquals("#0000ff", this.gui.getBackgroundColor(5, 2).toString());

        Mockito.verify(this.tg, Mockito.times(3)).setBackgroundColor(Mockito.any());
        Mockito.verify(this.tg, Mockito.times(6)).setCharacter(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyChar());
    }

    @Test
    void foregroundColorMatches() {
        this.gui.setForegroundColor(new Color("#ff0000"));
        this.gui.drawChar(0, 0, '?');
        this.gui.setForegroundColor(new Color("#0000ff"));
        this.gui.drawChar(5, 0, '?');
        this.gui.drawChar(5, 1, '.');
        this.gui.drawChar(5, 2, ';');
        this.gui.setForegroundColor(new Color("#00ff00"));
        this.gui.drawChar(6, 1, '?');
        this.gui.drawChar(5, 1, '=');

        Assertions.assertEquals("#ff0000", this.gui.getForegroundColor(0, 0).toString());
        Assertions.assertEquals("#00ff00", this.gui.getForegroundColor(5, 1).toString());
        Assertions.assertEquals("#0000ff", this.gui.getForegroundColor(5, 2).toString());

        Mockito.verify(this.tg, Mockito.times(3)).setForegroundColor(Mockito.any());
        Mockito.verify(this.tg, Mockito.times(6)).setCharacter(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyChar());
    }

    @Test
    void getNextAction() throws IOException {
        Mockito.when(this.screen.pollInput()).thenReturn(KeyStroke.fromString("w"));
        Assertions.assertEquals(GUI.KEYBOARD_ACTION.MOVE_UP, this.gui.getNextKeyboardAction());
        Mockito.when(this.screen.pollInput()).thenReturn(KeyStroke.fromString("s"));
        Assertions.assertEquals(GUI.KEYBOARD_ACTION.MOVE_DOWN, this.gui.getNextKeyboardAction());
        Mockito.when(this.screen.pollInput()).thenReturn(KeyStroke.fromString("a"));
        Assertions.assertEquals(GUI.KEYBOARD_ACTION.MOVE_LEFT, this.gui.getNextKeyboardAction());
        Mockito.when(this.screen.pollInput()).thenReturn(KeyStroke.fromString("d"));
        Assertions.assertEquals(GUI.KEYBOARD_ACTION.MOVE_RIGHT, this.gui.getNextKeyboardAction());
        Mockito.when(this.screen.pollInput()).thenReturn(KeyStroke.fromString("<Space>"));
        Assertions.assertEquals(GUI.KEYBOARD_ACTION.INTERACT, this.gui.getNextKeyboardAction());
        Mockito.when(this.screen.pollInput()).thenReturn(null);
        Assertions.assertEquals(GUI.KEYBOARD_ACTION.NONE, this.gui.getNextKeyboardAction());
        Mockito.when(this.screen.pollInput()).thenReturn(KeyStroke.fromString("."));
        Assertions.assertEquals(GUI.KEYBOARD_ACTION.NONE, this.gui.getNextKeyboardAction());
        Mockito.when(this.screen.pollInput()).thenReturn(new KeyStroke(KeyType.EOF));
        Assertions.assertEquals(GUI.KEYBOARD_ACTION.QUIT, this.gui.getNextKeyboardAction());
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
        MouseAdapter mouseAdapter = this.gui.getMouseAdapter(mouseListener);

        mouseAdapter.mouseMoved(getMouseEvent(4, 4));
        Mockito.verify(mouseListener, Mockito.times(1)).onMouseMovement(4, 4);
        Assertions.assertEquals(4, this.gui.getMouseX());
        Assertions.assertEquals(4, this.gui.getMouseY());

        mouseAdapter.mouseClicked(getMouseEvent(3, 20));
        Mockito.verify(mouseListener, Mockito.times(1)).onMouseClick(3, 20);
        Assertions.assertEquals(3, this.gui.getMouseX());
        Assertions.assertEquals(20, this.gui.getMouseY());

        mouseAdapter.mouseReleased(getMouseEvent(1, 2));
        Mockito.verify(mouseListener, Mockito.times(1)).onMouseMovement(1, 2);
        Assertions.assertEquals(1, this.gui.getMouseX());
        Assertions.assertEquals(2, this.gui.getMouseY());

        Mockito.verifyNoMoreInteractions(mouseListener);
    }
}
