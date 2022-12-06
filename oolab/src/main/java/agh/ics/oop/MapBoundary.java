package agh.ics.oop;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{
    private SortedSet<Vector2d> xPosition = new TreeSet<>(Comparator.comparing((Vector2d vc1) -> vc1.x));
    private SortedSet<Vector2d> yPosition = new TreeSet<Vector2d>(Comparator.comparing((Vector2d vc1) -> vc1.y));

    @Override
    public void positionChanged(Vector2d prev, Vector2d next){
        xPosition.remove(prev);
        xPosition.add(next);
        yPosition.remove(prev);
        yPosition.add(next);
    }
    public void addElement(Vector2d vector){
        xPosition.add(vector);
        yPosition.add(vector);
    }
    public void removeElement(Vector2d vector2d){
        xPosition.remove(vector2d);
        yPosition.remove(vector2d);
    }

    public Vector2d[] getDim(){
        return new Vector2d[]{xPosition.first().lowerLeft(yPosition.first()), xPosition.last().upperRight(yPosition.last())};
    }
}
