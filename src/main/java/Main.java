import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

public class Main {

    public static void main(String [] args) throws FileNotFoundException {
        Game pokerGame = new Game(new Hand("Kevin") ,new Hand("Zuzia"));
        URL url = Main.class.getResource("testInput.txt");
        File file = new File(url.getPath());
        Croupier dealer = new Croupier(pokerGame, file);

        for (int i = 0; i <1000 ; i++) {
            dealer.giveCards();
            pokerGame.getHandOne().printAllCards();
            pokerGame.getHandTwo().printAllCards();
            System.out.println();
            System.out.println(pokerGame.getHandOne().getName()+" "+pokerGame.getHandOne().findHandRank());
            System.out.println(pokerGame.getHandTwo().getName()+" "+pokerGame.getHandTwo().findHandRank());
            System.out.println(pokerGame.whoWins());
            System.out.println();
        }
    }
}
