package model.farm.building;

import model.Position;
import model.farm.building.Building;
import model.region.EdificeUntraversableRegion;
import model.region.PositionRegion;
import model.region.Region;

public class Edifice extends Building {
    private final String name;

    public Edifice(Position topLeft, String name) {
        super(topLeft);
        this.name = name;
    }

    public Edifice(String name) {
        this(new Position(0, 0), name);
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

    @Override
    public String getName() {
        return this.name;
    }
}
