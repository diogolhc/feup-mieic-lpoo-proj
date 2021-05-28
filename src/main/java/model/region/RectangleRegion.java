package model.region;

import model.Position;

public class RectangleRegion implements Region {
    private final Position topLeft;
    private final int width;
    private final int height;

    public RectangleRegion(Position topLeft, int width, int height) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
    }

    public Position getTopLeftPosition() {
        return this.topLeft;
    }

    public RectangleRegion getAt(Position position) {
        return new RectangleRegion(position, this.width, this.height);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean intersects(RectangleRegion region) {
        int left1 = this.topLeft.getX();
        int right1 = left1 + this.width - 1;
        int top1 = this.topLeft.getY();
        int bottom1 = top1 + this.height - 1;

        int left2 = region.topLeft.getX();
        int right2 = left2 + region.width - 1;
        int top2 = region.topLeft.getY();
        int bottom2 = top2 + region.height - 1;

        return left1 <= right2 && left2 <= right1 && top1 <= bottom2 && top2 <= bottom1;
    }

    public boolean contains(RectangleRegion region) {
        int left1 = this.topLeft.getX();
        int right1 = left1 + this.width - 1;
        int top1 = this.topLeft.getY();
        int bottom1 = top1 + this.height - 1;

        int left2 = region.topLeft.getX();
        int right2 = left2 + region.width - 1;
        int top2 = region.topLeft.getY();
        int bottom2 = top2 + region.height - 1;

        return left2 >= left1 && right2 <= right1 && top2 >= top1 && bottom2 <= bottom1;
    }

    @Override
    public boolean contains(Position position) {
        position = position.getRelativeTo(this.topLeft);
        int x = position.getX();
        int y = position.getY();
        if (x < 0 || x >= this.width) return false;
        if (y < 0 || y >= this.height) return false;
        return true;
    }
}
