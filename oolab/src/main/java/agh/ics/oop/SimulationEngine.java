package agh.ics.oop;

import javax.swing.*;

public class SimulationEngine implements IEngine{
    private final IWorldMap map;
    private final MoveDirection[] kierunki;
    private final Vector2d[] positions;
    private JTextArea pole=null;

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
    @Override
    public void run() {
        if (pole == null) {
            System.out.println(map);
        }
        if (positions.length>0) {
            for (int i = 0; i < kierunki.length; i++) {
                Vector2d pos = positions[i % positions.length];
                Animal zwierz = (Animal) (map.objectAt(pos));
                zwierz.move(kierunki[i]);
                positions[i % positions.length] = zwierz.getPosition();
//                if (pole != null){
//                    int x = map.get_max_position().x - map.get_min_position().x;
//                    int y = map.get_max_position().y - map.get_min_position().y;
//                    int font_size = 800/max(2* x, max((y + 3) * 2, 1));
//                    pole.setFont(new Font(Font.MONOSPACED, Font.BOLD, font_size));
//                    try {
//                        Thread.sleep(1000);
//                    }catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                    if (map instanceof GrassField){
//                        ((GrassField) map).placeGrass();
//                    }
//                    pole.setText(map.toString());
//                }
//                else {
                if (map instanceof GrassField) {
                    ((GrassField) map).placeGrass();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(map);
           // }
            }
        }
    }
}