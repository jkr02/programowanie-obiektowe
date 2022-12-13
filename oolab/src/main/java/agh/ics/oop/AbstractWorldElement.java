package agh.ics.oop;

abstract class AbstractWorldElement implements IMapElement{
    protected Vector2d position;

    public String getLink(){
        String link;
        switch (this.toString()){
            case ("N") -> link="src/main/resources/tyl.png";
            case ("S") -> link="src/main/resources/przod.jpg";
            case ("W") -> link="src/main/resources/lewo.png";
            case ("E") -> link="src/main/resources/prawo.png";
            case ("*") -> link="src/main/resources/grass.png";
            default -> link=null;
        }
        return link;
    }
    public Vector2d getPosition(){
        return position;
    }
}
