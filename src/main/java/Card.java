public class Card implements Comparable<Card>{

    private Suit suit;
    private Value value;

    Card(Value value, Suit suit ){
        this.suit = suit;
        this.value = value;
    }

    Card(char value, char suit){
        if(value=='2') this.value = Value.TWO;
        if(value=='3') this.value = Value.THREE;
        if(value=='4') this.value = Value.FOUR;
        if(value=='5') this.value = Value.FIVE;
        if(value=='6') this.value = Value.SIX;
        if(value=='7') this.value = Value.SEVEN;
        if(value=='8') this.value = Value.EIGHT;
        if(value=='9') this.value = Value.NINE;
        if(value=='T') this.value = Value.TEN;
        if(value=='J') this.value = Value.JACK;
        if(value=='Q') this.value = Value.QUEEN;
        if(value=='K') this.value = Value.KING;
        if(value=='A') this.value = Value.ACE;
        if(suit=='H') this.suit = Suit.HEARTS;
        if(suit=='D') this.suit = Suit.DIAMONDS;
        if(suit=='C') this.suit = Suit.CLUBS;
        if(suit=='S') this.suit = Suit.SPADES;
    }

    Suit getSuit(){
        return this.suit;
    }

    Value getValue() {
        return this.value;
    }

    int getNumericValue(){
        return this.value.getValue();
    }

    public String toString(){
        return this.value+" of "+this.suit;
    }

    public int compareTo(Card card) {
        return card.getNumericValue() - this.getNumericValue();
    }
}