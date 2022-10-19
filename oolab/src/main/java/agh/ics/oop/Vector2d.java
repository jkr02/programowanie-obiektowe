package agh.ics.oop;

import java.util.Objects;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Vector2d {
    public final int x, y;
    public Vector2d(int x, int y){
        this.x=x;
        this.y=y;
    }
    @Override public String toString(){
        return("(" + this.x + "," + this.y +")");
    }
    boolean precedes(Vector2d other){
        return(this.x<=other.x & this.y<=other.y);
    }
    boolean follows(Vector2d other){
        return(this.x>=other.x & this.y>=other.y);
    }
    Vector2d add(Vector2d other){
        return(new Vector2d(this.x+other.x, this.y+ other.y ));
    }
    Vector2d subtract(Vector2d other){
        return(new Vector2d(this.x-other.x, this.y-other.y));
    }
    Vector2d upperRight(Vector2d other){
        return(new Vector2d(max(this.x, other.x), max(this.y, other.y)));
    }
    Vector2d lowerLeft(Vector2d other){
        return(new Vector2d(min(this.x, other.x), min(this.y, other.y)));
    }
    Vector2d opposite(){
        return(new Vector2d(this.x * (-1), this.y * (-1)));
    }

    @Override public int hashCode() {
        return Objects.hash(x,y);
    }

    @Override public boolean equals(Object other){
        if (this == other){
            return(true);
        }
        if (!(other instanceof Vector2d)){
            return(false);
        }
        Vector2d that = (Vector2d) other;
        if (this.x == that.x && this.y == that.y){
            return(true);
        }
        else return(false);
    }
}
