package viewer.farm;

import gui.GUI;
import model.AtmosphericTime;
import model.ChronologicalTime;


public class HUDViewer {

    public void draw(ChronologicalTime chronologicalTime, AtmosphericTime atmosphericTime, GUI gui) {
        /* TODO these hardcoded values are just for debug purposes
        *   This is temporary */
        gui.drawString(0,20, chronologicalTime.toString());
        gui.drawString(25,20, atmosphericTime.getType().name()); // TODO maybe use some kind of related char to the type (?)
    }


}
