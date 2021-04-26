package model;

public class Farm {
    private final Farmer farmer;
    private int width;
    private int height;

    public Farm(int width, int height) {
        // TODO width and height at least 5
        this.width = width;
        this.height = height;
        this.farmer = new Farmer(3, 3);
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
        return true;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
