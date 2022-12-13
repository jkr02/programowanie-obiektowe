package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Platform;

import javax.swing.*;

public class SimulationEngine implements IEngine, Runnable{
    private final IWorldMap map;
    private MoveDirection[] kierunki;
    private final Vector2d[] positions;
    private JTextArea pole=null;
    private App observer;
    private boolean openApp;
    private int moveDelay;

    public SimulationEngine(MoveDirection[] kierunki, IWorldMap map, Vector2d[] positions, JTextArea pole){
        this.kierunki=kierunki;
        this.map=map;
        this.pole = pole;
        this.positions=positions;
        for (Vector2d position : positions) {
            Animal animal = new Animal(this.map, position);
            this.map.place(animal);
            pole.setText(map.toString());
        }
    }
    public SimulationEngine(MoveDirection[] kierunki, IWorldMap map, Vector2d[] positions){
        this.kierunki=kierunki;
        this.map=map;
        this.positions=positions;
        for (Vector2d position : positions) {
            Animal animal = new Animal(this.map, position);
            if(!this.map.place(animal)){
                throw new IllegalArgumentException(position + " is not legal position");
            }
            System.out.print(map);
        }
    }
    public SimulationEngine(IWorldMap map, Vector2d[] positions, App app, int moveDelay){
        this.positions=positions;
        this.map=map;
        this.observer=app;
        this.openApp=true;
        this.moveDelay=moveDelay;
        for (Vector2d position : positions) {
            Animal animal = new Animal(this.map, position);
            if(!this.map.place(animal)){
                throw new IllegalArgumentException(position + " is not legal position");
            }
        }
    }
    @Override
    public void run() {
        if (this.openApp){
            Platform.runLater(this.observer::updateMap);
            try {
                Thread.sleep(moveDelay);
                if (positions.length > 0) {
                    for (int i = 0; i < kierunki.length; i++) {
                        Vector2d pos = positions[i % positions.length];
                        Animal zwierz = (Animal) (map.objectAt(pos));
                        zwierz.move(kierunki[i]);
                        positions[i % positions.length] = zwierz.getPosition();
                        if (map instanceof GrassField) {
                            ((GrassField) map).placeGrass();
                        }
                        Platform.runLater(this.observer::updateMap);
                        Thread.sleep(moveDelay);
                    }
                }
//                Platform.exit();
            }catch (InterruptedException ex){
                throw new RuntimeException(ex + "ups cos poszlo nie tak :(");
            }
        }
        else if (pole == null) {
            System.out.println(map);
            if (positions.length > 0) {
                for (int i = 0; i < kierunki.length; i++) {
                    Vector2d pos = positions[i % positions.length];
                    Animal zwierz = (Animal) (map.objectAt(pos));
                    zwierz.move(kierunki[i]);
                    positions[i % positions.length] = zwierz.getPosition();
                    if (map instanceof GrassField) {
                        ((GrassField) map).placeGrass();
                    }
                    System.out.println(map);
                }
            }
        }
    }
    public void setKierunki(MoveDirection[] directions){
        this.kierunki=directions;
    }
}