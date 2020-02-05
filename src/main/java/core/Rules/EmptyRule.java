package core.Rules;

import core.Entities.Board;
import core.Entities.Supervisor;

import java.awt.Point;

public class EmptyRule implements Rule {
    @Override
    public boolean isValid(Supervisor supervisor) {
        return isValid(supervisor.getCurrentPoint(), supervisor.getBoard());
    }

    public static boolean isValid(Point point, Board board) {
        return board.isOnBoard(point)
                && board.getCell(point).getColor() == null;
    }
}
