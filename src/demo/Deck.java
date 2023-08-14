package demo;

import java.util.ArrayList;

public class Deck {
	protected ArrayList<Card> cards;
	protected ArrayList<Card> drawnCards; // Cards already drawn
	protected ArrayList<Card> lowCards; // Cards lower than 8
	protected ArrayList<Card> highCards; // Cards higher than 8
	protected ArrayList<Card> eights; // Cards equal to 8

	char[] suits = { 'H', 'D', 'C', 'S' };
	String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };

	public Deck() { // Brand new deck
		this.cards = new ArrayList<>();
		this.drawnCards = new ArrayList<>();
		this.highCards = new ArrayList<>();
		this.lowCards = new ArrayList<>();
		this.eights = new ArrayList<>();
		int counter = 1;
		for (String rank : ranks) { // Starts with 2H, 2D, 2C, 2S... according to predetermined numbering scheme
			for (char suit : suits) {
				Card newCard = new Card(counter, rank, suit);
				cards.add(newCard);
				counter++; // Should reach 52
			}
		}
	}

	// Getters
	public ArrayList<Card> getCards() {
		return cards;
	}

	public ArrayList<Card> getDrawnCards() {
		return drawnCards;
	}

	public int cardsInDeck() {
		return cards.size();
	}

	public boolean stillCards() {
		return cardsInDeck() > 0;
	}

	public void shuffleDeck() {
		if (stillCards()) {
			if (cardsInDeck() == 1) { // Makes no sense to shuffle only one card
				System.out.println("One card left!");
			}
			for (int i = 0; i < cards.size(); i++) {
				int r = i + (int) (Math.random() * (cards.size() - i));
				Card temp = cards.get(r);
				cards.set(r, cards.get(i));
				cards.set(i, temp);
			}
		} else { // No cards to shuffle; thus, game is over
			throw new NullPointerException("Cannot shuffle an empty deck.");
		}
	}

	public Card drawCard() {
		try {
			return getCards().remove(0); // Take the first card from the shuffled deck
			// Removing a card from the deck also removes it from the deck for the rest of
			// the round/game.
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Card cannot be drawn");
			return null;
		}
	}

	public void addToSubDeck(Card thisCard) {
		this.drawnCards.add(thisCard);
		if (thisCard.getId() < 25) { // For cases where player draws 8
			this.lowCards.add(thisCard);
			//System.out.println(this.lowCards.size() + " low cards");
		} else if (thisCard.getRank().equals("8")) {
			this.eights.add(thisCard);
			//System.out.println(this.eights.size() + " eights");
		} else { // if (thisCard.getId() > 29)
			this.highCards.add(thisCard);
			//System.out.println(this.highCards.size() + " high cards");
		}
	}

	public boolean removeFirstCard(Card firstCard) {
		for (Card card : cards) {
			if (card.isEqualTo(firstCard)) {
				return cards.remove(card);
			}
		}
		System.out.println("Object not removed from array");
		return false;
	}
}