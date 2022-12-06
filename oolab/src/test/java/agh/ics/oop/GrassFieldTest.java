package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {
    String[] arr = {"f", "l", "r","f","f","r","r","f","l","f","f","f","f","f","f","f","f","f","f","f"}; //argumenty dobrane tak, aby doszlo do zderzenia lub unikniecia kolizji
    MoveDirection[] directions = new OptionsParser().parse(arr);
    IWorldMap mapa = new GrassField(10);
    Vector2d[] positions = { new Vector2d(1,2), new Vector2d(2, 3)};//argumenty dobrane tak, aby doszlo do zderzenia lub unikniecia kolizji
    IEngine engine = new SimulationEngine(directions, mapa, positions);

    @Test
    void placeGrass() {
        engine.run();
        assertFalse(((GrassField) mapa).placeGrass()); // nie da sie trawy bo juz sa wszystkie umiejscowiaone
    }
    @Test
    void objectAt() {
        engine.run();
        assertTrue(mapa.objectAt(new Vector2d(6,3)) instanceof Animal);
        assertTrue(mapa.objectAt(new Vector2d(2,10)) instanceof Animal);
        assertFalse(mapa.objectAt(new Vector2d(0,10)) instanceof Animal);
    }

    @Test
    void place(){
        assertTrue(mapa.place(new Animal(mapa, new Vector2d(3,3))));
        assertFalse(mapa.place(new Animal(mapa, new Vector2d(3,3))));
    }
}