package model.farm.building;

import model.Position;

public class House extends Building {
    public static final int HOUSE_SIZE = 6;

    public House(Position topLeft) {
        super(topLeft);
    }

    public boolean isTraversable(Position position) {
        int x = this.getTopLeftPosition().getX();
        int y = this.getTopLeftPosition().getY();
        if (position.getX() > x
                && position.getX() < x + HOUSE_SIZE
                && position.getY() > y
                && position.getY() < y + HOUSE_SIZE)
            return false;

        return true;
    }

    @Override
    public boolean contains(Position position) {
        return false; // TODO
    }
}
