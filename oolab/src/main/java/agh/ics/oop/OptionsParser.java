package agh.ics.oop;

import java.util.Arrays;

public class OptionsParser {
    public MoveDirection[] parse(String[] args){
        MoveDirection[] direction = new MoveDirection[args.length];
        int k=0;
        for (String a: args){
            switch(a){
                case "f":
                case "forward": {direction[k]=MoveDirection.FORWARD; k++; break;}
                case "b":
                case "backward":{direction[k]=MoveDirection.BACKWARD;k++;break;}
                case "r":
                case "right":{direction[k]=MoveDirection.RIGHT;k++;break;}
                case "l":
                case "left":{direction[k]=MoveDirection.LEFT;k++;break;}
            }
        }
        return Arrays.copyOfRange(direction, 0, k);
    }
}
