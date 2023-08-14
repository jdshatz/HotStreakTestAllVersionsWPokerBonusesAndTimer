package demo;

public class Card {
	private final int id;
    private final String rank;
    private final char suit;

    public Card(int id, String rank, char suit) {
    	this.id = id;
        this.rank = rank;
        this.suit = suit;
    }

    public char getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }
    
    public int getId(){
    	return id;
    }
    
    public String printCard(){
    	return String.format("%s%c", rank, suit);
    }
    
    public boolean isLessThan(Card c){
    	return (!this.getRank().equals(c.getRank())) && (this.id < c.id);
    }
    
    public boolean isGreaterThan(Card c){
    	return (!this.getRank().equals(c.getRank())) && (this.id > c.id);
    }
    
    public boolean isTwo(){
    	return this.id < 5;
    }
    
    public boolean isAce(){
    	return this.id > 48;
    }

    public boolean sameValueAs(Card c){
    	return (this.getRank().equals(c.getRank()));
    }
    
    public boolean isEqualTo(Card c) {
    	return (this.getRank().equals(c.getRank())) & (this.getSuit() == c.getSuit());
    }
}
