import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class PokerHandsTest {
    private Game poker;
    private Croupier dealer;

    public PokerHandsTest() throws FileNotFoundException {
        poker =new Game(new Hand("one"), new Hand("two"));
        URL url = Main.class.getResource("testInput.txt");
        File file = new File(url.getPath());
        dealer = new Croupier(poker,file);
    }

    @Test
    void testFindHandRank(){
        dealer.giveCards();
        assertEquals(PokerHandRanks.HIGH_CARD,poker.getHandOne().findHandRank());
        dealer.giveCards();
        assertEquals(PokerHandRanks.ONE_PAIR,poker.getHandOne().findHandRank());
        dealer.giveCards();
        assertEquals(PokerHandRanks.TWO_PAIR, poker.getHandOne().findHandRank());
        dealer.giveCards();
        assertEquals(PokerHandRanks.THREE_OF_A_KIND,poker.getHandOne().findHandRank());
        dealer.giveCards();
        assertEquals(PokerHandRanks.STRAIGHT, poker.getHandOne().findHandRank());
        dealer.giveCards();
        assertEquals(PokerHandRanks.FLUSH, poker.getHandOne().findHandRank());
        dealer.giveCards();
        assertEquals(PokerHandRanks.FULL_HOUSE, poker.getHandOne().findHandRank());
        dealer.giveCards();
        assertEquals(PokerHandRanks.FOUR_OF_A_KIND,poker.getHandOne().findHandRank());
        dealer.giveCards();
        assertEquals(PokerHandRanks.STRAIGHT_FLUSH, poker.getHandOne().findHandRank());
        dealer.giveCards();
        assertEquals(PokerHandRanks.ROYAL_FLUSH, poker.getHandOne().findHandRank());
    }

    @Test
    void testIsTheSameSuit(){
        dealer.giveCards();
        assertFalse(poker.getHandOne().isTheSameSuit());
        dealer.giveCards();
        assertFalse(poker.getHandOne().isTheSameSuit());
        dealer.giveCards();
        assertFalse(poker.getHandOne().isTheSameSuit());
        dealer.giveCards();
        assertFalse(poker.getHandOne().isTheSameSuit());
        dealer.giveCards();
        assertFalse(poker.getHandOne().isTheSameSuit());
        dealer.giveCards();
        assertTrue(poker.getHandOne().isTheSameSuit());

    }

    @Test
    void testisInTheRow(){
        dealer.giveCards();
        assertFalse(poker.getHandOne().isInTheRow());
        dealer.giveCards();
        assertFalse(poker.getHandOne().isInTheRow());
        dealer.giveCards();
        assertFalse(poker.getHandOne().isInTheRow());
        dealer.giveCards();
        assertFalse(poker.getHandOne().isInTheRow());
        dealer.giveCards();
        assertTrue(poker.getHandOne().isInTheRow());
    }

    @Test
    void testHasManyOfAKind(){
        HashMap<Integer,Integer>expectedMap = new HashMap<>();
        expectedMap.put(5,2);
        dealer.giveCards();
        dealer.giveCards();
        assertEquals(expectedMap,poker.getHandOne().hasManyOfAKind());
    }

    @Test
    void testHasManyOfAKindII(){
        HashMap<Integer,Integer>expectedMap = new HashMap<>();
        expectedMap.put(5,2);
        expectedMap.put(14,2);
        dealer.giveCards();
        dealer.giveCards();
        dealer.giveCards();
        assertEquals(expectedMap,poker.getHandOne().hasManyOfAKind());
    }

    @Test
    void testFourOfAKind(){
        dealer.giveTestCards("AH AS AC AD 2H 5C 6H 9D TD 2C");
        assertEquals("one is the winner! with FOUR_OF_A_KIND",poker.whoWins());
    }

    @Test
    void testHighCard(){
        dealer.giveTestCards("AD 7H 5C 4H 2S AD 7H 5C 4H 3S");
        assertEquals("two is the winner! with HIGH_CARD", poker.whoWins());
    }

    @Test
    void testStraightVsStraight(){
        dealer.giveTestCards("8H 9D TC JD QS 4H 5S 6C 7C 8H");
        assertEquals("one is the winner! with STRAIGHT", poker.whoWins());
    }

    @Test
    void testStraightFlush(){
        dealer.giveTestCards("8H 9H TH JH QH 4H 5H 6H 7H 8H");
        assertEquals("one is the winner! with STRAIGHT_FLUSH",poker.whoWins());
    }

    @Test
    void testRoyalFlush(){
        dealer.giveTestCards("TC JC QC KC AC TD JD QD KD AD");
        assertEquals("It's a draw",poker.whoWins());
    }

    @Test
    void testTwoPairs(){
        dealer.giveTestCards("TC TD 5H 5S 3C TD TH 6S 6C 8D");
        assertEquals("two is the winner! with TWO_PAIR",poker.whoWins());
    }

    @Test
    void testTwoPairLast(){
        dealer.giveTestCards("TC TD 5H 5S 3C TD TH 5S 5C 8D");
        assertEquals("two is the winner! with TWO_PAIR", poker.whoWins());
    }
    @Test
    void testTwoOfKindLeftOver(){
        dealer.giveTestCards("TC TD 7S 5H 3C TD TC 7S 5H 2S");
        assertEquals("one is the winner! with ONE_PAIR",poker.whoWins());
    }
    @Test
    void testThreeOFKindLeft(){
        dealer.giveTestCards("6H 5H 5C 5D 2H 8H 7D 5D 5H 5C");
        assertEquals("two is the winner! with THREE_OF_A_KIND", poker.whoWins());
    }
}
