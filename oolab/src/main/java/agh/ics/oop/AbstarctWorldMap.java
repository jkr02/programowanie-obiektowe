package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

abstract class AbstarctWorldMap implements IWorldMap, IPositionChangeObserver{
    protected Map<Vector2d, IMapElement> elements=new HashMap<>();
    protected Vector2d min_position;
    protected Vector2d max_position;
    protected Vector2d right_corner;
    protected Vector2d left_corner;
    public MapBoundary mapBoundary;
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Animal animal=(Animal) objectAt(oldPosition);
        elements.remove(oldPosition);
        elements.put(newPosition, animal);
        mapBoundary.positionChanged(oldPosition, newPosition);
    }
    public Map<Vector2d, IMapElement> getElements(){
        return new HashMap<>(elements);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(left_corner) && position.precedes(right_corner) && !isOccupied(position);
    }
    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition()) && !isOccupied(animal.getPosition())){
            elements.put(animal.getPosition(), animal);
            mapBoundary.addElement(animal.getPosition());
            animal.addObserver(this);
            return true;
        }
        return false;
    }
    public boolean isOccupied(Vector2d position) {
        return elements.containsKey(position);
    }
    @Override
    public String toString() {
        return(new MapVisualizer(this).draw(min_position, max_position));
    }
    @Override
    public Object objectAt(Vector2d position) {
        if (isOccupied(position)){
            return elements.get(position);
        }
        return null;
    }
    public Vector2d get_min_position(){
        return min_position;
    }
    public Vector2d get_max_position(){
        return max_position;
    }
//    @Override
//    public void setAppObserver(IPositionChangeObserver appObserver) {
//        this.appObserver = appObserver;
//    }
}
