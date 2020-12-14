package fr.mael.tarot.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import fr.mael.tarot.card.Card;
import fr.mael.tarot.card.CardList;
import fr.mael.tarot.save.SaveProcess;
import fr.mael.tarot.utils.JsonFilter;

public class TarotPanel extends JPanel implements ActionListener, KeyListener{

	private CardList cardList;
	private JToolBar toolBar;
	
	private JButton open;
	private JButton save;
	private JButton add;
	private JButton remove;
	private JFileChooser openFc;
	private JFileChooser saveFc;
	private JTextField searchBar;
	
	private boolean deleteCard = false;
	
	public TarotPanel() {
		super(new BorderLayout());
				
		toolBar = new JToolBar("Still draggable");
		toolBar.setFloatable(false);
		
        openFc = new JFileChooser();
        openFc.addChoosableFileFilter(new JsonFilter());
        openFc.setAcceptAllFileFilterUsed(false);
        openFc.addActionListener(this);
        
        saveFc = new JFileChooser();
        saveFc.addChoosableFileFilter(new JsonFilter());
        saveFc.setAcceptAllFileFilterUsed(false);
        saveFc.addActionListener(this);
		
        open = new JButton();
        open.setText("Open");
        open.addActionListener(this);
        toolBar.add(open);
        
        save = new JButton();
        save.setText("Save");
        save.addActionListener(this);
        toolBar.add(save);
		
        toolBar.addSeparator();
        
        add = new JButton();
        add.setText("Add card");
        add.addActionListener(this);
        toolBar.add(add);
        
        remove = new JButton();
        remove.setText("Remove card");
        remove.addActionListener(this);
        toolBar.add(remove);
        
        toolBar.addSeparator();
                
        JLabel searchLbl = new JLabel("Search: ");
        toolBar.add(searchLbl);
        
        searchBar = new JTextField(20);
        searchBar.addActionListener(this);
        searchBar.addKeyListener(this);
        toolBar.add(searchBar, BorderLayout.EAST);
        
        add(toolBar, BorderLayout.PAGE_START);
        
        this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {
				Card card = getCardFromMouseEvent(e);
				if (card != null) {				
					if (deleteCard) {
						cardList.removeCard(card);
						remove.setForeground(Color.BLACK);
						deleteCard = false;
					}
				}
				repaint();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
	}
	
	public TarotPanel setCardList(CardList cardList) {
		this.cardList = cardList;
		return this;
	}
	
	public CardList getCardList() {
		return this.cardList;
	}
	
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    	    
	    if (cardList != null) {
	    	int nb = 0;
	    	int y = 150;
	    	int x = (int) (y/((1+Math.sqrt(5))/2));
	    	int maxPerLine = this.getWidth() / (x + x/4);
	    	int cardWidth = maxPerLine * (x + x/4);
	    	int borderX = (this.getWidth() - (cardWidth - x/4)) / 2;
	    		    	
		    for (Card card : this.cardList.getCardsBySearch(searchBar.getText())) {
		    	card.draw(g, (nb % maxPerLine) * (x + x/4) + borderX, toolBar.getHeight() + 5 + ((int) (nb / maxPerLine) * (y + 10)), x, y);
		    	nb++;
		    }	
	    }   
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == add) {
			AddCard frame = new AddCard(this, cardList);
			frame.setVisible(true);
		} else if (e.getSource() == remove) {
			deleteCard = !deleteCard;
			if (deleteCard) {
				remove.setForeground(Color.red);
			} else {
				remove.setForeground(Color.BLACK);
			}
		} else if (e.getSource() == open) {
			int response = openFc.showOpenDialog(this);
			if (response == JFileChooser.APPROVE_OPTION) {
				if (openFc.getSelectedFile() != null) {
					SaveProcess save = new SaveProcess(openFc.getSelectedFile());
					CardList list;
					try {
						list = save.deserializeCardList();
						this.setCardList(list);
					} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e2) {
						e2.printStackTrace();
					}
				}
			}
			this.repaint();
		} else if (e.getSource() == save) {
			int response = saveFc.showSaveDialog(this);
			if (response == JFileChooser.APPROVE_OPTION) {
				File file = saveFc.getSelectedFile();
				if (!file.getName().endsWith(".json")) {
					file = new File(file + ".json");
				}
				SaveProcess save = new SaveProcess(file);
				try {
					save.serializeCardList(this.cardList);
				} catch (JsonIOException | IOException e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	
	private Card getCardFromMouseEvent(MouseEvent e) {
	    if (cardList != null) {
	    	int nb = 0;
	    	int y = 150;
	    	int x = (int) (y/((1+Math.sqrt(5))/2));
	    	int maxPerLine = this.getWidth() / (x + x/4);
	    	int cardWidth = maxPerLine * (x + x/4);
	    	int borderX = (this.getWidth() - (cardWidth - x/4)) / 2;
		    for (Card card : this.cardList.getCardsBySearch(searchBar.getText())) {
		    	
		    	if (e.getX() >= (nb % maxPerLine) * (x + x/4) + borderX &&
		    			e.getX() <= ((nb % maxPerLine) * (x + x/4) + borderX) + x &&
		    			e.getY() >= toolBar.getHeight() + 5 + ((int) (nb / maxPerLine) * (y + 10)) &&
		    			e.getY() <= (toolBar.getHeight() + 5 + ((int) (nb / maxPerLine) * (y + 10))) + y) {
		    		return card;
		    	}
		    	nb++;
		    }	
	    } 
	    return null;
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
}
