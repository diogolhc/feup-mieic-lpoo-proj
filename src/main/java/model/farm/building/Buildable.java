package model.farm.building;

import model.Position;
import model.farm.Currency;

public abstract class Buildable extends Building {
    public Buildable(Position topLeft) {
        super(topLeft);
    }

    public abstract Currency getBuildPrice();
}
