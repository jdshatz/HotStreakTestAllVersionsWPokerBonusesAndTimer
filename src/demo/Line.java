package demo;

public class Line {
	private final Deck thisDeck;
	private Card currentCard = null;
	private Card nextCard = null;
	private boolean isStillGoing = true;
	private int streak = 0;
	private boolean onAutoWin = false;
	private boolean isLost = false;
	protected boolean isFlush = true;
	protected char firstSuit;

	private int lineNumber;

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public boolean isFlush() {
		return isFlush;
	}

	public void setFlush(boolean flush) {
		isFlush = flush;
	}

	public char getFirstSuit() {
		return firstSuit;
	}

	public void setFirstSuit(char firstSuit) {
		this.firstSuit = firstSuit;
	}

	public Line() {
		this.thisDeck = new Deck();
		this.thisDeck.shuffleDeck();
		this.setFlush(true);
	}

	public Deck getThisDeck() {
		return thisDeck;
	}

	public Card getCurrentCard() {
		return currentCard;
	}

	public void setCurrentCard(Card currentCard) {
		this.currentCard = currentCard;
	}

	public Card getNextCard() {
		return nextCard;
	}

	public void setNextCard(Card nextCard) {
		this.nextCard = nextCard;
	}

	public boolean isStillGoing() {
		return isStillGoing;
	}

	public void setStillGoing(boolean isStillGoing) {
		this.isStillGoing = isStillGoing;
	}

	public boolean isAutoWin() {
		return (this.currentCard.isTwo() || this.currentCard.isAce());
	}

	public boolean setToAutoWin() {
		return ((this.nextCard.isTwo() || this.nextCard.isAce()) && this.getStreak() < 3);
	}

	public int getStreak() {
		return streak;
	}

	//METHODS FOR DRAWING CARDS, ETC.

	public Card drawFirstCard() {
		this.setCurrentCard(this.thisDeck.drawCard());
		this.setFirstSuit(this.getCurrentCard().getSuit());
		return this.getCurrentCard();
	}

	public Card drawNextCard() {
		this.setNextCard(this.thisDeck.drawCard());
		if (this.getNextCard().getSuit() != getFirstSuit())
			this.setFlush(false);
		return this.getNextCard();
	}

	public void setCurrentCardToNextCard() {
		this.setCurrentCard(getNextCard());
		this.streak += 1;
	}

	public boolean removeFirstCard(Card firstCard) { //Passing a non-index param to List.remove() returns a boolean
		this.setCurrentCard(firstCard);
		boolean removed = this.thisDeck.removeFirstCard(this.getCurrentCard());
		if (!removed) {
			System.out.println("NULL!!!!!");
			throw new NullPointerException();
		}
		return true;
	}

	public void continueToNextCard() {
		this.thisDeck.addToSubDeck(this.currentCard);
		if (this.setToAutoWin())
			this.setOnAutoWin(true);
		this.setCurrentCardToNextCard();
	}

	//Handle auto-wins in loops that iterate over line-based arrays
	public boolean isOnAutoWin() {
		return onAutoWin;
	}

	public void setOnAutoWin(boolean onAutoWin) {
		this.onAutoWin = onAutoWin;
	}

	public boolean isLost() {
		return isLost;
	}

	public void setLost(boolean isLost) {
		this.isLost = isLost;
	}
}