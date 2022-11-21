package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {
    @Test
    void testToString() {
        assertEquals("E",MapDirection.EAST.toString());
        assertEquals("W",MapDirection.WEST.toString());
        assertEquals("N",MapDirection.NORTH.toString());
        assertEquals("S",MapDirection.SOUTH.toString());
    }

    @Test
    void nextTest() {
        assertEquals(MapDirection.SOUTH,MapDirection.EAST.next());
        assertEquals(MapDirection.WEST,MapDirection.SOUTH.next());
        assertEquals(MapDirection.NORTH,MapDirection.WEST.next());
        assertEquals(MapDirection.EAST,MapDirection.NORTH.next());
    }

    @Test
    void previousTest() {
        assertEquals(MapDirection.EAST,MapDirection.SOUTH.previous());
        assertEquals(MapDirection.SOUTH,MapDirection.WEST.previous());
        assertEquals(MapDirection.WEST,MapDirection.NORTH.previous());
        assertEquals(MapDirection.NORTH,MapDirection.EAST.previous());
    }

    @Test
    void toUnitVectorTest() {
        assertEquals(new Vector2d(0,-1), MapDirection.SOUTH.toUnitVector());
        assertEquals(new Vector2d(0,1), MapDirection.NORTH.toUnitVector());
        assertEquals(new Vector2d(-1,0), MapDirection.WEST.toUnitVector());
        assertEquals(new Vector2d(1,0), MapDirection.EAST.toUnitVector());
    }
}