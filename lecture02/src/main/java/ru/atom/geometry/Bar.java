package ru.atom.geometry;

import java.util.Objects;

public class Bar implements Collider {
    private int firstCornerX, firstCornerY, secondCornerX, secondCornerY;

    public Bar(int firstCornerX, int firstCornerY, int secondCornerX, int secondCornerY) {
        this.firstCornerX = firstCornerX;
        this.firstCornerY = firstCornerY;
        this.secondCornerX = secondCornerX;
        this.secondCornerY = secondCornerY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bar bar = (Bar) o;
        if ((this.getMaxX() == bar.getMaxX()) && this.getMinX() == bar.getMinX() && this.getMaxY() == bar.getMaxY() && this.getMinY() == bar.getMinY())
            return true;
        return firstCornerX == bar.firstCornerX &&
                firstCornerY == bar.firstCornerY &&
                secondCornerX == bar.secondCornerX &&
                secondCornerY == bar.secondCornerY;
    }

    @Override
    public boolean isColliding(Collider other) {
        if (other == null) return false;

        if (other instanceof Point) {
            Point point = (Point) other;

            if ((((point.getX() >= this.firstCornerX) && (point.getX() <= this.secondCornerX))
                    || ((point.getX() >= this.secondCornerX) && (point.getX() <= this.firstCornerX)))
                    && (((point.getY() >= this.firstCornerY) && (point.getY() <= this.secondCornerY))
                    || ((point.getY() >= this.secondCornerY) && (point.getY() <= this.firstCornerY)))
            ) return true;
        }

        if (other instanceof Bar) {
            Bar bar = (Bar) other;
            if (equals(bar)) return true;
            if ((this.getMaxX() >= bar.getMinX()) && ((this.getMinX() <= bar.getMaxX()))
                    && (this.getMaxY() >= bar.getMinY()) && (this.getMinY() <= bar.getMaxY())) return true;
        }
        return false;
    }

    public int getMaxX() {
        return this.firstCornerX <= this.secondCornerX ? this.secondCornerX : this.firstCornerX;
    }

    public int getMinX() {
        return this.firstCornerX <= this.secondCornerX ? this.firstCornerX : this.secondCornerX;
    }

    public int getMaxY() {
        return this.firstCornerY <= this.secondCornerY ? this.secondCornerY : this.firstCornerY;
    }

    public int getMinY() {
        return this.firstCornerY <= this.secondCornerY ? this.firstCornerY : this.secondCornerY;
    }
}
