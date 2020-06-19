package GUI;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Monster.Monster;
import Player.Attribute;
import Player.Player;
import config.gameConfig;
import map.BulletCreator;
import map.KeyListener;
import map.MapDrawer;
import map.MapReader;

public class mainFrame extends JFrame implements gameConfig{
	//地图框
	JPanel panel;
	//对话框
	JPanel spanel;
	
	//账号相关变量
    String ID;
    String icon;
    String map_name;

	public mainFrame(String ID, String map_name, String icon) {
        
        this.icon = icon;
        this.ID = ID;
        this.map_name = map_name;
        
		init();	
	}
	

	
	/**
	 * 设置窗体
	 */
	public void init(){
		
		this.setTitle(title);
		this.setSize(frameX, frameY);
		this.setDefaultCloseOperation(3);
		this.setLayout(null);
		
		//创建游戏面板
		MapReader.readFile(map_name, 100);
		
		
		panel = new MapDrawer(icon, ID);
		panel.setBounds(0, 0, 750, 750);
		panel.setLayout(null);
		
		//创建对话面板
		spanel = new SidePanel(MapDrawer.player_list.get(0));
		
		
		this.add(panel);
		this.add(spanel);
		this.setVisible(true);
		
		
		//启动刷新面板线程
		UpdateThread ut = new UpdateThread(panel, spanel);
		ut.start();
		
        KeyListener kl1 = new KeyListener(MapDrawer.player_list.get(0));
        this.addKeyListener(kl1);
	}
	
}
