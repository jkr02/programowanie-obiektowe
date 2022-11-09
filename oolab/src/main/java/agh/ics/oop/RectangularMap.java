package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

class RectangularMap implements IWorldMap{
    public final Vector2d wielkosc;
    public List<Animal> animals = new ArrayList<>();
    public RectangularMap(int width, int height){
        this.wielkosc=new Vector2d(width,height);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position.follows(new Vector2d(0, 0)) && position.precedes(wielkosc)){
            return true;
        }
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.position) && !isOccupied(animal.position)){
            animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (objectAt(position) == null){
            return false;
        }
        return true;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (int i=0; i<animals.size(); i++){
            if (animals.get(i).position.equals(position)){
                return animals.get(i);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return(new MapVisualizer(this).draw(new Vector2d(0,0), wielkosc));
    }
}
