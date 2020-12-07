package fr.mael.tarot.card;

import java.util.ArrayList;
import java.util.List;

public class CardList {

	private List<Card> cards = new ArrayList<Card>();
	
	public CardList() {
		
	}
	
	public List<Card> getCards() {
		return this.cards;
	}
	
	public CardList addCard(Card card) {
		this.cards.add(card);
		return this;
	}
	
	public CardList removeCard(Card card) {
		this.cards.remove(card);
		return this;
	}
	
	public CardList removeCardById(int cardId) {
		this.cards.remove(cardId);
		return this;
	}
	
	public boolean containCard(Card card) {
		return this.cards.contains(card);
	}
	
	public Card searchCard(String search) {
		try {
			int id = Integer.parseInt(search);
			
			for (Card card : this.cards) {
				if (card.getId() == id) {
					return card;
				}
			}
			
		} catch (NumberFormatException e) {
			
			for (Card card : this.cards) {
				if (card.getName().contains(search)) {
					return card;
				}
			}
		}
		return null;
	}
	
}
