package Player;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.UpdateThread;
import Monster.Monster;
import config.gameConfig;
import map.Bullet;
import map.MapDrawer;
import map.MapReader;

public class Player extends Thread implements gameConfig{
	//角色的位置
	public int x;
	public int y;
	//展示的角色贴图包
	public  String icon;
	public String ID;
	//角色的步长
	public int step;
	//角色是否移动
	public boolean up = false;
	public boolean down = false;
	public boolean left = false;
	public boolean right = false;
	//角色的当前贴图动画的位置
	public int x1 = 0;
	public int x2 = 0;
	public int y1 = 0;
	public int y2 = 0;
	
	public int playersize;
	//角色的面朝方向,默认朝下
	public int force = 2;
	
	public Attribute a;
	
	public boolean isAlive = true;//玩家状态
	public boolean isShoot = false;//子弹射击间隔
	public boolean isDamage = true;//伤害判断间隔
	
	public int immunityTimer = 100;
	public int immunityCount = 0;
	
	public Player(int x, int y, int step, String icon, String ID, Attribute a, int size) {
		this.x = x;
		this.y = y;
		this.icon = icon;
		this.step = step;
		this.ID = ID;
		this.a = a;
		this.playersize = size;
	}
	
	public Attribute getAttribute() {
		return a;
	}
	
	
	public void createBullet(int size, int speed, int damage) {
		Bullet b= new Bullet(x, y, force, size, speed, damage, ID);
		b.start();
		MapDrawer.addBullet(b);
	}
	
	@Override
	public void run() {
		while(true){
			move();	
			checkAlive();
			if(immunityCount >= immunityTimer ) {
				checkHit();
			}
			try {
				Thread.sleep(10);
				immunityCount++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void getHealth() {
		
	}
	
	public void getDamage(int damage) {
		a.current_Health = a.current_Health - (int)(damage*a.max_Armor*0.01);
	}
	
	
	
	public void move() {
		if(right == true) {
			//没有障碍物
			if(MapReader.map2[(x+step+playersize)/50][y/50]==0&&MapReader.map2[(x+step+playersize)/50][(y+playersize)/50]==0) {
				x+=step;
			}
			if(MapDrawer.monster_list.size() == 0) {
				if(MapReader.map2[(x+playersize+step)/50][y/50]>=100&&MapReader.map2[(x+step+playersize)/50][(y+playersize)/50]>=100) {
					MapReader.readFile(MapReader.name, MapReader.map2[(x+playersize+step)/50][y/50]);
					MapDrawer.count_map = 0;
				}
			}
			force = 4;
      	}	
		if(left == true) {
			//没有障碍物
			if(MapReader.map2[(x-step)/50][y/50]==0&&MapReader.map2[(x-step)/50][(y+playersize)/50]==0) {
				x-=step;
			}
			if(MapDrawer.monster_list.size() == 0) {
				if(MapReader.map2[(x-step)/50][y/50]>=100&&MapReader.map2[(x-step)/50][(y+playersize)/50]>=100) {
					MapReader.readFile(MapReader.name, MapReader.map2[(x-step)/50][y/50]);
					MapDrawer.count_map = 0;
				}
			}
			force = 3;
      	}	
		if(down == true) {
			//没有障碍物
			if(MapReader.map2[x/50][(y+playersize+step)/50]==0&&MapReader.map2[(x+playersize)/50][(y+playersize+step)/50]==0) {
				y+=step;
			}
			if(MapDrawer.monster_list.size() == 0) {
				if(MapReader.map2[x/50][(y+playersize+step)/50]>=100&&MapReader.map2[(x+playersize)/50][(y+playersize+step)/50]>=100) {
					MapReader.readFile(MapReader.name, MapReader.map2[x/50][(y+playersize+step)/50]);
					MapDrawer.count_map = 0;
				}
			}
			force = 2;
      	}	
		if(up == true) {
			//没有障碍物
			if(MapReader.map2[x/50][(y-step)/50]==0&&MapReader.map2[(x+playersize)/50][(y-step)/50]==0) {
				y-=step;
			}
			if(MapDrawer.monster_list.size() == 0) {
				if(MapReader.map2[x/50][(y-step)/50]>=100&&MapReader.map2[(x+playersize)/50][(y-step)/50]>=100) {
					MapReader.readFile(MapReader.name, MapReader.map2[(x)/50][(y-step)/50]);
					MapDrawer.count_map = 0;
				}
			}
			force = 1;
      	}
	}
	
	
	/**
	* 检测两个矩形是否碰撞
	* @return
	*/
	public boolean isCollsionWithRect(int x1, int y1, int w1, int h1, int x2,int y2, int w2, int h2) {
		if (x1 >= x2 && x1 >= x2 + w2) {
			return false;
		} else if (x1 <= x2 && x1 + w1 <= x2) {
			return false;
		} else if (y1 >= y2 && y1 >= y2 + h2) {
			return false;
		} else if (y1 <= y2 && y1 + h1 <= y2) {
			return false;
		}
			return true;
	}
	
	public void checkHit() {
		for(int i=0;i<MapDrawer.monster_list.size();i++) {
			Monster m = MapDrawer.monster_list.get(i);
			if(isCollsionWithRect(m.x, m.y, m.size, m.size, x, y, playersize, playersize)) {
				getDamage(100);
				immunityCount = 0;
			}
		}
	}
	
	public void checkAlive() {
		if(a.current_Health<=0) {
			isAlive = false;
		}
	}
	
}
