package model.farm.entity;

import model.InGameTime;
import model.Position;

public class Animal extends Entity {
    private InGameTime idleTime;

    public Animal(Position position) {
        super(position);
        this.idleTime = new InGameTime();
    }

    public InGameTime getIdleTime() {
        return this.idleTime;
    }

    public void setIdleTime(InGameTime idleTime) {
        this.idleTime = idleTime;
    }
}
