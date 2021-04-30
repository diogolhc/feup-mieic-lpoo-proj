package gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import model.Position;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class LanternaGUI implements GUI {
    private final TerminalScreen screen;
    private final TextGraphics graphics;

    public LanternaGUI(int width, int height) throws IOException, FontFormatException, URISyntaxException {
        AWTTerminalFontConfiguration fontConfig = this.loadSquareFont();
        Terminal terminal = this.createTerminal(width, height, fontConfig);
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
    public void setBackgroundColor(String color) {
        this.graphics.setBackgroundColor(TextColor.Factory.fromString(color));

    }

    @Override
    public void setForegroundColor(String color) {
        this.graphics.setForegroundColor(TextColor.Factory.fromString(color));
    }

    private static String getColorString(TextColor color) {
        return "#"
                + String.format("%02x", color.getRed())
                + String.format("%02x", color.getGreen())
                + String.format("%02x", color.getBlue());
    }

    @Override
    public String getBackGroundColor(int x, int y) {
        return this.getColorString(this.graphics.getCharacter(x, y).getBackgroundColor());
    }

    @Override
    public String getForegroundColor(int x, int y) {
        return this.getColorString(this.graphics.getCharacter(x, y).getBackgroundColor());
    }

    @Override
    public void drawChar(int x, int y, char character) {
        this.graphics.setCharacter(x, y, character);
    }

    private static boolean isKeyStrokeType(KeyStroke keyStroke, KeyType type) {
        return keyStroke.getKeyType() == type;
    }

    private static boolean isKeyStrokeCharacter(KeyStroke keyStroke, char c) {
        return isKeyStrokeType(keyStroke, KeyType.Character) && keyStroke.getCharacter() == c;
    }

    @Override
    public ACTION getNextAction() throws IOException {
        KeyStroke keyStroke = this.screen.readInput();

        if (this.isKeyStrokeType(keyStroke, KeyType.EOF)) return ACTION.QUIT;
        if (this.isKeyStrokeCharacter(keyStroke, 'q')) return ACTION.QUIT;

        if (this.isKeyStrokeCharacter(keyStroke, 'w')) return ACTION.UP;
        if (this.isKeyStrokeCharacter(keyStroke, 'd')) return ACTION.RIGHT;
        if (this.isKeyStrokeCharacter(keyStroke, 's')) return ACTION.DOWN;
        if (this.isKeyStrokeCharacter(keyStroke, 'a')) return ACTION.LEFT;

        return ACTION.NONE;
    }
}
