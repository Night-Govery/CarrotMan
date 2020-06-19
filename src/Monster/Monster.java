package Monster;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.UpdateThread;
import Player.Attribute;
import config.gameConfig;
import map.Bullet;
import map.MapDrawer;
import map.MapReader;

public class Monster extends Thread implements gameConfig{
	//怪物的位置
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
	
	public int size;
	//角色的面朝方向,默认朝下
	public int force = 2;
	
	public Attribute a;
	
	public boolean isAlive = true;//玩家状态
	public boolean isShoot = false;//子弹射击间隔
	public boolean isDamage = true;//伤害判断间隔
	
	public int count_step = 200;//用于记录怪物行动的步数，防止高频率颤抖
	
	public Monster(int x, int y, int step, String icon, Attribute a, int size) {
		this.x = x;
		this.y = y;
		this.icon = icon;
		this.step = step;
		this.ID = ID;
		this.a = a;
		this.size = size;
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
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(!isAlive) {
				break;
			}
		}
	}
	
	
	
	
	public void move() {
		if(count_step>=200) {
			int	number = (int)(Math.random()*130)+1;
			if(number>=1&&number<=25) {
				right = true;
				up = false;
				down = false;
				left = false;
				count_step = 0;
			}else if(number>25&&number<=50) {
				left = true;
				right = false;
				up = false;
				down = false;
				count_step = 0;
			}else if(number>50&&number<=75) {
				up = true;
				left = false;
				right = false;
				down = false;
				count_step = 0;
			}else if(number>75&&number<=100) {
				down = true;
				left = false;
				right = false;
				up = false;
				count_step = 0;
			}else {
				down = false;
				left = false;
				right = false;
				up = false;
				count_step = 0;
			}
		}
		
		if(right == true) {
			//没有障碍物
			if(MapReader.map2[(x+step+size)/50][y/50]==0&&MapReader.map2[(x+step+size)/50][(y+size)/50]==0) {
				x+=step;
				count_step++;
			}
			else {
				count_step=200;
			}
			force = 4;
      	}	
		if(left == true) {
			//没有障碍物
			if(MapReader.map2[(x-step)/50][y/50]==0&&MapReader.map2[(x-step)/50][(y+size)/50]==0) {
				x-=step;
				count_step++;
			}
			else {
				count_step=200;
			}
			force = 3;
      	}	
		if(down == true) {
			//没有障碍物
			if(MapReader.map2[x/50][(y+size+step)/50]==0&&MapReader.map2[(x+size)/50][(y+size+step)/50]==0) {
				y+=step;
				count_step++;
			}
			else {
				count_step=200;
			}
			force = 2;
      	}	
		if(up == true) {
			//没有障碍物
			if(MapReader.map2[x/50][(y-step)/50]==0&&MapReader.map2[(x+size)/50][(y-step)/50]==0) {
				y-=step;
				count_step++;
			}
			else {
				count_step=200;
			}
			force = 1;
      	}
		count_step++;
	}
	
	
	public void checkAlive() {
		if(a.current_Health<=0) {
			isAlive = false;
		}
	}
	
}

