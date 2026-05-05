package com.tw.step.rover.roversystem;

import com.tw.step.rover.boundary.Boundary;
import com.tw.step.rover.commands.CommandCreator;
import com.tw.step.rover.commands.RoverCommand;
import com.tw.step.rover.commands.RoverCommands;
import com.tw.step.rover.position.Coordinate;
import com.tw.step.rover.position.Direction;
import com.tw.step.rover.position.Navigator;
import com.tw.step.rover.rover.Rover;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoverSystemParser {
    private final RoverSystemScanner scanner;
    private final Navigator navigator;
    private final Boundary boundary;
    private final CommandCreator commandCreator;

    public RoverSystemParser(RoverSystemScanner scanner, Navigator navigator, Boundary boundary, CommandCreator commandCreator) {
        this.scanner = scanner;
        this.navigator = navigator;
        this.boundary = boundary;
        this.commandCreator = commandCreator;
    }

    private Rover parseRover(String id) {
        Coordinate coordinate = scanner.scanCoordinate();
        Direction heading = scanner.scanDirection();
        return new Rover(id, coordinate, heading);
    }

    public List<RoverSystem> parse() {

        Map<String, RoverSystem> rovers = new HashMap<>();

        while(!scanner.peek().endsWith(":")) {
            String id = scanner.consume() + ':';
            RoverSystem roverSystem = new RoverSystem();
            Rover rover = parseRover(id);
            roverSystem.addRover(rover);
            rovers.put(id, roverSystem);
        }

        while(scanner.peek() != null) {
            String id = scanner.consume();
            RoverSystem roverSystem = rovers.get(id);

            RoverCommands roverCommands = parseRoverCommands();
            roverSystem.addCommands(roverCommands);
        }

        return new ArrayList<>(rovers.values());
    }

    private RoverCommands parseRoverCommands() {
        RoverCommands roverCommands = new RoverCommands();
        String instructions = scanner.consume();
        for (int i = 0; i < instructions.length(); i++) {
            RoverCommand roverCommand = commandCreator.create(instructions.charAt(i), navigator, boundary);
            roverCommands.add(roverCommand);
        }

        return roverCommands;
    }
}
