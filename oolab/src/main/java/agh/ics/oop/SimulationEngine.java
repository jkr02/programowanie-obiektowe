package agh.ics.oop;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine{
    private final IWorldMap map;
    private final MoveDirection[] kierunki;
    private List <Vector2d> pozycje = new ArrayList<Vector2d>();
    private final JTextArea arena;
    public SimulationEngine(MoveDirection[] kierunki, IWorldMap map, Vector2d[] positions, JTextArea arena){
        this.kierunki=kierunki;
        this.map=map;
        this.arena = arena;
        for (int i=0; i<positions.length; i++){
            Animal animal = new Animal(this.map, positions[i]);
            if(this.map.place(animal)){
                this.pozycje.add(positions[i]);
            }
            arena.setText(map.toString());
            System.out.print(map);
        }
    }
    @Override
    public void run() {
        System.out.println(map);
        if (pozycje.size()>0) {
            for (int i = 0; i < kierunki.length; i++) {
                Animal animal = (Animal) map.objectAt(pozycje.get(i % pozycje.size()));
                animal.move(kierunki[i]);
                pozycje.set(i % pozycje.size(), animal.position);
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                arena.setText(map.toString());
                System.out.println(map);
            }
        }
    }
}
