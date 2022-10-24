package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class Vector2dTest {

    @Test
    void testToString() {
        Vector2d v1 = new Vector2d(101, 100000);
        Vector2d v2 = new Vector2d(-3, 2);
        Vector2d v3 = new Vector2d(-10, -300);
        assertEquals("(101,100000)",v1.toString());
        assertEquals("(-3,2)",v2.toString());
        assertEquals("(-10,-300)",v3.toString());
    }

    @Test
    void precedesTest() {
        Vector2d v1 = new Vector2d(400, 500);
        Vector2d v2 = new Vector2d(-800, 621);
        Vector2d v3 = new Vector2d(-400, 700);
        assertFalse(v1.precedes(v2));
        assertTrue(v2.precedes(v3));
        assertFalse(v3.precedes(v2));
    }

    @Test
    void followsTest() {
        Vector2d v1 = new Vector2d(400, 500);
        Vector2d v2 = new Vector2d(-800, 621);
        Vector2d v3 = new Vector2d(-400, 700);
        assertFalse(v1.follows(v2));
        assertFalse(v2.follows(v3));
        assertTrue(v3.follows(v2));
    }

    @Test
    void addTest() {
        Vector2d v1 = new Vector2d(400, 500);
        Vector2d v2 = new Vector2d(-800, 621);
        Vector2d v3 = new Vector2d(-400, 700);
        assertEquals(new Vector2d(-400, 1121), v1.add(v2));
        assertEquals(new Vector2d(-1200, 1321), v2.add(v3));
        assertEquals(new Vector2d(-1200, 1321), v3.add(v2));
    }

    @Test
    void subtractTest() {
        Vector2d v1 = new Vector2d(400, 500);
        Vector2d v2 = new Vector2d(-800, 621);
        Vector2d v3 = new Vector2d(-400, 700);
        assertEquals(new Vector2d(1200, -121), v1.subtract(v2));
        assertEquals(new Vector2d(-400, -79), v2.subtract(v3));
        assertEquals(new Vector2d(400, 79), v3.subtract(v2));
    }

    @Test
    void upperRightTest() {
        Vector2d v1 = new Vector2d(400, 500);
        Vector2d v2 = new Vector2d(-800, 621);
        Vector2d v3 = new Vector2d(-400, 700);
        assertEquals(new Vector2d(400, 621), v1.upperRight(v2));
        assertEquals(new Vector2d(-400, 700), v2.upperRight(v3));
        assertEquals(new Vector2d(-400, 700), v3.upperRight(v2));
    }

    @Test
    void lowerLeftTest() {
        Vector2d v1 = new Vector2d(400, 500);
        Vector2d v2 = new Vector2d(-800, 621);
        Vector2d v3 = new Vector2d(-400, 700);
        assertEquals(new Vector2d(-800, 500), v1.lowerLeft(v2));
        assertEquals(new Vector2d(-800, 621), v2.lowerLeft(v3));
        assertEquals(new Vector2d(-800, 621), v3.lowerLeft(v2));
    }

    @Test
    void oppositeTest() {
        Vector2d v1 = new Vector2d(400, 500);
        Vector2d v2 = new Vector2d(-800, 621);
        Vector2d v3 = new Vector2d(-400, 700);
        Vector2d zero = new Vector2d(0, 0);
        assertEquals(new Vector2d(-400, -500), v1.opposite());
        assertEquals(new Vector2d(800, -621), v2.opposite());
        assertEquals(new Vector2d(400, -700), v3.opposite());
        assertEquals(v3, v3.opposite().opposite());
        assertEquals(zero, zero.opposite());
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testEquals() {
        Vector2d v1 = new Vector2d(400, 500);
        Vector2d v2 = new Vector2d(-800, 621);
        Vector2d v3 = new Vector2d(-400, 700);
        int[] lista = {400, 500};
        assertTrue(v1.equals(v1));
        assertFalse(v1.equals("alabama"));
        assertFalse(v1.equals(lista));
        assertFalse(v1.equals(v2));
        assertTrue(v1.equals(v3.add(new Vector2d(800, -200))));

    }
}