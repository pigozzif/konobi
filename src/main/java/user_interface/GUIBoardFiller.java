package user_interface;

import core.Supervisor;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.Map;

public class GUIBoardFiller {

    int boardSize;
    int tileSize;

    GUIBoardFiller(int bS, int tS) {
        boardSize = bS;
        tileSize = tS;
    }

    public GridPane createEmptyBoard() {

        GridPane gridPane = new GridPane();

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                Rectangle r = new Rectangle(col * tileSize,row * tileSize , tileSize, tileSize);
                r.setFill( (col + row) % 2 == 0 ? Color.PALEVIOLETRED : Color.DARKSEAGREEN);
                gridPane.addRow(row,r);
            }
        }
        /*for (int x = 0 ; x < size ; x++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(100.0 / (size));
            cc.setFillWidth(true);
            gridPane.getColumnConstraints().add(cc);
        }

        // row constraints:
        for (int y = 0 ; y < size; y++) {
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(100.0 / (size));
            rc.setFillHeight(true);
            gridPane.getRowConstraints().add(rc);
        }*/

        return gridPane;
    }

    // TODO: ha senso metterla nella classe Cell o in una nuova classe?
    private static final Map<core.Color, Paint> colorPaintMap = new HashMap<>() {{
        put(core.Color.black, Color.BLACK);
        put(core.Color.white, Color.WHITE);
    }};

    public void addTile(GridPane gridPane, int X, int Y, Supervisor supervisor) {
        Rectangle rect = new Rectangle(X * tileSize, Y * tileSize, tileSize -12, tileSize -12);
        rect.setFill(colorPaintMap.get(supervisor.getLastPlayer().getColor()));
        gridPane.add(rect, X, Y);

    }
}
