package config;

import javax.swing.ImageIcon;

public interface gameConfig {
	//��Ϸ����������
	String title = "�ܲ�����";
	//��Ϸ������Ĵ�С
	int frameX = 1200;
	int frameY = 1000;
	//��ͼ����С
	int panelX = 1000;
	int panelY = 1000;
	//��Ϸ�زĴ�С
	int elesize = 50;
	//------------[��ͼ�ز�]----------
	//-----��һ��
	ImageIcon map1_icon_0 = new ImageIcon("resourse/map1/000.png");
	ImageIcon map1_icon_1 = new ImageIcon("resourse/map1/001.png");
	//-----�ڶ���
	ImageIcon map2_icon_0 = new ImageIcon("resourse/map2/000.png");
	ImageIcon map2_icon_1 = new ImageIcon("resourse/map2/001.png");
	ImageIcon map2_icon_2 = new ImageIcon("resourse/map2/002.png");
	
	ImageIcon map2_icon_100 = new ImageIcon("resourse/map2/100.png");
	ImageIcon map2_icon_101 = new ImageIcon("resourse/map2/101.png");
	//-----������
	ImageIcon map3_icon_0 = new ImageIcon("resourse/map3/000.png");
	ImageIcon map3_icon_1 = new ImageIcon("resourse/map3/001.png");
	
	ImageIcon background = new ImageIcon("resourse/gui/background.png");
	ImageIcon equipment = new ImageIcon("resourse/gui/equipment.png");
	ImageIcon weapon = new ImageIcon("resourse/gui/weapon.png");
	ImageIcon item = new ImageIcon("resourse/gui/item.png");
	
	
	
}