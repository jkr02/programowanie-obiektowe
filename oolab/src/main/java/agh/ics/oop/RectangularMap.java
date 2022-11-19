package agh.ics.oop;

class RectangularMap extends AbstarctWorldMap{
    public RectangularMap(int width, int height){
        this.min_position=new Vector2d(0,0);
        this.max_position=new Vector2d(width,height);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position.follows(new Vector2d(0, 0)) && position.precedes(max_position) && !isOccupied(position)){
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
}
