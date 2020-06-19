package GUI;

import javax.swing.JPanel;

public class UpdateThread extends Thread{
	JPanel panel;
	JPanel spanel;
	public UpdateThread(JPanel panel, JPanel spanel) {
		this.panel = panel;
		this.spanel = spanel;
		//this.cpanel = cpanel;
	}
	
	@Override
	public void run() {
		while(true){
			panel.repaint();
			spanel.repaint();
			//cpanel.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
