package viewer.farm;

import gui.GUI;
import model.Weather;
import model.ChronologicalTime;


public class HUDViewer {

    public void draw(ChronologicalTime chronologicalTime, Weather weather, GUI gui) {
        /* TODO these hardcoded values are just for debug purposes
        *   This is temporary */
        gui.drawString(0,20, chronologicalTime.toString());
        gui.drawString(25,20, weather.getType().name()); // TODO maybe use some kind of related char to the type (?)
    }


}
