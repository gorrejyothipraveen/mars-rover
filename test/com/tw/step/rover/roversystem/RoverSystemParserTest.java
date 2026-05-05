//package com.tw.step.rover.roversystem;
//
//import com.tw.step.rover.boundary.Boundary;
//import com.tw.step.rover.boundary.InfinitePlateau;
//import com.tw.step.rover.boundary.Plateau;
//import com.tw.step.rover.commands.CommandCreator;
//import com.tw.step.rover.position.Coordinate;
//import com.tw.step.rover.position.Navigator;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class RoverSystemParserTest {
//    @Test
//    void shouldParseAndExecuteRoverSystem() {
//        RoverSystemScanner scanner = RoverSystemScanner.from("5 5\n1 2 N\nRFF");
//        Navigator navigator = Navigator.create();
//        Boundary boundary = new Plateau(new Coordinate(0, 0), scanner.scanCoordinate());
//        CommandCreator commandCreator = new CommandCreator();
//        RoverSystemParser parser = new RoverSystemParser(scanner, navigator, boundary, commandCreator);
//
//
//        RoverSystem roverSystem = parser.parse();
//        roverSystem.execute();
//
//        assertEquals("3 2 E ALIVE", roverSystem.toString());
//    }
//
//}
//
