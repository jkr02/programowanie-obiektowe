package agh.ics.oop;

public class RectangularMap extends AbstarctWorldMap{
    public RectangularMap(int width, int height) {
        this.min_position = new Vector2d(0, 0);
        this.max_position = new Vector2d(width, height);
        this.left_corner=this.min_position;
        this.right_corner=this.max_position;
    }
}
