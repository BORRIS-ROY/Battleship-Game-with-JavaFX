package battleship;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board extends Parent{
    private VBox rows = new VBox();
    public int ships = 5;//initialise the number of ships
    
    public Board(EventHandler<? super MouseEvent> handler){
        for (int y = 0; y <10; y++){
            HBox row = new HBox();
            //Label label= new Label(Character.toString((char) ('A' + y)));
            //row.getChildren().add(label);
            for (int x = 0; x < 10; x++){
                Cell c = new Cell(x,y,this);//cell with x and y coords, this refferring to the board
                c.setOnMouseClicked(handler);
                row.getChildren().add(c);
                //rows.getChildren().add(label2);
            }
            Label label2 = new Label(Integer.toString(y + 1));
            rows.getChildren().add(row);//adds rows to the list of rows
            row.getChildren().add(label2);
        }
        getChildren().add(rows);
    }
   //Method to place the ships on the board 
    public boolean placeShip(Ship ship, int x, int y){
        if (canPlaceShip(ship, x, y)){
            int length = ship.type;//variable to check if preffered ship can be placed
            boolean vertical = ship.vertical;
            //To place ships vertically or horizontally
            if (vertical){
                for (int i = y; i < y + length; i++){
                    Cell cell = getCell(x, i);
                    cell.ship = ship;
                    cell.setFill(Color.WHITE);    
                }
            }
            //Places ships horizontally
            else{
                for (int i = x; i < x + length; i++){
                    Cell cell = getCell(i, y);
                    cell.ship = ship;
                    cell.setFill(Color.WHITE);    
                }
            }
            return true;
        }
        return false;
    }
    //Get cell within the hBox
    public Cell getCell(int x, int y){
        return (Cell)((HBox)rows.getChildren().get(y)).getChildren().get(x);
    }
    //Creation of coordinates around the cell
    public Cell[] getNeighbors(int x, int y){
        Point2D[] points = new Point2D[]{
            new Point2D(x -1, y),
            new Point2D(x + 1, y),
            new Point2D(x, y - 1),
            new Point2D(x, y + 1),
        };
        
        List<Cell> neighbors = new ArrayList<Cell>();//List of neighbors to store the cells
        //Check within list of points if point can be for added to list of neighbors
        for (Point2D p : points){
            if (isValidPoint(p)){
                neighbors.add(getCell((int)p.getX(), (int)p.getY()));
            }
        }
        return neighbors.toArray(new Cell[0]);//returns
    }
    //method to check if ship can be placed
    public boolean canPlaceShip(Ship ship, int x, int y){
        int length = ship.type;
        
        if (ship.vertical){
            for (int i = y; i < y + length; i++){
                if(!isValidPoint(x, i))
                    return false;
                
                Cell cell = getCell(x, i);
                if (cell.ship != null)
                    return false;
                
                for (Cell neighbor : getNeighbors(x, i)){
                    if(!isValidPoint(x, i))
                        return false;
                    
                    if(neighbor.ship != null)
                        return false;
                }
            }
        }
        else{
            for (int i = x; i < x + length; i++){
                if (!isValidPoint(i, y))
                    return false;
                
                Cell cell = getCell(i, y);
                if (cell.ship != null)
                    return false;
                
                for (Cell neighbor : getNeighbors(i, y)){
                    if (!isValidPoint(i, y))
                        return false;
                }
            }
        }
        return true;
    }
    
    public boolean isValidPoint(Point2D point){
        return isValidPoint(point.getX(), point.getY());
    }
    
    public boolean isValidPoint(double x, double y){
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    }
    //nested class Cell
    public class Cell extends Rectangle{
        public int x, y;//x and y condinates of a cell
        public Ship ship = null;//set placed ships to null
        public boolean isShot = false;//set the value of a shot cell to false
        //public boolean guessed = false;//set the value for guessed cell to false
        
        
        private Board board;
        
        public Cell(int x, int y, Board board){
            super(20,20);
            this.x = x;
            this.y = y;
            this.board = board;
            setFill(Color.WHITE);//Fill color of a cell is set to white
            setStroke(Color.BLACK);//the bounds of a cell are set to black
        }
        //boolean method to check if a cell is shot 
        public boolean shoot(){
            isShot = true;//Set shot to true
            setFill(Color.BLACK);//Sets missed shots to black
            //Checks if there are still ships on the board
            //If there are still ships on the board, hit.
            if (ship != null){
                ship.hit();
                setFill(Color.RED);//Sets the hit part of a ship to red
                //System.out.println("You Hit");//Prints when a ship is hit
                //If the ship has been shot or ship is not alive
                if (!ship.isAlive()){
                    board.ships--;//reduce the number of ships on the board
                }
                //assert false: "Nothing gets hit";
                return true;//To say the ship was hit
            }
            //System.out.println("You Missed");
            return false;//false for a miss
        }
    
    }
}
