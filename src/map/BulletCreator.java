package map;

import Player.Player;

public class BulletCreator extends Thread{
	
	Player p;
	
	
	public void run() {
		while(true) {
			try {
				if(p!=null) {
			        if(p.isShoot) {
						p.createBullet(5, 5, 15);
						p.isShoot = false;
			        }
			        Thread.sleep(p.getAttribute().shootTime); //������ͣ��ʱ��1 ��
				}else {
					break;
				}
		    } catch (InterruptedException e1) {
		        e1.printStackTrace();
		    }
		}
	}
	
	public BulletCreator(Player p) {
		this.p = p;
	}
	
}
