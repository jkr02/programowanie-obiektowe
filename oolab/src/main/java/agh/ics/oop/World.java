package agh.ics.oop;


import javax.swing.*;
import java.awt.*;

import static java.lang.Math.max;

public class World {
    public static void main(String[] args){
        System.out.println("Start");
//        Direction[] kierunki = assignment(args);
//        run(kierunki);
//        Vector2d position1 = new Vector2d(1,2);
//        System.out.println(position1);
//        Vector2d position2 = new Vector2d(0,1);
//        System.out.println(position2);
////        System.out.println(position1.add(position2));
////        System.out.println(position1.equals(position2));
////        System.out.println("Stop");
//        Animal Grogu = new Animal();
//        System.out.println(Grogu.toString());
//        Grogu.move(MoveDirection.RIGHT);
//        Grogu.move(MoveDirection.FORWARD);
//        Grogu.move(MoveDirection.FORWARD);
//        System.out.println(Grogu.toString());
//        Grogu.move(MoveDirection.FORWARD);
//        System.out.println(Grogu.toString());
//        Animal Grogu = new Animal();
//        OptionsParser r2d2= new OptionsParser();
//        MoveDirection[] direction = r2d2.parse(args);
//        for(MoveDirection x_wing: direction){
//            Grogu.move(x_wing);
//            System.out.println(Grogu);
//        }
//        System.out.println(Grogu);

        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };

        JFrame frame = new JFrame();
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextArea pole = new JTextArea(map.toString());
        int font_size = 800/max(2*10, max((5 + 3) * 2, 1));
        pole.setFont(new Font(Font.MONOSPACED, Font.BOLD, font_size));
        frame.add(pole);
        frame.setVisible(true);

        IEngine engine = new SimulationEngine(directions, map, positions, pole);
        engine.run();
        System.out.println("stop");
    }
    static int emp(String[] arg){
        int k=0;
        for (int i=0; i<arg.length; i+=1){
            if (!arg[i].equals("l") && !arg[i].equals("r") && !arg[i].equals("f") && !arg[i].equals("b")){
                k+=1;
            }
        }
        return(k);
    }

    Direction[] assignment(String[] args){
        int k = emp(args);
        Direction[] a = new Direction[args.length-k];
        int p=0;
        for (int i=0; i<args.length; i+=1) {
            if (assign(args[i]).equals("")){
                p+=1;
            }
            else {
                a[i-p] = Direction.valueOf(assign(args[i]));
            }
        }
        return(a);
    }
    static String assign(String a){
        return(switch(a){
            case "f" -> "FORWARD";
            case "b" -> "BACKWARD";
            case "r" -> "RIGHT";
            case "l" -> "LEFT";
            default -> "";
        });
    }
    static void run(Direction[] a){
        for (int i=0; i<a.length; i+=1){
            String message = switch (a[i]){
                case FORWARD -> "Do przodu";
                case BACKWARD -> "Do tylu";
                case LEFT -> "Skreca w lewo";
                case RIGHT -> "Skreca w prawo";
            };
            if (i<a.length-1) {
                System.out.println(message + ",");
            }
            else {
                System.out.println(message);
            }
        }
    }
}
