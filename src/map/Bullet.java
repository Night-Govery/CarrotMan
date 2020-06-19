package map;

import Monster.Monster;

public class Bullet extends Thread{

	public int x;
	public int y;
	public int force;
	public int size;
	public int speed;
	public int damage;
	String ID;
	boolean isAlive = true;
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			move();	
			checkHit();
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
	
	public Bullet(int x, int y, int force, int size, int speed, int damage, String ID) {
		this.x = x;
		this.y = y;
		this.force = force;
		this.damage = damage;
		this.size = size;
		this.ID = ID;
		this.speed = speed;
		
	}
	
	
	public void move() {
		switch(force) {
		case 1:
			if(MapReader.map2[x/50][(y-speed)/50]==0&&MapReader.map2[(x+size)/50][(y-speed)/50]==0&&MapReader.map3[x/50][(y-speed)/50]==0) {
				y-=speed;
			}else {
				isAlive = false;
			}
			break;
		case 2:
			if(MapReader.map2[x/50][(y+size+speed)/50]==0&&MapReader.map2[(x+size)/50][(y+size+speed)/50]==0) {
				y+=speed;
			}else {
				isAlive = false;
			}
			break;
		case 3:
			if(MapReader.map2[(x-speed)/50][y/50]==0&&MapReader.map2[(x-speed)/50][(y+size)/50]==0) {
				x-=speed;
			}else {
				isAlive = false;
			}
			break;
		case 4:
			if(MapReader.map2[(x+speed+size)/50][y/50]==0&&MapReader.map2[(x+speed+size)/50][(y+size)/50]==0) {
				x+=speed;
			}else {
				isAlive = false;
			}
			break;
		}
	}
	
	/**
	* ¼ì²âÁ½¸ö¾ØĞÎÊÇ·ñÅö×²
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
			if(isCollsionWithRect(m.x, m.y, m.size, m.size, x, y, size, size)) {
				m.a.current_Health -= damage;
				this.isAlive = false;
			}
		}
	}
	
	
}
