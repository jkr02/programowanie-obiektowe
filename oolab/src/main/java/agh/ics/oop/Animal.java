package agh.ics.oop;

public class Animal {
    private MapDirection direction;
    private Vector2d position;
    public Animal(){
        this.direction=MapDirection.NORTH;
        this.position=new Vector2d(2,2);
    }

    @Override
    public String toString() {
        return (position.toString()+ " " + direction.toString());
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
            if (tmp.x<=4 & tmp.x>=0 & tmp.y>=0 & tmp.y<=4){
                this.position=tmp;
            }
        }
        if (direction.equals(MoveDirection.BACKWARD)) {
            Vector2d tmp = this.position.subtract(this.direction.toUnitVector());
            if (tmp.x<=4 & tmp.x>=0 & tmp.y>=0 & tmp.y<=4){
                this.position=tmp;
            }
        }
    }

}
