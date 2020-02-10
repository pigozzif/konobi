package UI.GUI.Handlers;

import UI.GUI.Events.EndGameEvent;
import UI.GUI.GUI;
import UI.GUI.GUIMessageWriter;
import Core.Rules.EndGameRule;
import Core.Entities.Rulebook;
import javafx.event.EventHandler;

public class GUIEndGameHandler implements EventHandler<EndGameEvent> {
    private final GUI gui;

    public GUIEndGameHandler(GUI g) { gui = g; }

    @Override
    public void handle(EndGameEvent event) {
        if(Rulebook.queryRule(gui.getSupervisor(), EndGameRule::new)){
            GUIMessageWriter.notifyEndGame(gui.getSupervisor().getLastPlayer());
            gui.stop();
        }
    }
}
