package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parse() {
        OptionsParser modyfikator = new OptionsParser();
        String[] s1 = {"r", "l", "forward", "something", "left", "backward", "cosinus", "f", "right", "b", "niewiem"};
        assertThrows(IllegalArgumentException.class, ()->modyfikator.parse(s1));

        String[]s2 = {"r", "l"};
        MoveDirection[] pa= {MoveDirection.RIGHT, MoveDirection.LEFT};
        assertArrayEquals(modyfikator.parse(s2), pa);


    }
}