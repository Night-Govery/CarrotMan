package map;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
 
import javax.swing.ImageIcon;

import GUI.mainFrame;
import Player.Player;

 
 
//键盘按下监听类
public class KeyListener extends KeyAdapter{
	
	public Player p;
	
	public KeyListener(Player p) {
		this.p=p;
	}
	
	//键盘监听
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		switch(code){
		//向右走
		case 68:
			p.right=true;
			break;
			//向左走
		case 65:
			p.left=true;
		break;
		case 83:
			p.down=true;
			break;
		//向上走
		case 87:
			p.up=true;
			break;					
		case 74:
			p.isShoot = true;
			break;	
		}				
	}

 
	//键盘释放监听
	public void keyReleased(KeyEvent e) {
		
		int code=e.getKeyCode();
		if(code==68){			
			p.right=false;			
		}
		if(code==65){
			p.left=false;
		}
		
		if(code==87){
			p.up=false;
		}
		
		if(code==83){
			p.down=false;
		}
	}
 
}
