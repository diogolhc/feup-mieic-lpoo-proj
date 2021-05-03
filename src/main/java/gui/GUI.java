package gui;

import controller.MouseListener;

import java.io.IOException;

public interface GUI {
    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    void setBackgroundColor(Color color);

    void setForegroundColor(Color color);

    Color getBackGroundColor(int x, int y);

    Color getForegroundColor(int x, int y);

    void drawChar(int x, int y, char character);

    void drawString(int x, int y, String string);

    GUI.ACTION getNextAction() throws IOException;

    void setMouseListener(MouseListener mouseListener);

    enum ACTION {
        NONE, QUIT, MOVE_UP, MOVE_RIGHT, MOVE_DOWN, MOVE_LEFT, INTERACT
    }

}
