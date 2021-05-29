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
    public static final int CHAR_SIZE = 25;

    private final Terminal terminal;
    private final TerminalScreen screen;
    private final TextGraphics graphics;
    private int mouseX = 0;
    private int mouseY = 0;
    private final int windowWidth;
    private final int windowHeight;

    public LanternaGUI(Terminal terminal, TerminalScreen screen) throws IOException {
        this.terminal = terminal;
        this.windowWidth = terminal.getTerminalSize().getColumns();
        this.windowHeight = terminal.getTerminalSize().getRows();
        this.screen = screen;
        this.graphics = this.screen.newTextGraphics();
    }

    public LanternaGUI(int width, int height) throws IOException, FontFormatException, URISyntaxException {
        AWTTerminalFontConfiguration fontConfig = this.loadSquareFont();
        this.windowWidth = width;
        this.windowHeight = height;
        this.terminal = this.createTerminal(width, height, fontConfig);
        this.screen = this.createScreen(terminal);
        this.graphics = this.screen.newTextGraphics();
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
        URL resource = this.getClass().getClassLoader().getResource("fonts/square.ttf");
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
    public int getWindowWidth() {
        return this.windowWidth;
    }

    @Override
    public int getWindowHeight() {
        return this.windowHeight;
    }

    @Override
    public void setBackgroundColor(Color color) {
        this.graphics.setBackgroundColor(TextColor.Factory.fromString(color.toString()));

    }

    @Override
    public void setForegroundColor(Color color) {
        this.graphics.setForegroundColor(TextColor.Factory.fromString(color.toString()));
    }

    private static String getTextColorString(TextColor color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    @Override
    public Color getBackgroundColor(int x, int y) {
        return new Color(this.getTextColorString(this.graphics.getCharacter(x, y).getBackgroundColor()));
    }

    @Override
    public Color getForegroundColor(int x, int y) {
        return new Color(this.getTextColorString(this.graphics.getCharacter(x, y).getForegroundColor()));
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
    public KEYBOARD_ACTION getNextKeyboardAction() throws IOException {
        KeyStroke keyStroke = this.screen.pollInput();

        if (keyStroke == null) return KEYBOARD_ACTION.NONE;

        if (this.isKeyStrokeType(keyStroke, KeyType.EOF)) return KEYBOARD_ACTION.QUIT;
        if (this.isKeyStrokeType(keyStroke, KeyType.Escape)) return KEYBOARD_ACTION.BACK;

        if (this.isKeyStrokeCharacter(keyStroke, 'w')) return KEYBOARD_ACTION.MOVE_UP;
        if (this.isKeyStrokeCharacter(keyStroke, 'd')) return KEYBOARD_ACTION.MOVE_RIGHT;
        if (this.isKeyStrokeCharacter(keyStroke, 's')) return KEYBOARD_ACTION.MOVE_DOWN;
        if (this.isKeyStrokeCharacter(keyStroke, 'a')) return KEYBOARD_ACTION.MOVE_LEFT;
        if (this.isKeyStrokeCharacter(keyStroke, ' ')) return KEYBOARD_ACTION.INTERACT;

        return KEYBOARD_ACTION.NONE;
    }

    public MouseAdapter getMouseAdapter(MouseListener mouseListener) {
        return new MouseAdapter() {
            private void updateMousePosition(MouseEvent e) {
                mouseX = e.getX()/LanternaGUI.CHAR_SIZE;
                mouseY = e.getY()/LanternaGUI.CHAR_SIZE;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                updateMousePosition(e);
                mouseListener.onMouseClick(mouseX, mouseY);

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                updateMousePosition(e);
                mouseListener.onMouseMovement(mouseX, mouseY);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // When mouse is released after being pressed, it should
                // update position so that buttons are selected/unselected
                // correctly.
                updateMousePosition(e);
                mouseListener.onMouseMovement(mouseX, mouseY);
            }
        };
    }

    @Override
    public void setMouseListener(MouseListener mouseListener) {
        MouseAdapter mouseAdapter = getMouseAdapter(mouseListener);

        ((AWTTerminalFrame) this.terminal).getComponent(0).addMouseListener(mouseAdapter);
        ((AWTTerminalFrame) this.terminal).getComponent(0).addMouseMotionListener(mouseAdapter);
    }

    @Override
    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }
}
