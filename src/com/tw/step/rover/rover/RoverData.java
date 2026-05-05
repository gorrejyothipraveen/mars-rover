package com.tw.step.rover.rover;

import com.tw.step.rover.position.Coordinate;
import com.tw.step.rover.position.Direction;

public record RoverData(Coordinate coord, Direction direction, String[] instructions) {
}
