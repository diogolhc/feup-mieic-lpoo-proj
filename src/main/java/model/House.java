package model;

public class House extends InteractiveElement {
    public static final int HOUSE_FIELD_SIZE = 6;

    public House(Position topLeft) {
        super(topLeft);
    }

    public Position getPosition() {
        return topLeft;
    }

    public boolean isTraversable(Position position) {

        if (position.getX() > this.topLeft.getX() && position.getX() < this.topLeft.getX() + HOUSE_FIELD_SIZE
            && position.getY() > this.topLeft.getY() && position.getY() < this.topLeft.getY() + HOUSE_FIELD_SIZE )
            return false;

        return true;
    }

}
