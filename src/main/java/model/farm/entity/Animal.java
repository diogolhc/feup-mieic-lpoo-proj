package model.farm.entity;

import model.InGameTime;
import model.Position;

import java.io.Serializable;

public class Animal extends Entity {
    private InGameTime idleTime;

    public Animal(Position position) {
        super(position);
        this.idleTime = new InGameTime();
    }

    public InGameTime getIdleTime() {
        return idleTime;
    }

    public void setIdleTime(InGameTime idleTime) {
        this.idleTime = idleTime;
    }
}
