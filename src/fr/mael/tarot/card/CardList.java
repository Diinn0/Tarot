package fr.mael.tarot.card;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent a Deck of cards
 * @author Mael
 *
 */
public class CardList {

	private List<Card> cards = new ArrayList<Card>();
	private String name;
	
	public CardList(String name) {
		this.name = name;
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
	
	/**
	 * This method is used to draw Card 
	 * @param search
	 * @return
	 */
	public List<Card> getCardsBySearch(String search) {		
		if (!search.isEmpty()) {
			List<Card> cards = new ArrayList<Card>();

			try {
				int id = Integer.parseInt(search);
				
				for (Card card : this.cards) {
					if (card.getId() == id) {
						 cards.add(card);
					}
				}
				
				return cards;
				
			} catch (NumberFormatException e) {
				
				for (Card card : this.cards) {
					if (card.getName().toLowerCase().contains((search.toLowerCase()))) {
						cards.add(card);
					}
				}
				
				return cards;
			}
		}
		
		return this.cards;
	}
	
}
