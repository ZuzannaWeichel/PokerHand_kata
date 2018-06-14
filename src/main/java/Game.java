class Game {
    private Hand handOne;
    private Hand handTwo;

    Game(Hand handOne, Hand handTwo){
        this.handOne = handOne;
        this.handTwo = handTwo;
    }

    Hand getHandOne(){
        return this.handOne;
    }
    Hand getHandTwo(){
        return this.handTwo;
    }

    String whoWins(){
        if(handOne.compareTo(handTwo)== 0) {
            return "It's a draw";
        }else if(handOne.compareTo(handTwo) > 0){
            return handOne.getName()+" is the winner! with "+ handOne.findHandRank();
        }else{
            return handTwo.getName()+" is the winner! with "+ handTwo.findHandRank();
        }
    }
}
