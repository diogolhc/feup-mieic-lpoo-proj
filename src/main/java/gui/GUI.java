package gui;

import java.io.IOException;

public interface GUI {
    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    void setBackgroundColor(String color);

    void setForegroundColor(String color);

    String getBackGroundColor(int x, int y);

    String getForegroundColor(int x, int y);

    void drawChar(int x, int y, char character);

    void drawString(int x, int y, String string);

    GUI.ACTION getNextAction() throws IOException;

    enum ACTION {
        NONE, QUIT, MOVE_UP, MOVE_RIGHT, MOVE_DOWN, MOVE_LEFT, INTERACT
    }

}
