package GUI;

import static config.gameConfig.background;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import Player.Player;
import config.gameConfig;

public class SidePanel extends JPanel implements gameConfig{
	
	Player p;
	
	public SidePanel(Player p) {
		this.p = p;
		init();
	}
	
	public void init(){
		this.setBounds(750, 0, 450, 1000);
		this.setLayout(null);
		this.setOpaque(false);//�������͸��
	}
	
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//if(npc!=null){
			g.drawImage(background.getImage(), 0, 0, 450, 200, null);
			g.drawImage(equipment.getImage(), 0, 200, 200, 250, null);
			g.drawImage(item.getImage(), 0, 450, 400, 120, null);
			//g.drawImage(weapon.getImage(), 50, 400, 50, 50, null);
			Font font = new Font("����", 600, 20);
			g.setFont(font);
			g.drawString("ð��������:", 35, 30);
			g.drawString("��    ��:", 50, 60);
			g.drawString("��    ��:", 50, 90);
			g.drawString("Ѫ    ��:", 50, 120);
			g.drawString("������:", 50, 150);
			g.drawString("ħ������:", 250, 60);
			g.drawString("�������:", 250, 90);
			g.drawString("ħ������:", 250, 120);
			g.drawString(p.getAttribute().name, 145, 30);
			g.drawString(String.valueOf(p.getAttribute().level), 150, 60);
			g.drawString(String.valueOf(p.getAttribute().exp), 150, 90);
			g.drawString(String.valueOf(p.getAttribute().current_Health)+"/"+String.valueOf(p.getAttribute().max_Health), 150, 120);
			g.drawString(String.valueOf(p.getAttribute().max_Damage), 150, 150);
			g.drawString("75", 350, 120);
			g.drawString(String.valueOf(p.getAttribute().max_Armor), 350, 90);
			g.drawString("75", 350, 120);
			//g.setColor(Color.GREEN);
		//}
	}
	
	
}

