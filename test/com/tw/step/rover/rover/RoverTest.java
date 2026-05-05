package com.tw.step.rover.rover;

import com.tw.step.rover.boundary.Boundary;
import com.tw.step.rover.boundary.InfinitePlateau;
import com.tw.step.rover.boundary.Plateau;
import com.tw.step.rover.position.Coordinate;
import com.tw.step.rover.position.Direction;
import com.tw.step.rover.position.Navigator;
import com.tw.step.rover.roversystem.RoverSystemScanner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoverTest {
    @Test
    void shouldTurnAndMove() {
        Rover rover = new Rover(new Coordinate(0, 0), Direction.N);
        Navigator navigator = Navigator.create();
        InfinitePlateau boundary = new InfinitePlateau();

        rover.turnRight(navigator, boundary);
        rover.move(navigator, boundary);

        assertEquals("1 0 E ALIVE", rover.toString());
    }

    @Test
    void shouldReturnRoverStatusAsLost() {
        String text = """
1 0
1 0 N
FF
                """;
        Rover rover = new Rover(new Coordinate(1, 0), Direction.N);
        RoverSystemScanner scanner = RoverSystemScanner.from(text);
        Navigator navigator = Navigator.create();
        Boundary boundary = new Plateau(new Coordinate(0, 0), scanner.scanCoordinate());
        rover.move(navigator, boundary);
        assertEquals("1 0 N LOST", rover.toString());
}

    @Test
    void shouldReturnRoverStatusAsALive() {
        String text = """
1 2
1 0 N
FF
                """;
        Rover rover = new Rover(new Coordinate(1, 0), Direction.N);
        RoverSystemScanner scanner = RoverSystemScanner.from(text);
        Navigator navigator = Navigator.create();
        Boundary boundary = new Plateau(new Coordinate(0, 0), scanner.scanCoordinate());
        rover.move(navigator, boundary);
        assertEquals("1 1 N ALIVE", rover.toString());
    }

}