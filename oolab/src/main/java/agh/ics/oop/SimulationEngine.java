package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine{
    private IWorldMap map;
    private MoveDirection[] kierunki;
    private List<Animal> animals = new ArrayList<>();
    public SimulationEngine(MoveDirection[] kierunki, IWorldMap map, Vector2d[] positions){
        this.kierunki=kierunki;
        this.map=map;
        for (int i=0; i<positions.length; i++){
            if(this.map.place(new Animal(this.map, positions[i]))){
                animals.add((Animal) map.objectAt(positions[i]));
            }
        }
    }
    @Override
    public void run() {
        System.out.println(map);
        for (int i=0; i<kierunki.length; i++){
            animals.get(i%animals.size()).move(kierunki[i]);
            System.out.println(map);
        }
    }
}
