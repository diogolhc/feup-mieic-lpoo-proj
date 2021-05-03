package gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class LanternaGUI implements GUI {
    private static final int CHAR_SIZE = 25;
    private final Terminal terminal;
    private final TerminalScreen screen;
    private final TextGraphics graphics;

    public LanternaGUI(int width, int height) throws IOException, FontFormatException, URISyntaxException {
        AWTTerminalFontConfiguration fontConfig = this.loadSquareFont();
        terminal = this.createTerminal(width, height, fontConfig);
        screen = this.createScreen(terminal);
        graphics = this.screen.newTextGraphics();
    }

    private static TerminalScreen createScreen(Terminal terminal) throws IOException {
        final TerminalScreen screen;
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    private static Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration fontConfig) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height);
        Terminal terminal = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize)
                .setForceAWTOverSwing(true)
                .setTerminalEmulatorFontConfiguration(fontConfig)
                .createTerminal();

        ((AWTTerminalFrame)terminal).addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
            }
        });

        return terminal;
    }

    private AWTTerminalFontConfiguration loadSquareFont() throws URISyntaxException, FontFormatException, IOException {
        URL resource = this.getClass().getClassLoader().getResource("square.ttf");
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 25);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
        return fontConfig;
    }

    @Override
    public void clear() {
        this.screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        this.screen.refresh();
    }

    @Override
    public void close() throws IOException {
        this.screen.close();
    }

    @Override
    public void setBackgroundColor(Color color) {
        this.graphics.setBackgroundColor(TextColor.Factory.fromString(color.toString()));

    }

    @Override
    public void setForegroundColor(Color color) {
        this.graphics.setForegroundColor(TextColor.Factory.fromString(color.toString()));
    }

    private static String getColorString(TextColor color) {
        return "#"
                + String.format("%02x", color.getRed())
                + String.format("%02x", color.getGreen())
                + String.format("%02x", color.getBlue());
    }

    @Override
    public Color getBackGroundColor(int x, int y) {
        return new Color(this.getColorString(this.graphics.getCharacter(x, y).getBackgroundColor()));
    }

    @Override
    public Color getForegroundColor(int x, int y) {
        return new Color(this.getColorString(this.graphics.getCharacter(x, y).getBackgroundColor()));
    }

    @Override
    public void drawChar(int x, int y, char character) {
        this.graphics.setCharacter(x, y, character);
    }

    @Override
    public void drawString(int x, int y, String string) {
        this.graphics.putString(x, y, string);
    }

    private static boolean isKeyStrokeType(KeyStroke keyStroke, KeyType type) {
        return keyStroke.getKeyType() == type;
    }

    private static boolean isKeyStrokeCharacter(KeyStroke keyStroke, char c) {
        return isKeyStrokeType(keyStroke, KeyType.Character) && keyStroke.getCharacter() == c;
    }

    @Override
    public ACTION getNextAction() throws IOException {
        // TODO input using other thread in order to not block the rest of the game (namely screen update)
        KeyStroke keyStroke = this.screen.readInput();

        if (keyStroke == null) return ACTION.NONE;

        if (this.isKeyStrokeType(keyStroke, KeyType.EOF)) return ACTION.QUIT;
        if (this.isKeyStrokeCharacter(keyStroke, 'q')) return ACTION.QUIT;

        if (this.isKeyStrokeCharacter(keyStroke, 'w')) return ACTION.MOVE_UP;
        if (this.isKeyStrokeCharacter(keyStroke, 'd')) return ACTION.MOVE_RIGHT;
        if (this.isKeyStrokeCharacter(keyStroke, 's')) return ACTION.MOVE_DOWN;
        if (this.isKeyStrokeCharacter(keyStroke, 'a')) return ACTION.MOVE_LEFT;
        if (this.isKeyStrokeCharacter(keyStroke, ' ')) return ACTION.INTERACT;

        return ACTION.NONE;
    }

    @Override
    public void setMouseListener(MouseListener mouseListener) {
        ((AWTTerminalFrame) this.terminal).getComponent(0).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseListener.onMouseClick(e.getX()/LanternaGUI.CHAR_SIZE, e.getY()/LanternaGUI.CHAR_SIZE);
            }
        });

        ((AWTTerminalFrame) this.terminal).getComponent(0).addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseListener.onMouseMovement(e.getX()/LanternaGUI.CHAR_SIZE, e.getY()/LanternaGUI.CHAR_SIZE);
            }
        });
    }
}
