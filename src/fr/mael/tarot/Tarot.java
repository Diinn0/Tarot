package fr.mael.tarot;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFrame;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import fr.mael.tarot.card.CardList;
import fr.mael.tarot.component.TarotPanel;
import fr.mael.tarot.save.SaveProcess;

public class Tarot {

	public static void main(String[] args) {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		JFrame window = new JFrame("Tarot");	
		window.setBounds(screenSize.width / 2 / 3, screenSize.height / 2 / 3, (int) (screenSize.width / 1.5), (int) (screenSize.height / 1.5));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TarotPanel centerPanel = new TarotPanel();
		centerPanel.setSize(window.getSize());
        window.getContentPane().add(centerPanel, BorderLayout.CENTER);
		window.setVisible(true);
		
	}

}
