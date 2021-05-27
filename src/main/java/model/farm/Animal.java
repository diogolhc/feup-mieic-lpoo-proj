package model.farm;

import model.InGameTime;
import model.Position;

import java.io.Serializable;

// TODO maybe this could be unified with Farmer?
public class Animal implements Serializable {
    private Position position;
    private InGameTime idleTime;

    public Animal(Position position) {
        this.position = position;
        this.idleTime = new InGameTime();
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public InGameTime getIdleTime() {
        return idleTime;
    }

    public void setIdleTime(InGameTime idleTime) {
        this.idleTime = idleTime;
    }
}
