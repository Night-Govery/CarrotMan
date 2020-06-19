package config;

import javax.swing.ImageIcon;

public interface gameConfig {
	//游戏主窗体名字
	String title = "萝卜勇者";
	//游戏主窗体的大小
	int frameX = 1200;
	int frameY = 1000;
	//地图面板大小
	int panelX = 1000;
	int panelY = 1000;
	//游戏素材大小
	int elesize = 50;
	//------------[地图素材]----------
	//-----第一层
	ImageIcon map1_icon_0 = new ImageIcon("resourse/map1/000.png");
	ImageIcon map1_icon_1 = new ImageIcon("resourse/map1/001.png");
	//-----第二层
	ImageIcon map2_icon_0 = new ImageIcon("resourse/map2/000.png");
	ImageIcon map2_icon_1 = new ImageIcon("resourse/map2/001.png");
	ImageIcon map2_icon_2 = new ImageIcon("resourse/map2/002.png");
	
	ImageIcon map2_icon_100 = new ImageIcon("resourse/map2/100.png");
	ImageIcon map2_icon_101 = new ImageIcon("resourse/map2/101.png");
	//-----第三层
	ImageIcon map3_icon_0 = new ImageIcon("resourse/map3/000.png");
	ImageIcon map3_icon_1 = new ImageIcon("resourse/map3/001.png");
	
	ImageIcon background = new ImageIcon("resourse/gui/background.png");
	ImageIcon equipment = new ImageIcon("resourse/gui/equipment.png");
	ImageIcon weapon = new ImageIcon("resourse/gui/weapon.png");
	ImageIcon item = new ImageIcon("resourse/gui/item.png");
	
	
	
}