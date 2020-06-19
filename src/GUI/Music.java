package GUI;
 

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
 
@SuppressWarnings("deprecation")
public class Music {
	public void play() {
		//1 ��ȡ��Ҫ���ŵ������ļ�
		File file = new File("music1.wav");
		//2������һ��AudioInputStream���ڽ����������Ƶ����
		AudioInputStream am;
		//3��ʹ��AudioSystem����ȡ��Ƶ����Ƶ������(�����׳����쳣)
		try {
			am = AudioSystem.getAudioInputStream(file);
			//4��ʹ��AudioFormat����ȡAudioInputStream�ĸ�ʽ
			AudioFormat af = am.getFormat();
			//5��һ��Դ������
			SourceDataLine sd ;
			//6����ȡ��������֧�ֵ���Ƶ��ʽDataLine.info
			//DataLine.Info dl = new DataLine.Info(SourceDataLine.class, af);
			//7����ȡ������������ƥ����� д��Դ�������� ��ѡһ
			sd = AudioSystem.getSourceDataLine(af);//���д��
			//sd = (SourceDataLine) AudioSystem.getLine(dl);
			//8���򿪾���ָ����ʽ���У���������ʹ�л����Դ�����в���
			sd.open();
			//9������ĳ��������ִ������i/o
			sd.start();
			//10��д����
			int sumByteRead = 0; //��ȡ�����ֽ���
			byte [] b = new byte[320];//�����ֽ������С
			//11������Ƶ����ȡָ������������������ֽڣ����������������ֽ������С�
			while (sumByteRead != -1) {//-1����û�� ������-1ʱ�����޶�ȡ
				sumByteRead = am.read(b, 0, b.length);//12����ȡ�ĸ�����
				if(sumByteRead >= 0 ) {//13����ȡ��֮������д���Ƶ��,��ʼ����
					sd.write(b, 0, b.length);
				}
			}
			//�ر�
			sd.drain();
			sd.close();
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
    
    
    