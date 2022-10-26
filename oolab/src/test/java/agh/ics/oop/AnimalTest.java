package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void testToString() {
        Animal rak=new Animal();
        assertEquals("(2,2) Polnoc", rak.toString());
    }
    @Test
    void isAt(){
        Animal rak = new Animal();
        assertTrue(rak.isAt(new Vector2d(2,2)));
        assertFalse(rak.isAt(new Vector2d(-1,2)));
    }
}