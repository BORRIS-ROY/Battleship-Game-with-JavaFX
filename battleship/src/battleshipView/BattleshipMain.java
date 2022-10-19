package battleship;

import java.util.Random;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import battleship.Board.Cell;

public class BattleshipMain extends Application{

    private boolean running = false;
    private Board playerBoard;
    private int shipsToPlace = 5;
    private int counter = 5;
    private boolean playerPlay = false;
    private Random random = new Random();
    int shots = 0;

    private Parent createContent() {
        //Creat
        BorderPane root = new BorderPane();
        root.setPrefSize(500, 500);
        Button button = new Button("RESET");//Button to reset game once it has ended
        root.setRight(button);
        //Creating the board and placing the ships on the board
        playerBoard = new Board(event -> {
            if (running)
                return;
            while(counter > 0){
                assert shipsToPlace > 0: "Yep...greater than zero";
                if (shipsToPlace == 1){
                    shipsToPlace = 2;
                }
                int x = random.nextInt(10);
                int y = random.nextInt(10);
                    //randomised placement of the ships on the board
                if (playerBoard.placeShip(new Ship(shipsToPlace, Math.random() < 0.5), x, y)) {
                    if(--counter == 1){
                        return;
                    }
                    --shipsToPlace;
                }
                
            }
            assert counter == 0: "Nothing more to place";
            //checks if the number of ships is more than 5
            if(playerBoard.ships > 0){
                Cell cell = (Cell) event.getSource();
                if (cell.isShot)//if cell was shot, then do nothing
                    return;
                    playerPlay = cell.shoot();//shoots a cell
                    shots++;//count the number of shots taken
                }
                if (playerBoard.ships == 0){
                    System.out.println("You attempted "+shots+" shots");//prints a message to user for number of shots taken
                    //System.exit(0);
                }   
        });
        
        VBox vbox = new VBox(50, playerBoard);
        vbox.setAlignment(Pos.CENTER);

        root.setCenter(vbox);

        return root;
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Battleship");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
       //assert false: "Something has gone wrong";
       launch(args);
    }
}
