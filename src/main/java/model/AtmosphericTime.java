package model;


public class AtmosphericTime {
    private TYPE type;

    public AtmosphericTime(TYPE type) {
        this.type = type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public AtmosphericTime.TYPE getType() {
        return this.type;
    }

    public enum TYPE {
        SUNNY, // drys the land
        CLOUDY, // no drying but no moister added either
        RAINY, // moist the land
        THUNDERSTORM, // occasionally a position is hit by a thunder light
        WINDSTORM // everything (?) suffers damages
    }

}
