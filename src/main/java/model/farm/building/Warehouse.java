package model.farm.building;

import model.Position;

public class Warehouse extends Building {
    public static final int WAREHOUSE_SIZE = 6;

    public Warehouse(Position topLeft) {
        super(topLeft);
    }

    @Override
    public boolean isTraversable(Position position) {
        position = position.getRelativeTo(this.getTopLeftPosition());
        int x = position.getX();
        int y = position.getY();
        if (y == 0 && x >= 1 && x <= 5) return false;
        if (y >= 1 && y <= 5 && x >= 0 && x <= 6) return false;

        return true;
    }

    @Override
    public boolean isInInteractiveZone(Position position) {
        return position.equals(this.getTopLeftPosition().getTranslated(new Position(4, 6)));
    }
}
