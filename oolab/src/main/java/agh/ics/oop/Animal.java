package agh.ics.oop;

public class Animal {
    protected IWorldMap map;
    private MapDirection direction;
    protected Vector2d position;
    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map=map;
        this.direction=MapDirection.NORTH;
        this.position=initialPosition;
    }

    @Override
    public String toString() {
        return (direction.toString());
    }
    boolean isAt(Vector2d position){
        if (this.position.equals(position)){
            return true;
        }
        return false;
    }
    void move(MoveDirection direction){
        if (direction.equals(MoveDirection.LEFT)){
            this.direction=this.direction.previous();
        }
        if (direction.equals(MoveDirection.RIGHT)) {
            this.direction=this.direction.next();
        }
        if (direction.equals(MoveDirection.FORWARD)){
            Vector2d tmp = this.position.add(this.direction.toUnitVector());
            if (map.canMoveTo(tmp) && !map.isOccupied(tmp)){
                this.position=tmp;
            }
        }
        if (direction.equals(MoveDirection.BACKWARD)) {
            Vector2d tmp = this.position.subtract(this.direction.toUnitVector());
            if (map.canMoveTo(tmp) && !map.isOccupied(tmp)){
                this.position=tmp;
            }
        }
    }

}
