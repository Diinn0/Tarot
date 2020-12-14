package fr.mael.tarot.component;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.plaf.FileChooserUI;

import fr.mael.tarot.card.Card;
import fr.mael.tarot.card.CardList;
import fr.mael.tarot.utils.ImageFilter;
import fr.mael.tarot.utils.SpringUtilities;

public class AddCard extends JFrame implements ActionListener {
   
	private JPanel panel;
	private JButton imgBtn;
	private JFileChooser imgfc;
	private JButton validBtn;
	private JButton cancelBtn;
	private JTextField numberTxt;
	private JTextField nameTxt;
	private JTextField loreTxt;
	
	private CardList cardList;
	private TarotPanel tarotPanel;
	
	public AddCard(TarotPanel tarotPanel, CardList cardList) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.tarotPanel = tarotPanel;
		this.cardList = cardList;
		
		this.setTitle("Add Card");
		this.setBounds(screenSize.width / 2 / 3, screenSize.height / 2 / 3, 400, 200);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		panel = new JPanel(new SpringLayout());
		panel.setSize(this.getSize());		
		 
		JLabel numberLbl = new JLabel("Number*:", JLabel.TRAILING);
		panel.add(numberLbl);
		
		numberTxt = new JTextField(20);
		numberLbl.setLabelFor(numberTxt);
		panel.add(numberTxt);
		
		JLabel nameLbl = new JLabel("Name*:", JLabel.TRAILING);
		panel.add(nameLbl);
		
		nameTxt = new JTextField(20);
		nameLbl.setLabelFor(nameTxt);
		panel.add(nameTxt);
		
		JLabel loreLbl = new JLabel("Lore:", JLabel.TRAILING);
		panel.add(loreLbl);
		
		loreTxt = new JTextField(100);
		loreLbl.setLabelFor(loreTxt);
		panel.add(loreTxt);
		
		JLabel imgLbl = new JLabel("Image:", JLabel.TRAILING);
		panel.add(imgLbl);
				
		imgfc = new JFileChooser();
		imgfc.addChoosableFileFilter(new ImageFilter());
		imgfc.setAcceptAllFileFilterUsed(false);
		
		imgBtn = new JButton("Search");
		imgLbl.setLabelFor(imgBtn);
		imgBtn.addActionListener(this);
		panel.add(imgBtn);
		
		cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(this);
		panel.add(cancelBtn);
		
		validBtn = new JButton("Add card");
		validBtn.addActionListener(this);
		panel.add(validBtn);
				
        SpringUtilities.makeCompactGrid(panel, panel.getComponentCount() / 2, 2, 6, 6, 6, 6);        
		this.getContentPane().add(panel, BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == imgBtn) {
			imgfc.showOpenDialog(panel);
			
		} else if (e.getSource() == cancelBtn) {
			this.dispose();
		} else if (e.getSource() == validBtn) {
			try {
				int number = Integer.parseInt(numberTxt.getText());
				String name = nameTxt.getText();
				String lore = loreTxt.getText();
				if (number != 0 && name != null) {
					if (this.cardList.getCardsBySearch(String.valueOf(number)).isEmpty()) {
						Card card = new Card(number, name);					
						if(imgfc.getSelectedFile() != null) {
							BufferedImage image = null;
							try {
								image = ImageIO.read(imgfc.getSelectedFile());
							} catch (IOException e2) {
								e2.printStackTrace();
							}
							card.setImage(image);	
						}
						card.setLore(lore);
						this.cardList.addCard(card);
						this.tarotPanel.repaint();
						this.dispose();
					}
				}
			} catch (NumberFormatException e2) {
				
			}
		}
		
	}
	
}