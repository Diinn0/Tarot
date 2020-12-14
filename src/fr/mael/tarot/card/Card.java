package fr.mael.tarot.card;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;

import fr.mael.tarot.utils.Utils;

/**
 * This class represent a Card
 * @author Mael
 *
 */
public class Card {

	private int id;
	private String name;
	private String lore;
	private BufferedImage image;
	
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
	
	public Card setImage(BufferedImage image) {
		this.image = image;
		return this;
	}
	
	public BufferedImage getImage() {
		return this.image;
	}
	
	/**
	 * This method draw Card in JPanel using Graphics
	 * @param g 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void draw(Graphics g, int x, int y, int width, int height) {
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);
		g.drawImage(this.image, x, y, width, height - 20, null);
		String title = Utils.integerToRoman(this.id) + " - " + this.name;
		int titleWidth = g.getFontMetrics().stringWidth(title);
		g.drawString(title, x + (width - titleWidth) / 2, y + height - 5);
	}
	
}
