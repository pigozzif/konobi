package core.Rules;

import core.Entities.Board;
import core.Entities.Colour;
import core.Entities.StatusSupervisor;
import org.junit.Test;
import java.awt.Point;

import static org.junit.Assert.*;


public class validPositionRuleTest {

    private final StatusSupervisor supervisor = new StatusSupervisor(11);
    private final ValidPositionRule validPositionRule = new ValidPositionRule();

    private void setTestBoard(Board board) {
        board.setCellColour(new Point(0, 0), Colour.BLACK);
        board.setCellColour(new Point(2, 1), Colour.BLACK);
        board.setCellColour(new Point(0, 1), Colour.WHITE);
        board.setCellColour(new Point(2, 2), Colour.WHITE);
    }

    @Test
    public void testValidPositionRule() {
        setTestBoard(supervisor.getBoard());
        supervisor.setCurrentPoint(new Point(1, 1));
        assertTrue(validPositionRule.isValid(supervisor));
    }

    @Test
    public void testValidPositionRuleAgain() {
        setTestBoard(supervisor.getBoard());
        supervisor.setCurrentPoint(new Point(1, 0));
        assertFalse(validPositionRule.isValid(supervisor));
    }

}
