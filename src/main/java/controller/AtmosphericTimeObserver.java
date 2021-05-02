package controller;

import model.AtmosphericTime;

public interface AtmosphericTimeObserver {
    void notifyAtmosphericTimeChange(AtmosphericTime.TYPE type);

}
