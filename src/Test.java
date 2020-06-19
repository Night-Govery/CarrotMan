import java.io.File;
import java.net.MalformedURLException;

import GUI.LaunchFrame;
import GUI.Music;
import GUI.mainFrame;
import map.MapReader;

public class Test {
	
	public static void main(String[] args) {
		//用读到的地图数组创建游戏窗体，开始游戏
		LaunchFrame frame = new LaunchFrame();
		frame.setVisible(true);
		
		//播放背景音乐
		Music p=new Music();
		p.play();
	}
}
