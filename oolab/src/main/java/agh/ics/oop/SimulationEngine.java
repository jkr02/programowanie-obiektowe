package agh.ics.oop;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.max;

public class SimulationEngine implements IEngine{
    private final IWorldMap map;
    private final MoveDirection[] kierunki;
    private JTextArea pole=null;

    public SimulationEngine(MoveDirection[] kierunki, IWorldMap map, Vector2d[] positions, JTextArea pole){
        this.kierunki=kierunki;
        this.map=map;
        this.pole = pole;
        for (Vector2d position : positions) {
            Animal animal = new Animal(this.map, position);
            this.map.place(animal);
            pole.setText(map.toString());
        }
    }
    public SimulationEngine(MoveDirection[] kierunki, IWorldMap map, Vector2d[] positions){
        this.kierunki=kierunki;
        this.map=map;
        for (Vector2d position : positions) {
            Animal animal = new Animal(this.map, position);
            this.map.place(animal);
            System.out.print(map);
        }
    }
    @Override
    public void run() {
        if (pole == null) {
            System.out.println(map);
        }
        ArrayList<Animal> animals = map.getAnimals();
        if (animals.size()>0) {
            for (int i = 0; i < kierunki.length; i++) {
                animals.get(i%animals.size()).move(kierunki[i]);
                if (pole != null){
                    int x = map.get_max_position().x - map.get_min_position().x;
                    int y = map.get_max_position().y - map.get_min_position().y;
                    int font_size = 800/max(2* x, max((y + 3) * 2, 1));
                    pole.setFont(new Font(Font.MONOSPACED, Font.BOLD, font_size));
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    pole.setText(map.toString());
                }
                if (map instanceof GrassField){
                    ((GrassField) map).placeGrass();
                }
                else System.out.println(map);
            }
        }
    }
}
