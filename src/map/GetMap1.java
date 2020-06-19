package map;

import javax.swing.ImageIcon;

import config.gameConfig;

public class GetMap1 implements gameConfig{
	//Í¨¹ýÊý×ÖÆ¥ÅäÍ¼Æ¬
		public static ImageIcon int2icon(int num){
			if(num==0){
				return map1_icon_0;
			}else if(num==1){
				return map1_icon_1;
			}
			return null;
		}
}