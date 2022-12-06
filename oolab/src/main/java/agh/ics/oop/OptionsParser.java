package agh.ics.oop;

public class OptionsParser {
    public MoveDirection[] parse(String[] args){
        MoveDirection[] direction = new MoveDirection[args.length];
        int k=0;
        for (String a: args){
            switch (a) {
                case "f", "forward" -> {
                    direction[k] = MoveDirection.FORWARD;
                    break;
                }
                case "b", "backward" -> {
                    direction[k] = MoveDirection.BACKWARD;
                    break;
                }
                case "r", "right" -> {
                    direction[k] = MoveDirection.RIGHT;
                    break;
                }
                case "l", "left" -> {
                    direction[k] = MoveDirection.LEFT;
                    break;
                }
                default -> throw new IllegalArgumentException(a + " is not legal move specification");
            }
            k++;
        }
        return direction;
   //     return Arrays.copyOfRange(direction, 0, k);
    }
}
