package battleship;

import java.util.ArrayList;
import java.util.Observable;
import javafx.scene.Parent;


public class Ship extends Parent{
    public int type;//type of the ship, 5, 4, 3, 2, 2
    public boolean vertical = true;//boolean to place ships vertically on the board
    private ArrayList<String> location = new ArrayList<String>();//an arrayList for locations
    private int health;//current health of the ship

    public Ship(int type, boolean vertical) {
        this.type = type;
        this.vertical = vertical;
        health = type;//health of the ship is equal to type when ship is created
    }

    public void hit() {
        assert health > 0: "Ships get shot";//asserts if ship is still healthy...cannot hit an already hit ship
        health--;//decrease health by one
        if (health == 0){
            System.out.println("Ship "+type+" is sunk!");//prints ship is sunk
            assert health == 0: "Ship health is above zero";//asserts the ship has been sunk.
        }
        
    }

    public boolean isAlive() {//checks wheather ship is alive or not.
        assert type > 0;//asserts if the ship is still alive
        if(health == 0){//checks if health of a ship is 0.
            System.out.println("Ship shot");
            //assert false;//asserts that the ship is not alive anymore.
        }
        assert type != health;//checks whether type is equal to health.
        return health > 0;//Health of the ship is above 0...ship still healthy
        
    }

    public ArrayList<String> getLocations(){
        return location;
    }

    public int getType(){
        return type;
    }

    public void setLocation(ArrayList<String> locationToSet){
        this.location.addAll(locationToSet);
    }

}
