package map;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MapReader{

	public static int map1[][] = new int[15][15];
	public static int map2[][] = new int[15][15];
	public static int map3[][] = new int[15][15];
	public static String name = null;
	
	
	public static void readFile(String temp, int count) {
		name = temp;
		File file = new File("map/"+name+"/map"+count+".txt");
		
		try {
			Scanner sc1 = new Scanner(file);
			
			for(int i=0;i<15;i++) {
				for(int j=0;j<15;j++) {
					map1[j][i] = sc1.nextInt();
				}
			}

			for(int i=0;i<15;i++) {
				for(int j=0;j<15;j++) {
					map2[j][i] = sc1.nextInt();
				}
			}
			
			for(int i=0;i<15;i++) {
				for(int j=0;j<15;j++) {
					map3[j][i] = sc1.nextInt();
				}
			}
			
			sc1.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	/**	for(int i=0;i<15;i++) {
			for(int j=0;j<15;j++) {
				System.out.print(map2[i][j]);
			}
			System.out.print("\n");
		}**/
	}
	

}