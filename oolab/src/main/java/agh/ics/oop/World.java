package agh.ics.oop;


public class World {
    public static void main(String[] args){
        System.out.println("Start");
        int k = emp(args);
        Direction[] kierunki = new Direction[args.length-k];
        assignment(kierunki, args);
        run(kierunki);
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

    static void assignment(Direction[] x, String[] args){
        int p=0;
        for (int i=0; i<args.length; i+=1) {
            if (!args[i].equals("l") && !args[i].equals("r") && !args[i].equals("f") && !args[i].equals("b")){
                p+=1;
            }
            else {
                x[i-p] = Direction.valueOf(assign(args[i]));
            }
        }
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
                case BACKWARD -> "Do tyłu";
                case LEFT -> "Skręca w lewo";
                case RIGHT -> "Skręca w prawo";
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
