package model;

public class Farm {
    private final Farmer farmer;
    private final CropField cropField; // TODO Experimental, shouldn't exist in the final version
    private int width;
    private int height;

    public Farm(int width, int height) {
        // TODO width and height at least 5
        this.width = width;
        this.height = height;
        this.farmer = new Farmer(new Position(3, 3));
        this.cropField = new CropField(new Position(5, 1));
    }

    public Farmer getFarmer() {
        return this.farmer;
    }

    public boolean isTraversable(Position position) {
        int x = position.getX();
        int y = position.getY();
        if (x <= 0 || x >= this.width-1) return false;
        if (y <= 0 || y >= this.height-1) return false;
        // TODO check other objects
        if (!this.cropField.isTraversable(position)) return false;
        return true;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public CropField getCropField() {
        return this.cropField;
    }
}
