package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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

    @Test
    void addObserver(){
        GrassField mapa = new GrassField(10);
        Animal zwierz = new Animal(mapa, new Vector2d(2,3));
        zwierz.addObserver(mapa);
        ArrayList<IPositionChangeObserver> tablica = new ArrayList<IPositionChangeObserver>();
        tablica.add((IPositionChangeObserver) mapa);
        assertEquals(zwierz.observers, tablica);
        IPositionChangeObserver mapa2 = new RectangularMap(5,5);
        tablica.add(mapa2);
        assertNotEquals(zwierz.observers, tablica);
        zwierz.addObserver(mapa2);
        assertEquals(zwierz.observers, tablica);
    }

    @Test
    void removeObserver(){
        GrassField mapa = new GrassField(10);
        Animal zwierz = new Animal(mapa, new Vector2d(2,3));
        zwierz.addObserver(mapa);
        ArrayList<IPositionChangeObserver> tablica = new ArrayList<IPositionChangeObserver>();
        IPositionChangeObserver mapa2 = new RectangularMap(5,5);
        tablica.add(mapa);
        zwierz.addObserver(mapa2);
        tablica.add(mapa2);
        assertEquals(zwierz.observers, tablica);
        zwierz.removeObserver(mapa2);
        assertNotEquals(zwierz.observers, tablica);
        zwierz.removeObserver(mapa);
        assertNotEquals(zwierz.observers, tablica);
    }
    @Test
    void positionChanged(){
        RectangularMap mapa = new RectangularMap(8,9);
        Animal zwierz = new Animal(mapa, new Vector2d(2,3));
        mapa.place(zwierz);
        ArrayList<IPositionChangeObserver> tablica = new ArrayList<IPositionChangeObserver>();
        RectangularMap mapa2 = new RectangularMap(5,5);
        tablica.add(mapa);
        mapa2.place(zwierz);
        tablica.add(mapa2);
        assertEquals(zwierz.observers, tablica);
        assertTrue(mapa.isOccupied(new Vector2d(2,3)));
        assertTrue(mapa2.isOccupied(new Vector2d(2,3)));
        zwierz.positionChanged(zwierz.getPosition(), new Vector2d(3,3));
        assertTrue(mapa.isOccupied(new Vector2d(3,3)));
        assertTrue(mapa2.isOccupied(new Vector2d(3,3)));
    }
}