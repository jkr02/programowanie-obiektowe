package agh.ics.oop;

import java.util.ArrayList;

public class Animal extends AbstractWorldElement{

    private IWorldMap map=new RectangularMap(4,4);
    private MapDirection direction=MapDirection.NORTH;
    protected final ArrayList<IPositionChangeObserver> observers=new ArrayList<>();

    public Animal(){
        this.position=new Vector2d(2,2);
    }
    public Animal(IWorldMap map){
        this.position=new Vector2d(2,2);
        this.map=map;
    }
    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map=map;
        this.position=initialPosition;
    }
    @Override
    public String toString() {
        return (direction.toString());
    }
    boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    void move(MoveDirection direction){
        Vector2d pos = this.position;
        if (direction.equals(MoveDirection.LEFT)){
            this.direction=this.direction.previous();
        }
        if (direction.equals(MoveDirection.RIGHT)) {
            this.direction=this.direction.next();
        }
        if (direction.equals(MoveDirection.FORWARD)){
            Vector2d tmp = this.position.add(this.direction.toUnitVector());
            if (map.canMoveTo(tmp)){
                this.position=tmp;
            }
        }
        if (direction.equals(MoveDirection.BACKWARD)) {
            Vector2d tmp = this.position.subtract(this.direction.toUnitVector());
            if (map.canMoveTo(tmp)){
                this.position=tmp;
            }
        }
        positionChanged(pos, position);
    }
    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }
    public void positionChanged(Vector2d prev, Vector2d next) {
        for (IPositionChangeObserver observer : observers) {
            observer.positionChanged(prev, next);
        }
    }
}
