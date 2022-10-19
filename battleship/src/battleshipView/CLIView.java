//Unssuccessful execution 
package battleship;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import battleship.Board;

public class CLIView {
    public ArrayList<Ship> ship = new ArrayList<Ship>();

    public static void main(String[] args){ 
        CLIView cli = new CLIView();
        cli.setUp();    
    }
    
    public void setUp(){
        ship.add(new Ship(1,true));
        ship.add(new Ship(2,false));
        ship.add(new Ship(3,true));
        ship.add(new Ship(4,true));
        ship.add(new Ship(5,false));

        System.out.println("Play the game...sink whatever is sinkable!");
        //playIt();
    }

    /*public void play(){
        String guess, result;
        Scanner input = new Scanner(System.in);
        while (!ship.isEmpty){
            result = "miss";
            numGuess++;
            System.out.println("Enter a guess");
            guess = input.nextLine();
            guess = guess.toUpperCase();
            for (int i = 0; i < ship.type(); i++){
                result = type.get(i).check(guess);
                if (result.equals("Hit")){
                    result = ("Ship" + type.get(i).getType()+ " is sunk");
                    type.remove(i);
                    break;
                    }
            }
            System.out.println(result);
        }
        input.close();
        finish();
    }

    private void setLocations(){
        Random rand = new Random();
        ArrayList<String> locationToSet = new ArrayList<String>();
        ArrayList<String> temp = null;
        int let, num, incl, incn;
        boolean worked;
        for (int i = 0; i < ship.type(); i++){
            worked = false;
            start:
                while(!worked){
                    locationToSet.clear();
                }
        }
    }*/
    
}
