package map;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
 
import javax.swing.ImageIcon;

import GUI.mainFrame;
import Player.Player;

 
 
//���̰��¼�����
public class KeyListener extends KeyAdapter{
	
	public Player p;
	
	public KeyListener(Player p) {
		this.p=p;
	}
	
	//���̼���
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		switch(code){
		//������
		case 68:
			p.right=true;
			break;
			//������
		case 65:
			p.left=true;
		break;
		case 83:
			p.down=true;
			break;
		//������
		case 87:
			p.up=true;
			break;					
		case 74:
			p.isShoot = true;
			break;	
		}				
	}

 
	//�����ͷż���
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
