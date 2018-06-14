import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Croupier {

    private Scanner reader;
    private Game game;

    Croupier(Game game, File file) throws FileNotFoundException {
        this.reader = new Scanner(file);
        this.game = game;
    }

    void giveCards()  {
        String[]line = reader.nextLine().split(" ");
        System.out.println(Arrays.toString(line));
        makeIntoCards(line);
    }

    void giveTestCards(String cards){
        String[] line =cards.split(" ");
        makeIntoCards(line);
    }

    private void makeIntoCards(String[] line){
        ArrayList<Card> cardsForHand = new ArrayList<>();
        for (int i = 0; i <5; i++) {
            cardsForHand.add(new Card(line[i].charAt(0),line[i].charAt(1)));
        }
        game.getHandOne().addCards(cardsForHand);
        cardsForHand.clear();
        for (int i = 5; i <10 ; i++) {
            cardsForHand.add(new Card(line[i].charAt(0),line[i].charAt(1)));
        }
        game.getHandTwo().addCards(cardsForHand);
    }
}
