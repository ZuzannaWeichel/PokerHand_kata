import java.util.*;

public class Hand implements Comparable<Hand>{

    private String name;
    private ArrayList<Card> cards;
    private PokerHandRanks rank;

    Hand(String name){
        this.name = name;
        this.cards = new ArrayList<>();
    }
    public Hand(String name,ArrayList<Card> cards){
        this.name = name;
        this.cards = cards;
    }

    void addCards(ArrayList<Card> cards){
        this.cards.clear();
        this.cards.addAll(cards);
    }

    ArrayList<Card> getCards(){
        return this.cards;
    }

    String getName(){
        return this.name;
    }

    void printAllCards(){
        Collections.sort(cards);
        System.out.println(this.name);
        for (Card c: cards) {
            System.out.println(c);
        }
    }

    boolean isTheSameSuit(){
        Card checkedCard = cards.get(0);
        boolean isSame = false;
        for (int i = 1; i <cards.size() ; i++) {
            if(checkedCard.getSuit() != cards.get(i).getSuit()){
                return false;
            }else{
                checkedCard = cards.get(i);
                isSame = true;
            }
        }
        return isSame;
    }

    boolean isInTheRow(){
        Collections.sort(cards);
        boolean isItTrue = false;
        //System.out.println(cards);
        Card checkedCard = cards.get(0);
        for (int i = 1; i <cards.size() ; i++) {
            if(checkedCard.getNumericValue()-1 != cards.get(i).getNumericValue()){
                isItTrue = false;
            }else{
                checkedCard = cards.get(i);
                isItTrue = true;
            }
        }
        return isItTrue;
    }

    Map<Integer,Integer> hasManyOfAKind(){
        Collections.sort(cards);
        Map<Integer,Integer> thisMany = new HashMap<>();
        for (int i = 0; i <cards.size()-1 ; i++) {
            Card checkedCard = cards.get(i);
            if(checkedCard.getNumericValue() == cards.get(i+1).getNumericValue()){
               thisMany.putIfAbsent(checkedCard.getNumericValue(),1);
               thisMany.put(checkedCard.getNumericValue(), thisMany.get(checkedCard.getNumericValue())+1);
            }
        }
        return thisMany;
    }

    PokerHandRanks findHandRank() {
        PokerHandRanks rank = null;
        if(isRoyalFlush()){
          rank = PokerHandRanks.ROYAL_FLUSH;
        }else if(isStraightFlush()) {
          rank = PokerHandRanks.STRAIGHT_FLUSH;
        }else if(isFourOfAKind()){
              rank = PokerHandRanks.FOUR_OF_A_KIND;
        }else if(isFullHouse()){
              rank = PokerHandRanks.FULL_HOUSE;
        }else if(isFlush()) {
              rank = PokerHandRanks.FLUSH;
        }else if(isStraight()){
              rank = PokerHandRanks.STRAIGHT;
        }else if(isThreeOfAKind()){
              rank = PokerHandRanks.THREE_OF_A_KIND;
        }else if(isTwoPairs()){
              rank = PokerHandRanks.TWO_PAIR;
        }else if(isOnePair()){
              rank = PokerHandRanks.ONE_PAIR;
        }else if(isHighCard()){
              rank = PokerHandRanks.HIGH_CARD;
        }
          return rank;
    }

    private boolean isRoyalFlush(){
        return isTheSameSuit()&& isInTheRow() && cards.get(0).getValue() == Value.ACE;
    }
    private boolean isStraightFlush(){
        return isTheSameSuit() && isInTheRow() && cards.get(0).getValue() != Value.ACE;
    }
    private boolean isFourOfAKind(){
        return hasManyOfAKind().keySet().size() == 1 && hasManyOfAKind().containsValue(4);
    }
    private boolean isFullHouse(){
        return hasManyOfAKind().keySet().size() == 2 && hasManyOfAKind().containsValue(3);
    }
    private boolean isFlush(){
        return isTheSameSuit() && !isInTheRow() && hasManyOfAKind().isEmpty();
    }
    private boolean isStraight(){
        return !isTheSameSuit() && isInTheRow();
    }
    private boolean isThreeOfAKind(){
        return hasManyOfAKind().keySet().size() == 1 && hasManyOfAKind().containsValue(3);
    }
    private boolean isTwoPairs(){
        return hasManyOfAKind().keySet().size() == 2 && hasManyOfAKind().containsValue(2) &&
                !hasManyOfAKind().containsValue(3);
    }
    private boolean isOnePair(){
        return hasManyOfAKind().keySet().size() == 1 && hasManyOfAKind().containsValue(2);
    }
    private boolean isHighCard(){
        return !isTheSameSuit()&& !isInTheRow() && hasManyOfAKind().isEmpty();
    }

    @Override
    public int compareTo(Hand hand) {
        ArrayList<Integer> keysOfHandOne = new ArrayList<>(hasManyOfAKind().keySet());
        ArrayList<Integer> keysOfHandTwo = new ArrayList<>(hand.hasManyOfAKind().keySet());
        Integer compared = findHandRank().getRankValue()- hand.findHandRank().getRankValue();
        if(compared != 0){
            return compared;

        }else if(isRoyalFlush() || isStraight() || isStraightFlush() || isFlush()){
            return hand.cards.get(0).compareTo(cards.get(0));

        }else if(isFourOfAKind() || isThreeOfAKind() || isOnePair()){
            if(keysOfHandOne.get(0) - keysOfHandTwo.get(0) == 0){
                for (int i = 0; i <5 ; i++) {
                    if(cards.get(i).getNumericValue()!= hand.cards.get(i).getNumericValue()){
                       return hand.cards.get(i).compareTo(cards.get(i));
                    }
                }
            }else{
                return keysOfHandOne.get(0) - keysOfHandTwo.get(0);
            }

        }else if(isFullHouse() || isTwoPairs()) {
            if (keysOfHandOne.get(0) - keysOfHandTwo.get(0) == 0) {
                if(keysOfHandOne.get(1) - keysOfHandTwo.get(1)==0){
                    for (int i = 0; i <5 ; i++) {
                        if(cards.get(i).getNumericValue()!= keysOfHandOne.get(0)
                                && cards.get(i).getNumericValue()!= keysOfHandOne.get(1)){
                            return hand.cards.get(i).compareTo(cards.get(i));
                        }
                    }
                }
            } else {
                return keysOfHandOne.get(0) - keysOfHandTwo.get(0);
            }
        }else if(isHighCard()){
            for (int i = 0; i <5 ; i++) {
                if(hand.cards.get(i).compareTo(cards.get(i)) != 0){
                        return hand.cards.get(i).compareTo(cards.get(i));
                }
            }
        }   return 300;
    }
}
