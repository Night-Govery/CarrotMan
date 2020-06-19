package map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Monster.Monster;
import Player.Attribute;
import Player.Player;
import config.gameConfig;

public class MapDrawer extends JPanel implements gameConfig{
	
	public static ArrayList<Player> player_list = new ArrayList<Player>(100);
	public static ArrayList<Bullet> bullet_list_player = new ArrayList<Bullet>(100);
	public static ArrayList<Monster> monster_list = new ArrayList<Monster>(100);
	public static int count_map = 0;
	 
	public static void addPlayer(Player p) {
		player_list.add(p);
	}
	
	public static void deletePlayer(Player p) {
		player_list.remove(p);
	}
	
	public static void addMonster(Monster m) {
		monster_list.add(m);
	}
	
	public static void deleteMonster(Monster m) {
		monster_list.remove(m);
	}
	
	public static void addBullet(Bullet b) {
		bullet_list_player.add(b);
	}
	
	public static void deleteBullet(Bullet b) {
		bullet_list_player.remove(b);
	}
	
	public static void changePlayer(String ID, boolean up1, boolean down1, boolean right1, boolean left1) {
		for(int i=0;i<player_list.size();i++){
			if(player_list.get(i).equals(ID)) {
				player_list.get(i).up = up1;
				player_list.get(i).down = down1;
				player_list.get(i).right = right1;
				player_list.get(i).left = left1;
			}
		}
	}
	
	
	public void crateMonster() {
		for(int i=0;i<15;i++) {
			for(int j=0;j<15;j++) {
				//if() {
					
				//}
			}
		}
	}
	
	public MapDrawer(String icon, String ID) {
		if(count_map == 0) {
			player_list.clear();
			bullet_list_player.clear();
			monster_list.clear();
			
			for(int i=0;i<15;i++) {
				for(int j=0;j<15;j++) {
					//角色生成器
					if(MapReader.map3[i][j]==999) {
						//启动人物移动线程
						Attribute a= new Attribute(100, 10, 10, 76, 20, "传说中的勇者", 200);
						Player player = new Player(i*elesize, j*elesize,2,icon, ID, a, 30);
						player.start();
						
						BulletCreator bc = new BulletCreator(player);
						bc.start();
						
						MapDrawer.addPlayer(player);	
					}
					//怪物生成器
					else if(MapReader.map3[i][j]>0&&MapReader.map3[i][j]<999) {
						Attribute m= new Attribute(100, 10, 10, 76, 20, "传说中的勇者", 200);
						//启动怪物移动线程
						Monster monster = new Monster(i*elesize, j*elesize,2,"carrot", m, 60);
						monster.start();
						
						MapDrawer.addMonster(monster);
						System.out.print("怪物类型："+MapReader.map3[i][j]+"生成成功！");
					}
				}
			}
			count_map++;
		}
	}
	
	
	@Override
	public void paint(Graphics src) {
	    
		

		Image iBuffer = null;
		Graphics g = null;

	    if(iBuffer==null)
	    {
	       iBuffer=createImage(this.getSize().width,this.getSize().height);
	       g=iBuffer.getGraphics();
	    }

		
		if(count_map == 0) {
			bullet_list_player.clear();
			monster_list.clear();
			
			for(int i=0;i<15;i++) {
				for(int j=0;j<15;j++) {
					if(MapReader.map3[i][j]==999) {
						//重置玩家角色坐标
						for(int k=0;k<player_list.size();k++) {
							Player p = player_list.get(k);
							p.x = i*elesize;
							p.y = j*elesize;
						}
					}
					//怪物生成器
					else if(MapReader.map3[i][j]>0&&MapReader.map3[i][j]<999) {
						Attribute m= new Attribute(100, 10, 10, 76, 20, "传说中的勇者", 200);
						//启动怪物移动线程
						Monster monster = new Monster(i*elesize, j*elesize,2,"carrot", m, 60);
						monster.start();
						
						MapDrawer.addMonster(monster);
						System.out.print("怪物类型："+MapReader.map3[i][j]+"生成成功！\n");
					}
				}
			}
			count_map++;
		}
		
		//地图生成器
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				//画第一层元素
				ImageIcon icon = GetMap1.int2icon(MapReader.map1[i][j]);
				g.drawImage(icon.getImage(), i*elesize, j*elesize, elesize, elesize, null);
				//第二层即障碍物
				ImageIcon icon2 = GetMap2.int2icon(MapReader.map2[i][j]);
				g.drawImage(icon2.getImage(), i*elesize, j*elesize, elesize, elesize, null);
			}
		}
		
		//画角色
		for(int i=0;i<player_list.size();i++) {
			Player p = player_list.get(i);
			//画角色
			if(p.isAlive) {
				if(p.right == true) {
		            if(p.x2<=40) {
		            	if(p.x2<=10) {
		            		g.drawImage((new ImageIcon("resourse/player/"+p.icon+"/right1.png")).getImage(), p.x, p.y, p.playersize, p.playersize, null);
		            	}
		            	else if(p.x2>10&&p.x2<=20) {
		            		g.drawImage((new ImageIcon("resourse/player/"+p.icon+"/right2.png")).getImage(), p.x, p.y, p.playersize, p.playersize, null);
		            	}
		            	else if(p.x2>20&&p.x2<=30) {
		            		g.drawImage((new ImageIcon("resourse/player/"+p.icon+"/right3.png")).getImage(), p.x, p.y, p.playersize, p.playersize, null);
		            	}
		            	else if(p.x2>30&&p.x2<=40) {
		            		g.drawImage((new ImageIcon("resourse/player/"+p.icon+"/right4.png")).getImage(), p.x, p.y, p.playersize, p.playersize, null);
		            	}
		            	p.x2++;
		            }
		            else {
		            	p.x2=0;
		            }
		      	}	
				else if(p.left == true) {
		            if(p.x1<=40) {
		            	if(p.x1<=10) {
		            		g.drawImage((new ImageIcon("resourse/player/"+p.icon+"/left1.png")).getImage(), p.x, p.y, p.playersize, p.playersize, null);
		            	}
		            	else if(p.x1>10&&p.x1<=20) {
		            		g.drawImage((new ImageIcon("resourse/player/"+p.icon+"/left2.png")).getImage(), p.x, p.y, p.playersize, p.playersize, null);
		            	}
		            	else if(p.x1>20&&p.x1<=30) {
		            		g.drawImage((new ImageIcon("resourse/player/"+p.icon+"/left3.png")).getImage(), p.x, p.y, p.playersize, p.playersize, null);
		            	}
		            	else if(p.x1>30&&p.x1<=40) {
		            		g.drawImage((new ImageIcon("resourse/player/"+p.icon+"/left4.png")).getImage(), p.x, p.y, p.playersize, p.playersize, null);
		            	}
		            	p.x1++;
		            }
		            else {
		            	p.x1=0;
		            }
		      	}	
				else if(p.down == true) {
		            if(p.y1<=40) {
		            	if(p.y1<=10) {
		            		g.drawImage((new ImageIcon("resourse/player/"+p.icon+"/down1.png")).getImage(), p.x, p.y, p.playersize, p.playersize, null);
		            	}
		            	else if(p.y1>10&&p.y1<=20) {
		            		g.drawImage((new ImageIcon("resourse/player/"+p.icon+"/down2.png")).getImage(),p.x, p.y, p.playersize, p.playersize, null);
		            	}
		            	else if(p.y1>20&&p.y1<=30) {
		            		g.drawImage((new ImageIcon("resourse/player/"+p.icon+"/down3.png")).getImage(), p.x, p.y, p.playersize, p.playersize, null);
		            	}
		            	else if(p.y1>30&&p.y1<=40) {
		            		g.drawImage((new ImageIcon("resourse/player/"+p.icon+"/down4.png")).getImage(), p.x, p.y, p.playersize, p.playersize, null);
		            	}
		            	p.y1++;
		            }
		            else {
		            	p.y1=0;
		            }
		      	}	
				else if(p.up == true) {
		            if(p.y2<=40) {
		            	if(p.y2<=10) {
		            		g.drawImage((new ImageIcon("resourse/player/"+p.icon+"/up1.png")).getImage(), p.x, p.y, p.playersize, p.playersize, null);
		            	}
		            	else if(p.y2>10&&p.y2<=20) {
		            		g.drawImage((new ImageIcon("resourse/player/"+p.icon+"/up2.png")).getImage(), p.x, p.y, p.playersize, p.playersize, null);
		            	}
		            	else if(p.y2>20&&p.y2<=30) {
		            		g.drawImage((new ImageIcon("resourse/player/"+p.icon+"/up3.png")).getImage(), p.x, p.y, p.playersize, p.playersize, null);
		            	}
		            	else if(p.y2>30&&p.y2<=40) {
		            		g.drawImage((new ImageIcon("resourse/player/"+p.icon+"/up4.png")).getImage(), p.x, p.y, p.playersize, p.playersize, null);
		            	}
		            	p.y2++;
		            }
		            else {
		            	p.y2=0;
		            }
		      	}
				else {
					switch(p.force) {
					case 1:
			    		g.drawImage((new ImageIcon("resourse/player/"+p.icon+"/up1.png")).getImage(), p.x, p.y, p.playersize, p.playersize, null);
						break;
					case 2:
			    		g.drawImage((new ImageIcon("resourse/player/"+p.icon+"/down1.png")).getImage(), p.x, p.y, p.playersize, p.playersize, null);
						break;
					case 3:
			    		g.drawImage((new ImageIcon("resourse/player/"+p.icon+"/left1.png")).getImage(), p.x, p.y, p.playersize, p.playersize, null);
						break;
					case 4:
			    		g.drawImage((new ImageIcon("resourse/player/"+p.icon+"/right1.png")).getImage(), p.x, p.y, p.playersize, p.playersize, null);
						break;
					}
				}
			}
			else {
				deletePlayer(p);
				System.out.println("玩家    "+p.ID+"  已阵亡");
			}
		}
		
		
		//画子弹
		for(int i=0;i<bullet_list_player.size();i++) {
			Bullet b = bullet_list_player.get(i);
			if(b.isAlive) {
	    		g.drawImage((new ImageIcon("resourse/bullet/001.png")).getImage(), b.x, b.y, b.size, b.size, null);
			}
			else {
				deleteBullet(b);
			}
		}
		
		//画怪物
		for(int i=0;i<monster_list.size();i++) {
			Monster p = monster_list.get(i);
			if(p.isAlive) {
				if(p.right == true) {
		            if(p.x2<=40) {
		            	if(p.x2<=10) {
		            		g.drawImage((new ImageIcon("resourse/monster/"+p.icon+"/right1.png")).getImage(), p.x, p.y, p.size, p.size, null);
		            	}
		            	else if(p.x2>10&&p.x2<=20) {
		            		g.drawImage((new ImageIcon("resourse/monster/"+p.icon+"/right2.png")).getImage(), p.x, p.y, p.size, p.size, null);
		            	}
		            	else if(p.x2>20&&p.x2<=30) {
		            		g.drawImage((new ImageIcon("resourse/monster/"+p.icon+"/right3.png")).getImage(), p.x, p.y, p.size, p.size, null);
		            	}
		            	else if(p.x2>30&&p.x2<=40) {
		            		g.drawImage((new ImageIcon("resourse/monster/"+p.icon+"/right4.png")).getImage(), p.x, p.y, p.size, p.size, null);
		            	}
		            	p.x2++;
		            }
		            else {
		            	p.x2=0;
		            }
		      	}	
				else if(p.left == true) {
		            if(p.x1<=40) {
		            	if(p.x1<=10) {
		            		g.drawImage((new ImageIcon("resourse/monster/"+p.icon+"/left1.png")).getImage(), p.x, p.y, p.size, p.size, null);
		            	}
		            	else if(p.x1>10&&p.x1<=20) {
		            		g.drawImage((new ImageIcon("resourse/monster/"+p.icon+"/left2.png")).getImage(), p.x, p.y, p.size, p.size, null);
		            	}
		            	else if(p.x1>20&&p.x1<=30) {
		            		g.drawImage((new ImageIcon("resourse/monster/"+p.icon+"/left3.png")).getImage(), p.x, p.y, p.size, p.size, null);
		            	}
		            	else if(p.x1>30&&p.x1<=40) {
		            		g.drawImage((new ImageIcon("resourse/monster/"+p.icon+"/left4.png")).getImage(), p.x, p.y, p.size, p.size, null);
		            	}
		            	p.x1++;
		            }
		            else {
		            	p.x1=0;
		            }
		      	}	
				else if(p.down == true) {
		            if(p.y1<=40) {
		            	if(p.y1<=10) {
		            		g.drawImage((new ImageIcon("resourse/monster/"+p.icon+"/down1.png")).getImage(), p.x, p.y, p.size, p.size, null);
		            	}
		            	else if(p.y1>10&&p.y1<=20) {
		            		g.drawImage((new ImageIcon("resourse/monster/"+p.icon+"/down2.png")).getImage(),p.x, p.y, p.size, p.size, null);
		            	}
		            	else if(p.y1>20&&p.y1<=30) {
		            		g.drawImage((new ImageIcon("resourse/monster/"+p.icon+"/down3.png")).getImage(), p.x, p.y, p.size, p.size, null);
		            	}
		            	else if(p.y1>30&&p.y1<=40) {
		            		g.drawImage((new ImageIcon("resourse/monster/"+p.icon+"/down4.png")).getImage(), p.x, p.y, p.size, p.size, null);
		            	}
		            	p.y1++;
		            }
		            else {
		            	p.y1=0;
		            }
		      	}	
				else if(p.up == true) {
		            if(p.y2<=40) {
		            	if(p.y2<=10) {
		            		g.drawImage((new ImageIcon("resourse/monster/"+p.icon+"/up1.png")).getImage(), p.x, p.y, p.size, p.size, null);
		            	}
		            	else if(p.y2>10&&p.y2<=20) {
		            		g.drawImage((new ImageIcon("resourse/monster/"+p.icon+"/up2.png")).getImage(), p.x, p.y, p.size, p.size, null);
		            	}
		            	else if(p.y2>20&&p.y2<=30) {
		            		g.drawImage((new ImageIcon("resourse/monster/"+p.icon+"/up3.png")).getImage(), p.x, p.y, p.size, p.size, null);
		            	}
		            	else if(p.y2>30&&p.y2<=40) {
		            		g.drawImage((new ImageIcon("resourse/monster/"+p.icon+"/up4.png")).getImage(), p.x, p.y, p.size, p.size, null);
		            	}
		            	p.y2++;
		            }
		            else {
		            	p.y2=0;
		            }
		      	}
				else {
					switch(p.force) {
					case 1:
			    		g.drawImage((new ImageIcon("resourse/monster/"+p.icon+"/up1.png")).getImage(), p.x, p.y, p.size, p.size, null);
						break;
					case 2:
			    		g.drawImage((new ImageIcon("resourse/monster/"+p.icon+"/down1.png")).getImage(), p.x, p.y, p.size, p.size, null);
						break;
					case 3:
			    		g.drawImage((new ImageIcon("resourse/monster/"+p.icon+"/left1.png")).getImage(), p.x, p.y, p.size, p.size, null);
						break;
					case 4:
			    		g.drawImage((new ImageIcon("resourse/monster/"+p.icon+"/right1.png")).getImage(), p.x, p.y, p.size, p.size, null);
						break;
					}
				}
				//画血条				
				g.setColor(Color.RED);
				g.drawRect(p.x, (p.y-5), p.size, 5);
				g.fillRect(p.x, (p.y-5), p.a.current_Health*p.size/p.a.max_Health, 5);
			}
			else {
				deleteMonster(p);
			}
		}
		//玩家死亡
		if(player_list.size() == 0) {
			g.drawImage((new ImageIcon("resourse/other/lose.jpg")).getImage(), 300, 300, 100, 100, null);
		}
		
	    src.drawImage(iBuffer,0,0,this);
	}	
}
