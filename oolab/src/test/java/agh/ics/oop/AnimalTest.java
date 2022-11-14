package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    IWorldMap map = new RectangularMap(4,4);
    @Test
    void testToString() {
        Animal rak=new Animal(map, new Vector2d(2, 2));
        assertEquals("N", rak.toString());
    }
    @Test
    void isAt(){
        Animal rak = new Animal(map, new Vector2d(2,2));
        assertTrue(rak.isAt(new Vector2d(2,2)));
        assertFalse(rak.isAt(new Vector2d(-1,2)));
        rak.move(MoveDirection.FORWARD);
        rak.move(MoveDirection.RIGHT);
        assertTrue(rak.isAt(new Vector2d(2,3)));
    }
    @Test
    void move(){
        Animal chewie = new Animal(map, new Vector2d(2,2));
        assertEquals("N", chewie.toString());
        chewie.move(MoveDirection.FORWARD);
        chewie.move(MoveDirection.FORWARD);
        chewie.move(MoveDirection.FORWARD);
        assertEquals("N", chewie.toString()); // czy nie wychodzi za mape na polnoc
        chewie.move(MoveDirection.RIGHT);
        assertEquals("E", chewie.toString()); // czy umie sie obrocic w prawo
        chewie.move(MoveDirection.FORWARD);
        chewie.move(MoveDirection.FORWARD);
        chewie.move(MoveDirection.FORWARD);
        assertEquals("E", chewie.toString()); // czy nie wychodzi za mape na wschod
        for (int i=0; i<5; i++){
            chewie.move(MoveDirection.BACKWARD);
        }
        assertEquals("E", chewie.toString()); // czy nie wychodzi za mape na zachod
        chewie.move(MoveDirection.LEFT);
        assertEquals("N", chewie.toString()); // czy dobrze sie obraca w lewo
        chewie.move(MoveDirection.LEFT);
        chewie.move(MoveDirection.LEFT);
        for (int i=0; i<5; i++){
            chewie.move(MoveDirection.FORWARD);
        }
        assertEquals("S", chewie.toString()); // czy nie wychodzi za mape na polodnie
    }
}