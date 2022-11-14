package agh.ics.oop;

import javax.swing.*;
import java.util.ArrayList;

public class SimulationEngine implements IEngine{
    private final IWorldMap map;
    private final MoveDirection[] kierunki;
    //private final List <Vector2d> pozycje = new ArrayList<>();
    private final JTextArea pole;
    public SimulationEngine(MoveDirection[] kierunki, IWorldMap map, Vector2d[] positions, JTextArea pole){
        this.kierunki=kierunki;
        this.map=map;
        this.pole = pole;
        for (Vector2d position : positions) {
            Animal animal = new Animal(this.map, position);
            this.map.place(animal);
            pole.setText(map.toString());
            System.out.print(map);
        }
    }
    @Override
    public void run() {
        //System.out.println(map);
        RectangularMap mapa = (RectangularMap) this.map;
        ArrayList<Animal> animals = mapa.getAnimals();
        if (animals.size()>0) {
            for (int i = 0; i < kierunki.length; i++) {
                animals.get(i%animals.size()).move(kierunki[i]);
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                pole.setText(map.toString());
                //System.out.println(map);
            }
        }
    }
}
