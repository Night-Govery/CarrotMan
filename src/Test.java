import java.io.File;
import java.net.MalformedURLException;

import GUI.LaunchFrame;
import GUI.Music;
import GUI.mainFrame;
import map.MapReader;

public class Test {
	
	public static void main(String[] args) {
		//�ö����ĵ�ͼ���鴴����Ϸ���壬��ʼ��Ϸ
		LaunchFrame frame = new LaunchFrame();
		frame.setVisible(true);
		
		//���ű�������
		Music p=new Music();
		p.play();
	}
}
