package model.farm.building.edifice;

import model.Position;
import model.farm.building.Building;
import model.region.EdificeUntraversableRegion;
import model.region.PositionRegion;
import model.region.Region;

public abstract class Edifice extends Building {
    public Edifice(Position topLeft) {
        super(topLeft);
    }

    @Override
    public int getWidth() {
        return 7;
    }

    @Override
    public int getHeight() {
        return 7;
    }

    @Override
    public Region getUntraversableRegion() {
        return new EdificeUntraversableRegion(this.getTopLeftPosition());
    }

    @Override
    public Region getInteractiveRegion() {
        return new PositionRegion(this.getTopLeftPosition().getTranslated(new Position(4, 6)));
    }
}
