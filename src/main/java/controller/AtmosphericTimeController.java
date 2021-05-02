package controller;


import model.AtmosphericTime;
import model.ChronologicalTime;

public class AtmosphericTimeController {
    private int lastDay;

    public AtmosphericTimeController() {
        this.lastDay = 0;
    }

    public void updateTime(AtmosphericTime atmosphericTime, int currentDay) {
        if (currentDay <= lastDay)
            return;

        this.lastDay = currentDay;
        AtmosphericTime.TYPE current = atmosphericTime.getType();

        double chance = Math.random();
        switch (current) {
            case SUNNY:
                if (chance < 0.1) {
                    atmosphericTime.setType(AtmosphericTime.TYPE.RAINY);
                } else if (chance < 0.4) {
                    atmosphericTime.setType(AtmosphericTime.TYPE.CLOUDY);
                }
                // else maintains SUNNY
                break;

            case CLOUDY:
                if (chance < 0.2) {
                    atmosphericTime.setType(AtmosphericTime.TYPE.SUNNY);
                } else if (chance < 0.5) {
                    atmosphericTime.setType(AtmosphericTime.TYPE.RAINY);
                } else if (chance < 0.51) {
                    atmosphericTime.setType(AtmosphericTime.TYPE.THUNDERSTORM);
                } else if (chance < 0.52) {
                    atmosphericTime.setType(AtmosphericTime.TYPE.WINDSTORM);
                }
                // else maintains CLOUDY
                break;

            case RAINY:
                if (chance < 0.1) {
                    atmosphericTime.setType(AtmosphericTime.TYPE.SUNNY);
                } else if (chance < 0.4) {
                    atmosphericTime.setType(AtmosphericTime.TYPE.CLOUDY);
                } else if (chance < 0.5) {
                    atmosphericTime.setType(AtmosphericTime.TYPE.THUNDERSTORM);
                } else if (chance < 0.52) {
                    atmosphericTime.setType(AtmosphericTime.TYPE.WINDSTORM);
                }
                // else maintains RAINY
                break;

            case THUNDERSTORM:
                if (chance < 0.45) {
                    atmosphericTime.setType(AtmosphericTime.TYPE.CLOUDY);
                } else if (chance < 0.9) {
                    atmosphericTime.setType(AtmosphericTime.TYPE.RAINY);
                }
                // else maintains THUNDERSTORM
                break;

            case WINDSTORM:
                if (chance < 0.49) {
                    atmosphericTime.setType(AtmosphericTime.TYPE.CLOUDY);
                } else if (chance < 0.98) {
                    atmosphericTime.setType(AtmosphericTime.TYPE.RAINY);
                }
                // else maintains WINDSTORM
                break;

        }

    }


}
