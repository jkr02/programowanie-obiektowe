package agh.ics.oop;

public enum MapDirection {
    NORTH, SOUTH, WEST, EAST;

    @Override
    public String toString() {
        return(switch (this){
            case EAST -> "E";
            case WEST -> "W";
            case NORTH -> "N";
            case SOUTH -> "S";
        });
    }
    public MapDirection next(){
        return(switch(this){
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
        });
    }
    public MapDirection previous(){
        return(switch (this){
            case NORTH -> WEST;
            case EAST -> NORTH;
            case SOUTH -> EAST;
            case WEST -> SOUTH;
        });
    }
    public Vector2d toUnitVector(){
        return(new Vector2d(switch (this){
            case NORTH -> 0;
            case WEST -> -1;
            case SOUTH -> 0;
            case EAST -> 1;
        }, switch (this){
            case NORTH -> 1;
            case WEST -> 0;
            case SOUTH -> -1;
            case EAST -> 0;
        }));
    }


}
