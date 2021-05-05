package gui;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
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
    void setUp() {
        this.terminal = Mockito.mock(Terminal.class);
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
            return TextCharacter.fromCharacter(c, fg, bg);
        });

        gui = new LanternaGUI(terminal, screen);
    }


    @Test
    void drawString() {
        gui.setForegroundColor(new Color("#00FF56"));
        gui.drawString(1, 2, "HELLO WORLD");

        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(0, 255, 86));
        Mockito.verify(tg, Mockito.times(1)).putString(1, 2, "HELLO WORLD");
    }

    @Test
    void drawChar() {
        // TODO
    }

    @Test
    void backGroundColorMatches() {
        gui.setBackgroundColor(new Color("#FF0000"));
        gui.drawChar(0, 0, ' ');
        gui.setBackgroundColor(new Color("#0000FF"));
        gui.drawChar(5, 0, ' ');
        gui.drawChar(5, 1, '.');
        gui.drawChar(5, 2, ';');
        gui.setBackgroundColor(new Color("#00FF00"));
        gui.drawChar(6, 1, ' ');
        gui.drawChar(5, 1, '=');

        // TODO fix this
        //Assertions.assertEquals(gui.getBackgroundColor(0, 0).toString(), "#FF0000");

        Mockito.verify(tg, Mockito.times(3)).setBackgroundColor(Mockito.any());
        Mockito.verify(tg, Mockito.times(6)).setCharacter(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyChar());
    }

    @Test
    void foregroundGroundColorMatches() {
        // TODO
    }

    @Test
    void getNextAction() throws IOException {
        Mockito.when(screen.pollInput()).thenReturn(KeyStroke.fromString("w"));
        Assertions.assertEquals(GUI.ACTION.MOVE_UP, gui.getNextAction());
        Mockito.when(screen.pollInput()).thenReturn(KeyStroke.fromString("s"));
        Assertions.assertEquals(GUI.ACTION.MOVE_DOWN, gui.getNextAction());
        Mockito.when(screen.pollInput()).thenReturn(KeyStroke.fromString("a"));
        Assertions.assertEquals(GUI.ACTION.MOVE_LEFT, gui.getNextAction());
        Mockito.when(screen.pollInput()).thenReturn(KeyStroke.fromString("d"));
        Assertions.assertEquals(GUI.ACTION.MOVE_RIGHT, gui.getNextAction());
        Mockito.when(screen.pollInput()).thenReturn(KeyStroke.fromString("<Space>"));
        Assertions.assertEquals(GUI.ACTION.INTERACT, gui.getNextAction());
        Mockito.when(screen.pollInput()).thenReturn(null);
        Assertions.assertEquals(GUI.ACTION.NONE, gui.getNextAction());
        Mockito.when(screen.pollInput()).thenReturn(KeyStroke.fromString("."));
        Assertions.assertEquals(GUI.ACTION.NONE, gui.getNextAction());
    }

    @Test
    void mouseListenerClick() {
        //MouseListener mouseListener = Mockito.mock(MouseListener.class);

        /*
        Mockito.doAnswer(invocation -> {
            //MouseAdapter adapter = invocation.getArgument(0);
            //adapter.mouseClicked(new MouseEvent(null, 0, 0, 0, 49, 39, 0, false));
            return null;
        }).when((AWTTerminalFrame) terminal).getComponent(0).addMouseListener(Mockito.any());
        */

        //gui.setMouseListener(mouseListener);


        //Mockito.verify(mouseListener, Mockito.times(1)).onMouseClick(2, 2);
    }

    @Test
    void mouseListenerMove() {
        /*
        MouseListener mouseListener = Mockito.mock(MouseListener.class);

        Mockito.doAnswer(invocation -> {
            MouseMotionListener adapter = invocation.getArgument(0);
            adapter.mouseMoved(new MouseEvent(null, 0, 0, 0, 50, 39, 0, false));
            return null;
        }).when((AWTTerminalFrame) terminal).getComponent(0).addMouseMotionListener(Mockito.any());
        */
    }
}
