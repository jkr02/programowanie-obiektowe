package agh.ics.oop;

import java.util.ArrayList;

class RectangularMap implements IWorldMap{
    private final Vector2d wielkosc;
    private final ArrayList<Animal> animals = new ArrayList<>();
    public RectangularMap(int width, int height){
        this.wielkosc=new Vector2d(width,height);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position.follows(new Vector2d(0, 0)) && position.precedes(wielkosc) && !isOccupied(position)){
            return true;
        }
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition()) && !isOccupied(animal.getPosition())){
            animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.getPosition().equals(position)) {
                return animal;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return(new MapVisualizer(this).draw(new Vector2d(0,0), wielkosc));
    }

    public ArrayList<Animal> getAnimals(){
        return animals;
    }
}
