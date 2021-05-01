package viewer.farm;

import gui.GUI;
import model.ChronologicalTime;

public class ChronologicalTimeViewer {
    public void draw(ChronologicalTime chronologicalTime, GUI gui) {
        gui.drawString(0,20, chronologicalTime.toString()); /* TODO temporary
                                                                    game will have some kind of HUD
                                                                    and position will be related to it
                                                                  */
    }
}
