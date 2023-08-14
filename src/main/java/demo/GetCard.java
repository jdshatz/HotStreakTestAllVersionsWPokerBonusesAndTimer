package demo;

import java.util.HashMap;

public class GetCard {
	private final Card card;
	private final HashMap<String, String> activeCardMap;
	private final HashMap<String, String> inactiveCardMap;

	public GetCard(Card card) {
		this.card = card;
		this.activeCardMap = setUpActiveHashMap();
		this.inactiveCardMap = setUpInactiveHashMap();
	}
	
	private HashMap<String, String> setUpActiveHashMap() {
		HashMap<String, String> cardMap = new HashMap<>();
		cardMap.put("2C", "/resources/Cards/Card_2Clubs.png");
		cardMap.put("2D", "/resources/Cards/Card_2Diamonds.png");
		cardMap.put("2H", "/resources/Cards/Card_2Hearts.png");
		cardMap.put("2S", "/resources/Cards/Card_2Spades.png");
		cardMap.put("3C", "/resources/Cards/Card_3Clubs.png");
		cardMap.put("3D", "/resources/Cards/Card_3Diamonds.png");
		cardMap.put("3H", "/resources/Cards/Card_3Hearts.png");
		cardMap.put("3S", "/resources/Cards/Card_3Spades.png");
		cardMap.put("4C", "/resources/Cards/Card_4Clubs.png");
		cardMap.put("4D", "/resources/Cards/Card_4Diamonds.png");
		cardMap.put("4H", "/resources/Cards/Card_4Hearts.png");
		cardMap.put("4S", "/resources/Cards/Card_4Spades.png");
		cardMap.put("5C", "/resources/Cards/Card_5Clubs.png");
		cardMap.put("5D", "/resources/Cards/Card_5Diamonds.png");
		cardMap.put("5H", "/resources/Cards/Card_5Hearts.png");
		cardMap.put("5S", "/resources/Cards/Card_5Spades.png");
		cardMap.put("6C", "/resources/Cards/Card_6Clubs.png");
		cardMap.put("6D", "/resources/Cards/Card_6Diamonds.png");
		cardMap.put("6H", "/resources/Cards/Card_6Hearts.png");
		cardMap.put("6S", "/resources/Cards/Card_6Spades.png");
		cardMap.put("7C", "/resources/Cards/Card_7Clubs.png");
		cardMap.put("7D", "/resources/Cards/Card_7Diamonds.png");
		cardMap.put("7H", "/resources/Cards/Card_7Hearts.png");
		cardMap.put("7S", "/resources/Cards/Card_7Spades.png");
		cardMap.put("8C", "/resources/Cards/Card_8Clubs.png");
		cardMap.put("8D", "/resources/Cards/Card_8Diamonds.png");
		cardMap.put("8H", "/resources/Cards/Card_8Hearts.png");
		cardMap.put("8S", "/resources/Cards/Card_8Spades.png");		
		cardMap.put("9C", "/resources/Cards/Card_9Clubs.png");
		cardMap.put("9D", "/resources/Cards/Card_9Diamonds.png");
		cardMap.put("9H", "/resources/Cards/Card_9Hearts.png");
		cardMap.put("9S", "/resources/Cards/Card_9Spades.png");
		cardMap.put("10C", "/resources/Cards/Card_10Clubs.png");
		cardMap.put("10D", "/resources/Cards/Card_10Diamonds.png");
		cardMap.put("10H", "/resources/Cards/Card_10Hearts.png");
		cardMap.put("10S", "/resources/Cards/Card_10Spades.png");
		cardMap.put("JC", "/resources/Cards/Card_JackClubs.png");
		cardMap.put("JD", "/resources/Cards/Card_JackDiamonds.png");
		cardMap.put("JH", "/resources/Cards/Card_JackHearts.png");
		cardMap.put("JS", "/resources/Cards/Card_JackSpades.png");
		cardMap.put("QC", "/resources/Cards/Card_QueenClubs.png");
		cardMap.put("QD", "/resources/Cards/Card_QueenDiamonds.png");
		cardMap.put("QH", "/resources/Cards/Card_QueenHearts.png");
		cardMap.put("QS", "/resources/Cards/Card_QueenSpades.png");
		cardMap.put("KC", "/resources/Cards/Card_KingClubs.png");
		cardMap.put("KD", "/resources/Cards/Card_KingDiamonds.png");
		cardMap.put("KH", "/resources/Cards/Card_KingHearts.png");
		cardMap.put("KS", "/resources/Cards/Card_KingSpades.png");
		cardMap.put("AC", "/resources/Cards/Card_AceClubs.png");
		cardMap.put("AD", "/resources/Cards/Card_AceDiamonds.png");
		cardMap.put("AH", "/resources/Cards/Card_AceHearts.png");
		cardMap.put("AS", "/resources/Cards/Card_AceSpades.png");
		cardMap.put("WI", "/resources/Cards/AutoWinCard.png");
		cardMap.put("BC", "/resources/CardsInactive2/back-of-card-active.png");
		return cardMap;
	}
	
	private HashMap<String, String> setUpInactiveHashMap(){
		HashMap<String, String> cardMap = new HashMap<>();
		cardMap.put("2C", "/resources/CardsInactive2/Card_2Clubs-Inactive.png");
		cardMap.put("2D", "/resources/CardsInactive2/Card_2Diamonds-Inactive.png");
		cardMap.put("2H", "/resources/CardsInactive2/Card_2Hearts-Inactive.png");
		cardMap.put("2S", "/resources/CardsInactive2/Card_2Spades-Inactive.png");
		cardMap.put("3C", "/resources/CardsInactive2/Card_3Clubs-Inactive.png");
		cardMap.put("3D", "/resources/CardsInactive2/Card_3Diamonds-Inactive.png");
		cardMap.put("3H", "/resources/CardsInactive2/Card_3Hearts-Inactive.png");
		cardMap.put("3S", "/resources/CardsInactive2/Card_3Spades-Inactive.png");
		cardMap.put("4C", "/resources/CardsInactive2/Card_4Clubs-Inactive.png");
		cardMap.put("4D", "/resources/CardsInactive2/Card_4Diamonds-Inactive.png");
		cardMap.put("4H", "/resources/CardsInactive2/Card_4Hearts-Inactive.png");
		cardMap.put("4S", "/resources/CardsInactive2/Card_4Spades-Inactive.png");
		cardMap.put("5C", "/resources/CardsInactive2/Card_5Clubs-Inactive.png");
		cardMap.put("5D", "/resources/CardsInactive2/Card_5Diamonds-Inactive.png");
		cardMap.put("5H", "/resources/CardsInactive2/Card_5Hearts-Inactive.png");
		cardMap.put("5S", "/resources/CardsInactive2/Card_5Spades-Inactive.png");
		cardMap.put("6C", "/resources/CardsInactive2/Card_6Clubs-Inactive.png");
		cardMap.put("6D", "/resources/CardsInactive2/Card_6Diamonds-Inactive.png");
		cardMap.put("6H", "/resources/CardsInactive2/Card_6Hearts-Inactive.png");
		cardMap.put("6S", "/resources/CardsInactive2/Card_6Spades-Inactive.png");
		cardMap.put("7C", "/resources/CardsInactive2/Card_7Clubs-Inactive.png");
		cardMap.put("7D", "/resources/CardsInactive2/Card_7Diamonds-Inactive.png");
		cardMap.put("7H", "/resources/CardsInactive2/Card_7Hearts-Inactive.png");
		cardMap.put("7S", "/resources/CardsInactive2/Card_7Spades-Inactive.png");
		cardMap.put("8C", "/resources/CardsInactive2/Card_8Clubs-Inactive.png");
		cardMap.put("8D", "/resources/CardsInactive2/Card_8Diamonds-Inactive.png");
		cardMap.put("8H", "/resources/CardsInactive2/Card_8Hearts-Inactive.png");
		cardMap.put("8S", "/resources/CardsInactive2/Card_8Spades-Inactive.png");		
		cardMap.put("9C", "/resources/CardsInactive2/Card_9Clubs-Inactive.png");
		cardMap.put("9D", "/resources/CardsInactive2/Card_9Diamonds-Inactive.png");
		cardMap.put("9H", "/resources/CardsInactive2/Card_9Hearts-Inactive.png");
		cardMap.put("9S", "/resources/CardsInactive2/Card_9Spades-Inactive.png");
		cardMap.put("10C", "/resources/CardsInactive2/Card_10Clubs-Inactive.png");
		cardMap.put("10D", "/resources/CardsInactive2/Card_10Diamonds-Inactive.png");
		cardMap.put("10H", "/resources/CardsInactive2/Card_10Hearts-Inactive.png");
		cardMap.put("10S", "/resources/CardsInactive2/Card_10Spades-Inactive.png");
		cardMap.put("JC", "/resources/CardsInactive2/Card_JackClubs-Inactive.png");
		cardMap.put("JD", "/resources/CardsInactive2/Card_JackDiamonds-Inactive.png");
		cardMap.put("JH", "/resources/CardsInactive2/Card_JackHearts-Inactive.png");
		cardMap.put("JS", "/resources/CardsInactive2/Card_JackSpades-Inactive.png");
		cardMap.put("QC", "/resources/CardsInactive2/Card_QueenClubs-inactive.png");
		cardMap.put("QD", "/resources/CardsInactive2/Card_QueenDiamonds-inactive.png");
		cardMap.put("QH", "/resources/CardsInactive2/Card_QueenHearts-inactive.png");
		cardMap.put("QS", "/resources/CardsInactive2/Card_QueenSpades-Inactive.png");
		cardMap.put("KC", "/resources/CardsInactive2/Card_KingClubs-Inactive.png");
		cardMap.put("KD", "/resources/CardsInactive2/Card_KingDiamonds-inactive.png");
		cardMap.put("KH", "/resources/CardsInactive2/Card_KingHearts-inactive.png");
		cardMap.put("KS", "/resources/CardsInactive2/Card_KingSpades-inactive.png");
		cardMap.put("AC", "/resources/CardsInactive2/Card_AceClubs-Inactive.png");
		cardMap.put("AD", "/resources/CardsInactive2/Card_AceDiamonds-Inactive.png");
		cardMap.put("AH", "/resources/CardsInactive2/Card_AceHearts-Inactive.png");
		cardMap.put("AS", "/resources/CardsInactive2/Card_AceSpades-Inactive.png");
		cardMap.put("IB", "/resources/CardsInactive2/Back-of-card-inactive.png");
		return cardMap;
	}
	
	public String getActiveCardImage() {
		return activeCardMap.get(card.printCard());
	}
	
	public String getInactiveCardImage() {
		return inactiveCardMap.get(card.printCard());
	}
}
