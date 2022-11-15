package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimulationEngineTest {

    @Test
    void run() {
        String[] args ={"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] kierunki = new OptionsParser().parse(args);
        IWorldMap mapa = new RectangularMap(10,5);
        Vector2d[] pozycje = {new Vector2d(2,2), new Vector2d(3,4), new Vector2d(3,4)};
        SimulationEngine engine = new SimulationEngine(kierunki, mapa, pozycje);
        ArrayList<Animal> animals = ((RectangularMap) mapa).getAnimals();

        assertEquals(animals.get(0).getPosition(), new Vector2d(2,2));
        assertEquals(animals.get(1).getPosition(), new Vector2d(3,4));
        assertEquals(animals.size(), 2);

        engine.run();

        assertEquals(animals.get(0).getPosition(), new Vector2d(2,0));
        assertEquals(animals.get(1).getPosition(), new Vector2d(3,5));
        assertEquals(animals.get(0).toString(), "S");
        assertEquals(animals.get(1).toString(), "N");
    }
}