package map;

import javax.swing.ImageIcon;

import config.gameConfig;

public class GetMap2 implements gameConfig{
	//Í¨¹ýÊý×ÖÆ¥ÅäÍ¼Æ¬
		public static ImageIcon int2icon(int num){
			if(num==0){
				return map2_icon_0;
			}else if(num==1){
				return map2_icon_1;
			}else if(num==2){
				return map2_icon_2;
			}
				
			if(MapDrawer.monster_list.size()==0) {
				if(num==101){
					return map2_icon_100;
				}else if(num==102){
					return map2_icon_100;
				}else if(num==103){
					return map2_icon_100;
				}
			}
			else if(MapDrawer.monster_list.size()!=0) {
				if(num==101){
					return map2_icon_101;
				}else if(num==102){
					return map2_icon_101;
				}else if(num==103){
					return map2_icon_101;
				}
			}
			return null;
		}
}