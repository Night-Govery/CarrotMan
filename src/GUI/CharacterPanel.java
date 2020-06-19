package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import config.gameConfig;

public class CharacterPanel extends JPanel implements gameConfig{
	
	
	public CharacterPanel() {
		init();
	}
	
	public void init(){
		this.setBounds(750, 150, 420, 50);
		this.setLayout(null);
		this.setOpaque(false);//设置面板透明
		
	    
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//if(npc!=null){
			g.drawImage(background.getImage(), 0, 0, 630, 130, null);
			g.setColor(Color.BLUE);
			Font font = new Font("黑体", 600, 25);
			g.setFont(font);
			g.drawString("123", 30, 30);
			g.setColor(Color.GREEN);
			g.drawString("hahahah", 60, 65);
		//}
	}
}
