package model;

public class CropField extends InteractiveElement {
    public static final int CROP_FIELD_SIZE = 4;

    public CropField(Position topLeft) {
        super(topLeft);
    }

    public Position getPosition() {
        return topLeft;
    }

    public boolean isTraversable(Position position) {
        Position invalidPosition = this.topLeft.getRight().getDown();
        if (position.equals(invalidPosition)) return false;
        invalidPosition = invalidPosition.getRight();
        if (position.equals(invalidPosition)) return false;
        invalidPosition = invalidPosition.getDown();
        if (position.equals(invalidPosition)) return false;
        invalidPosition = invalidPosition.getLeft();
        if (position.equals(invalidPosition)) return false;
        return true;
    }
}
