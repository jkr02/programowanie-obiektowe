package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {
    String[] dane = {"f", "l", "r","f","f"}; //argumenty dobrane tak, aby doszlo do zderzenia lub unikniecia kolizji
    MoveDirection[] kierunki = new OptionsParser().parse(dane);
    IWorldMap mapa = new RectangularMap(4, 4);
    Vector2d[] pozycje = { new Vector2d(2,2), new Vector2d(1, 2)};//argumenty dobrane tak, aby doszlo do zderzenia lub unikniecia kolizji
    IEngine engine = new SimulationEngine(kierunki, mapa, pozycje);
    @Test
    void canMoveTo() {
        engine.run();
        assertTrue(mapa.canMoveTo(new Vector2d(2, 2)));
        assertFalse(mapa.canMoveTo(new Vector2d(3, 3)));
        assertTrue(mapa.canMoveTo(new Vector2d(1, 3)));
        assertFalse(mapa.canMoveTo(new Vector2d(-1,-1)));
    }

    @Test
    void place() {
        engine.run();
        assertTrue(mapa.place(new Animal(mapa, new Vector2d(2,2))));
        assertFalse(mapa.place(new Animal(mapa, new Vector2d(2,2))));
        assertFalse(mapa.place(new Animal(mapa, new Vector2d(-1,-1))));
    }

    @Test
    void isOccupied() {
        engine.run();
        assertFalse(mapa.isOccupied(new Vector2d(2,2)));
        assertTrue(mapa.isOccupied(new Vector2d(3,3)));
        assertFalse(mapa.isOccupied(new Vector2d(-1,-1)));
    }

    @Test
    void objectAt() {
        engine.run();
        RectangularMap map = (RectangularMap) mapa;
        ArrayList<Animal> animals = map.getAnimals();
        assertEquals(animals.get(0), mapa.objectAt(new Vector2d(3,3)));
        assertEquals(animals.get(1), mapa.objectAt(new Vector2d(0,2)));
        assertNotEquals(animals.get(0), mapa.objectAt(new Vector2d(0,2)));
    }
    @Test
    void get_min_position(){
        engine.run();
        assertEquals(mapa.get_min_position(), new Vector2d(0,0));
    }
    @Test
    void get_max_position(){
        engine.run();
        assertEquals(mapa.get_max_position(), new Vector2d(4,4));
    }

}