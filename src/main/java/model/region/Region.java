package model.region;

import model.Position;

public interface Region {
    boolean contains(Position position);
}
