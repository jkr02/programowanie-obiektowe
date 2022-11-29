package agh.ics.oop;

import static java.lang.Math.*;

public class GrassField extends AbstarctWorldMap{
    private final Integer n;
    private final int zakres;
    private int grass_count=0;
    public GrassField(Integer n){
        if (n<0)this.n=0;
        else this.n=n;
        this.zakres=(int) (sqrt(n)*sqrt(10));
        this.right_corner = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.left_corner = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        this.max_position=new Vector2d(0,0);
        this.min_position=new Vector2d(0,0);
        placeGrass();
    }
    private boolean canplaceGrass(){
        for (int i=0; i<=zakres; i++){
            for (int j=0; j<=zakres; j++){
                if (!isOccupied(new Vector2d(i,j))){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean placeGrass(){
        boolean put=false;
        while (grass_count<n){
            if (!canplaceGrass()){
                return false;
            }
            else {
                while (true) {
                    Vector2d wsp = new Vector2d((int) (random() * zakres), (int) (random() * zakres));
                    if (objectAt(wsp) == null) {
                        elements.put(wsp, new Grass(wsp));
                        grass_count+=1;
                        put=true;
                        break;
                    }
                }
            }
        }
        return put;
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        if (super.canMoveTo(position)){
            return true;
        }
        if (objectAt(position)instanceof Grass){
            elements.remove(position);
            grass_count-=1;
            return true;
        }
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        if (super.place(animal)){
            return true;
        }
        if (objectAt(animal.getPosition())instanceof Grass){
            elements.remove(animal.getPosition());
            grass_count-=1;
            elements.put(animal.getPosition(), animal);
            animal.addObserver(this);
            placeGrass();
            return true;
        }
        return false;
    }
    private Vector2d minimize(){
        Vector2d minimum=new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        for (IMapElement element: elements.values()){
            minimum=new Vector2d(min(minimum.x, element.getPosition().x), min(minimum.y, element.getPosition().y));
        }
        if (elements.isEmpty()){
            return new Vector2d(0,0);
        }
        return minimum;
    }
    private Vector2d maximize(){
        Vector2d maksimum=new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        for (IMapElement element: elements.values()){
            maksimum=new Vector2d(max(maksimum.x, element.getPosition().x), max(maksimum.y, element.getPosition().y));
        }
        if (elements.isEmpty()){
            return new Vector2d(0,0);
        }
        return maksimum;
    }
    @Override
    public String toString(){
        min_position=minimize();
        max_position=maximize();
        return super.toString();
    }
}
