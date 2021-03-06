package core.Entities;

import java.awt.*;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.stream.Stream;


public class Neighbourhood {

    private Neighbourhood() {}

    public static Stream<Cell> getMooreNeighbours(Board board, Point p) {
        return Arrays.stream(board.slice(p.y - 1, p.y + 2, p.x - 1, p.x + 2));
    }

    public static boolean isStrongNeighbour(Point target, Point query) {
        return manhattanDistance(target.x, query.x, target.y, query.y) == 1.0;
    }

    public static boolean isWeakNeighbour(Point target, Point query) {
        return manhattanDistance(target.x, query.x, target.y, query.y) == 2.0;
    }

    public static Stream<Cell> getNeighboursByPosition(Board board, Point point, BiPredicate<Point, Point> function) {
        return getMooreNeighbours(board, point).filter(cell -> function.test(point, cell.getCoordinates()));
    }

    public static Stream<Cell> getNeighboursByColour(Board board, Point point, Colour colour) {
        return getMooreNeighbours(board, point).filter(cell -> cell.hasThisColour(colour));
    }

    public static Stream<Cell> getNeighboursByPositionAndColour(Board board, Point point, Colour colour,
                                                                BiPredicate<Point, Point> function) {
        return getMooreNeighbours(board, point).filter(cell -> cell.hasThisColour(colour)
                                                                 && function.test(point, cell.getCoordinates()));
    }

    private static double manhattanDistance(int x1, int x2, int y1, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

}
