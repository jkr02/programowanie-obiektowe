package agh.ics.oop;

public class Animal {
    private IWorldMap map=new RectangularMap(4,4);
    private MapDirection direction=MapDirection.NORTH;
    private Vector2d position=new Vector2d(2,2);

    public Animal(){
    }
    public Animal(IWorldMap map){
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
    public Vector2d getPosition(){
        return position;
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
    }

}
