package agh.ics.oop;

import java.util.ArrayList;

import static java.lang.Math.*;

public class GrassField extends AbstarctWorldMap{
    private final Integer n;
    private final int zakres;
    private final ArrayList<Grass> pole;
    public GrassField(Integer n){
        if (n<0)this.n=0;
        else this.n=n;
        this.zakres=(int) (sqrt(n)*sqrt(10));
        this.right_corner = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.left_corner = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        this.max_position=new Vector2d(0,0);
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
    public boolean placeGrass(){
        boolean put=false;
        while (pole.size()<n){
            if (!canplaceGrass()){
                return false;
            }
            else {
                while (true) {
                    Vector2d wsp = new Vector2d((int) (random() * zakres), (int) (random() * zakres));
                    if (objectAt(wsp) == null) {
                        pole.add(new Grass(wsp));
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
            return true;
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
        Vector2d minimum=new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        for (Animal animal: animals){
            minimum=new Vector2d(min(minimum.x, animal.getPosition().x), min(minimum.y, animal.getPosition().y));
        }
        for (Grass grass: pole){
            minimum=new Vector2d(min(minimum.x, grass.getPosition().x), min(minimum.y, grass.getPosition().y));
        }
        if (animals.size()==0 & pole.size()==0){
            return new Vector2d(0,0);
        }
        return minimum;
    }
    private Vector2d maximize(){
        Vector2d maksimum=new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        for (Animal animal: animals){
            maksimum=new Vector2d(max(maksimum.x,animal.getPosition().x), max(maksimum.y,animal.getPosition().y));
        }
        for (Grass grass: pole){
            maksimum=new Vector2d(max(maksimum.x,grass.getPosition().x), max(maksimum.y,grass.getPosition().y));
        }
        if (animals.size()==0 & pole.size()==0){
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
