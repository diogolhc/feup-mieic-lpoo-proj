package model.farm.entity;

import model.Position;

import java.io.Serializable;

public class Entity implements Serializable {
    private Position position;

    public Entity(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}
