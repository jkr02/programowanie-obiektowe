package agh.ics.oop;

public class Grass extends AbstractWorldElement{
    public Grass(Vector2d pozycja){
        this.position=pozycja;
    }

    @Override
    public String toString(){
        return "*";
    }
}
