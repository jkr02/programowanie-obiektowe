package agh.ics.oop;

import java.util.ArrayList;

import static java.lang.Math.*;

public class GrassField extends AbstarctWorldMap{
    private final Integer n;
    private final int zakres;
    private final ArrayList<Grass> pole;
    public GrassField(Integer n){
        this.n=n;
        this.zakres=(int) sqrt(n*10);
        this.right_corner = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.left_corner = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        this.max_position=new Vector2d(zakres,zakres);
        this.min_position=new Vector2d(0,0);
        this.pole= new ArrayList<>();
        this.animals= new ArrayList<>();
        placeGrass();
    }
    private boolean canplaceGrass(){
        for (int i=0; i<=zakres; i++){
            for (int j=0; j<=zakres; j++){
                if (objectAt(new Vector2d(i,j))==null){
                    return true;
                }
            }
        }
        return false;
    }
    public void placeGrass(){
        while (pole.size()<n){
            if (!canplaceGrass()){
                return;
            }
            else {
                while (true) {
                    Vector2d wsp = new Vector2d((int) (random() * zakres), (int) (random() * zakres));
                    if (objectAt(wsp) == null) {
                        pole.add(new Grass(wsp));
                        break;
                    }
                }
            }
        }
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        if (super.canMoveTo(position)){
            return true;
        }
        if (objectAt(position)instanceof Grass){
            pole.remove(objectAt(position));
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
            pole.remove(objectAt(animal.getPosition()));
            animals.add(animal);
            placeGrass();
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (super.objectAt(position)!=null){
            return super.objectAt(position);
        }
        for (Grass trawa: pole){
            if (trawa.getPosition().equals(position)){
                return trawa;
            }
        }
        return null;
    }
    private Vector2d minimize(){
        Vector2d minimum=min_position;
        for (Animal animal: animals){
            minimum=new Vector2d(min(minimum.x, animal.getPosition().x), min(minimum.y, animal.getPosition().y));
        }
        return minimum;
    }
    private Vector2d maximize(){
        Vector2d maksimum=max_position;
        for (Animal animal: animals){
            maksimum=new Vector2d(max(maksimum.x,animal.getPosition().x), max(maksimum.y,animal.getPosition().y));
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
