package agh.ics.oop;


public class World {
    public static void main(String[] args){
        System.out.println("Start");
//        Direction[] kierunki = assignment(args);
//        run(kierunki);
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
        System.out.println(position1.equals(position2));
        System.out.println("Stop");
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

    static Direction[] assignment(String[] args){
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
