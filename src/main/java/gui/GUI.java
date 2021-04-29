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

    GUI.ACTION getNextAction() throws IOException;

    enum ACTION {
        UP, RIGHT, DOWN, LEFT, QUIT, NONE;
    }

}
