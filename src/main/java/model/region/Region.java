package model.region;

import model.Position;

import java.io.Serializable;

public interface Region extends Serializable {
    boolean contains(Position position);
}
