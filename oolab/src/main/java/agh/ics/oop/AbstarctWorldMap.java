package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

abstract class AbstarctWorldMap implements IWorldMap, IPositionChangeObserver{
    protected Map<Vector2d, IMapElement> elements=new HashMap<>();
    protected ArrayList<Animal> animals=new ArrayList<>();
    protected Vector2d min_position;
    protected Vector2d max_position;
    protected Vector2d right_corner;
    protected Vector2d left_corner;
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Animal animal=(Animal) objectAt(oldPosition);
        elements.remove(oldPosition);
        elements.put(newPosition, animal);
    }
    public ArrayList<Animal> getAnimals(){
        return new ArrayList<Animal>(animals);
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(left_corner) && position.precedes(right_corner) && !isOccupied(position);
    }
    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition()) && !isOccupied(animal.getPosition())){
            elements.put(animal.getPosition(), animal);
            animals.add(animal);
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
}
