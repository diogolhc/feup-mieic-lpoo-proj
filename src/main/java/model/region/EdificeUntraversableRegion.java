package model.region;

import model.Position;

public class EdificeUntraversableRegion implements Region {
    private Position topLeft;

    public EdificeUntraversableRegion(Position topLeft) {
        this.topLeft = topLeft;
    }

    @Override
    public boolean contains(Position position) {
        position = position.getRelativeTo(this.topLeft);
        int x = position.getX();
        int y = position.getY();
        if (y == 0 && x >= 1 && x <= 5) return true;
        if (y >= 1 && y <= 5 && x >= 0 && x <= 6) return true;

        return false;
    }
}
