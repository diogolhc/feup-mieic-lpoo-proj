package model;


public class Weather {
    private TYPE type;

    public Weather(TYPE type) {
        this.type = type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public Weather.TYPE getType() {
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
