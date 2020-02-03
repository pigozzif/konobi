package user_interface;

import core.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.Point;

public class GUI extends Application {
    private final int TILESIZE = 50;
    private Stage stage;
    private GridPane gridPane;
    private Supervisor supervisor;
    private GUIBoardFiller boardFiller;


    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setResizable(false);
        initialize();
    }

    public void initUI(int boardSize) {

        supervisor = new Supervisor(boardSize);
        boardFiller = new GUIBoardFiller(boardSize, TILESIZE);

        gridPane = boardFiller.createEmptyBoard();

        gridPane.setOnMouseClicked(e -> inputHandler(e.getX(), e.getY()));

        Scene scene = new Scene(gridPane, Color.WHITESMOKE);
        stage.setTitle("ChessBoard");
        stage.setScene(scene);
        stage.show();
    }

    private int coordinateConversion(double coordinate) {
        return (int)coordinate/ TILESIZE;
    }

    private void inputHandler(double X, double Y) {

        int columnIndex = coordinateConversion(X);
        int rowIndex = coordinateConversion(Y);

        if(!supervisor.newMove(new Point(columnIndex, rowIndex))) {
            GUIMessageWriter.notifyInvalidMove();
            return;
        }

        boardFiller.addPiece(gridPane, columnIndex, rowIndex, supervisor.getLastPlayer());

        if(Rulebook.queryRule(supervisor, EndGameRule::new)){
            GUIMessageWriter.notifyEndGame(supervisor.getLastPlayer());
            stop();
        }
        if(Rulebook.queryRule(supervisor, PieRule::new) && GUIAsker.askPieRule()){
            supervisor.performPieRule();
        }
        if(Rulebook.queryRule(supervisor, PassRule::new) ){
            supervisor.performPassRule();
            GUIMessageWriter.notifyPass();
        }
    }

    @Override
    public void stop(){
        Platform.exit();
    }




    public int initialize() {
        HBox initPane = new HBox();
        Button startButton = new Button("Start");
        startButton.setOnAction((ActionEvent e) -> {
            int size = GUIAsker.askSize();
            initUI(size);});
        Button endButton = new Button("Quit");
        endButton.setOnAction((ActionEvent e) -> Platform.exit());
        Button rulesButton = new Button("Rules");
        rulesButton.setOnAction((ActionEvent e) -> System.out.println("RULES NOT AVAILABLE YET"));
        initPane.setPadding(new Insets(25, 25, 25, 25));
        initPane.getChildren().add(startButton);
        initPane.getChildren().add(endButton);
        initPane.getChildren().add(rulesButton);
        Scene scene = new Scene(initPane);

        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        return 1;
    }
}


