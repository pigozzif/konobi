package core.Rules;

import core.Entities.Board;
import core.Entities.Color;
import org.junit.Test;
import java.awt.Point;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class weakRuleTest {
    private final Board board = new Board(5);

    @Test
    public void weakRuleTestFirstConf() {
        Point point = new Point(1, 2);

        board.setCell(new Point(0, 0), Color.white);
        board.setCell(new Point(2, 1), Color.white);
        board.setCell(new Point(3, 3), Color.white);

        board.setCell(new Point(2, 0), Color.black);
        board.setCell(new Point(3, 0), Color.black);
        board.setCell(new Point(3, 1), Color.black);

        assertTrue(WeakRule.isValid(point, board, Color.white));
    }

    @Test
    public void weakRuleTestSecondConf() {
        Point point = new Point(1, 1);

        board.setCell(new Point(3, 0), Color.white);
        board.setCell(new Point(0, 3), Color.white);
        board.setCell(new Point(2, 2), Color.white);
        board.setCell(new Point(2, 3), Color.white);

        board.setCell(new Point(2, 0), Color.black);
        board.setCell(new Point(2, 1), Color.black);
        board.setCell(new Point(4, 2), Color.black);
        board.setCell(new Point(0, 2), Color.black);

        assertFalse(WeakRule.isValid(point, board, Color.black));
    }

    @Test
    public void weakRuleTestThirdConf() {
        Point point = new Point(1, 0);

        board.setCell(new Point(0, 1), Color.white);
        board.setCell(new Point(2, 2), Color.white);

        board.setCell(new Point(0, 0), Color.black);
        board.setCell(new Point(2, 1), Color.black);

        assertFalse(WeakRule.isValid(point, board, Color.black));
    }

}