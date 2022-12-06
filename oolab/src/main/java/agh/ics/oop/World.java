package agh.ics.oop;


import agh.ics.oop.gui.App;
import javafx.application.Application;

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
        try {
            Application.launch(App.class, args);
        }catch (IllegalArgumentException ex){
            System.out.println(ex);
        }
        System.out.println("stop");
    }
    static int emp(String[] arg){
        int k=0;
        for (String s : arg) {
            if (!s.equals("l") && !s.equals("r") && !s.equals("f") && !s.equals("b")) {
                k += 1;
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
