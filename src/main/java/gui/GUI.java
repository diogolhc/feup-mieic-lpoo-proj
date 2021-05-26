package gui;

import java.io.IOException;

public interface GUI {
    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    int getWindowWidth();

    int getWindowHeight();

    void setBackgroundColor(Color color);

    void setForegroundColor(Color color);

    Color getBackgroundColor(int x, int y);

    Color getForegroundColor(int x, int y);

    void drawChar(int x, int y, char character);

    void drawString(int x, int y, String string);

    GUI.ACTION getNextAction() throws IOException;

    void setMouseListener(MouseListener mouseListener);

    enum ACTION {
        NONE, QUIT, MOVE_UP, MOVE_RIGHT, MOVE_DOWN, MOVE_LEFT, BACK, INTERACT
    }

}
