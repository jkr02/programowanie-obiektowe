package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

class OptionsParserTest {

    @Test
    void parse() {
        OptionsParser modyfikator = new OptionsParser();
        String[] s1 = {"r", "l", "forward", "something", "left", "backward", "cosinus", "f", "right", "b", "niewiem"};
        MoveDirection[] kierunki1 = modyfikator.parse(s1);
        MoveDirection[] spodziewane1 = {MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.BACKWARD};
        assertTrue(Arrays.equals(kierunki1, spodziewane1));

        String[] s2 = {};
        MoveDirection[] kierunki2 = modyfikator.parse(s2);
        MoveDirection[] spodziewane2 = {};
        assertTrue(Arrays.equals(kierunki2, spodziewane2));

        String[] s3 = {"somebody", "once", "told", "me"};
        MoveDirection[] kierunki3 = modyfikator.parse(s3);
        MoveDirection[] spodziewane3 = {};
        assertTrue(Arrays.equals(kierunki3, spodziewane3));

    }
}