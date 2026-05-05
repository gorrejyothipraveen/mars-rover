package com.tw.step.rover;

import com.tw.step.rover.boundary.Boundary;
import com.tw.step.rover.boundary.InfinitePlateau;
import com.tw.step.rover.boundary.Plateau;
import com.tw.step.rover.commands.CommandCreator;
import com.tw.step.rover.position.Coordinate;
import com.tw.step.rover.position.Navigator;
import com.tw.step.rover.roversystem.RoverSystem;
import com.tw.step.rover.roversystem.RoverSystemParser;
import com.tw.step.rover.roversystem.RoverSystemScanner;

import java.util.List;

public class App {
    static void main() {
        String text = """
2 2
R1 0 0 N
R2 0 0 E
R1: FFF
R2: FF
                """;

        RoverSystemScanner scanner = RoverSystemScanner.from(text);
        Navigator navigator = Navigator.create();
        Boundary boundary = new Plateau(new Coordinate(0, 0), scanner.scanCoordinate());
        CommandCreator commandCreator = new CommandCreator();
        RoverSystemParser roverSystemParser = new RoverSystemParser(scanner, navigator, boundary, commandCreator);
        List<RoverSystem> systems = roverSystemParser.parse();
        for(RoverSystem system : systems) {
            system.execute();
            System.out.println(system);
        }
    }
}
