package fr.mael.tarot.card;

import java.awt.Image;

public class Card {

	private int id;
	private String name;
	private String lore;
	private Image image;
	
	public Card(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getLore() {
		return this.lore;
	}
	
	public Card setLore(String lore) {
		this.lore = lore;
		return this;
	}
	
	public Card setImage(Image image) {
		this.image = image;
		return this;
	}
	
	public Image getImage() {
		return this.image;
	}
	
}
