package user_interface;

import core.PieRule;
import core.Rulebook;
import javafx.event.EventHandler;

public class GUIPieRuleHandler implements EventHandler<PieRuleEvent> {
    private GUI gui;

    public GUIPieRuleHandler(GUI g) { gui = g; }

    @Override
    public void handle(PieRuleEvent event) {
        if (Rulebook.queryRule(gui.getSupervisor(), PieRule::new) && GUIAsker.askPieRule()){
            gui.getSupervisor().performPieRule();
            //TODO: Ha senso così?
            gui.getBoardFiller().switchLabelsColors(gui.getLabelBoard());
        }
    }
}
