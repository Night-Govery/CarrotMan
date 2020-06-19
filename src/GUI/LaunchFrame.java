package GUI;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class LaunchFrame extends JFrame {

	private JPanel contentPane;
	
    //服务器相关变量
    static Socket connection;
	PrintStream serverOut;
	Scanner serverIn;
	OnlingFrame frame;
	
	
	public static String name = null;
	
	/**
	 * Create the frame.
	 */
	public LaunchFrame() {	
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 422, 293);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("\u672C\u5730\u6A21\u5F0F");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalFrame frame = new LocalFrame();
				frame.loadList();
				frame.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton.setBounds(23, 175, 146, 43);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("\u8054\u7F51\u6A21\u5F0F");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			      try {
			            // Create a MulticastSocket
			        	connection = new Socket("127.0.0.2", 4017);
			            System.out.println("客户端启动成功");   
			            //创建输入和输出的流，用于获取和输出服务器的数据
			            serverIn = new Scanner(connection.getInputStream());
			            serverOut = new PrintStream(connection.getOutputStream());
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						//System.exit(0);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						//System.exit(0);
					}
			        RemoteReader r = new RemoteReader();
			        Thread t = new Thread(r);
			        t.start();
				
				frame = new OnlingFrame(serverOut, serverIn);
				frame.setVisible(true);

			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		button.setBounds(242, 175, 146, 43);
		contentPane.add(button);
		
		JLabel lblNewLabel = new JLabel("\u841D\u535C\u52C7\u8005");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 40));
		lblNewLabel.setBounds(127, 32, 160, 58);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(180, 100, 91, 65);
		Icon icon = new ImageIcon("resourse/other/main.png");
		lblNewLabel_1.setIcon(icon);
		lblNewLabel_1.setSize(60,60);
		contentPane.add(lblNewLabel_1);
	}
	
	
	
	
    /**
     * 
     * 处理服务器信息的线程
     * 
     * 
     * @author FlyBull
     *
     */
	class RemoteReader implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
        	try {
            	while(true) {
      	        	if(serverIn.hasNext()) {
      	        		String aLine = null;
      	        		aLine = serverIn.nextLine();
	        			System.out.println("接收到服务端消息："+aLine);
      	        		choseInfo(aLine);
      	        	}	
          	    }
        	}catch(Exception e) {
        		System.out.println(e.getLocalizedMessage());
        	}
		}	
	}
	
	
	private void choseInfo(String s) {
		if(s.startsWith("MAPLIST~")){
			frame.map_list.clear();
			int count = 0;
			String c1 = null;
			char c = '1';
			String line = null;
			for(int j=0;j<s.length();j++) {
				  c = s.charAt(j);
				  c1 = String.valueOf(c);
				  if(c1.equals("~")) {
					  count++;
					  frame.map_list.add(line); 
					  line = null;
					  
				  }
				  else {
					  if(line == null) {
						  line = c1;
					  }else {
						  line+=c1;
					  }
				  }
			}
		}
		else if(s.startsWith("MAP~")) {		
			try {
				String[] a = s.split("~");
				
				File file = new File("map/"+a[1]);
	            if(!file.exists()){
	            	file.mkdirs();//创建目录
	            }
				
	            File file1 = new File(file, "/"+a[2]);
	            if(!file.exists()){
	                try {
	                    file.createNewFile();
	                } catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	            }
	            
				BufferedWriter out = new BufferedWriter(new FileWriter(file1));

				s = serverIn.nextLine();
				String[] a1 = s.split("~");
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						out.write(a1[j*15+i+1]+" ");
					}
					out.write("\n");
				}
				
				out.write("\n");
				out.write("\n");
				out.write("\n");
				
				s = serverIn.nextLine();
				String[] a2 = s.split("~");
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						out.write(a2[j*15+i+1]+" ");
					}
					out.write("\n");
				}
				
				out.write("\n");
				out.write("\n");
				out.write("\n");
				
				s = serverIn.nextLine();
				String[] a3 = s.split("~");
				for(int i=0;i<15;i++) {
					for(int j=0;j<15;j++) {
						out.write(a3[j*15+i+1]+" ");
					}
					out.write("\n");
				}
				
				out.close();
				
				
				//更改地图列表
				ArrayList<String> map_list = new ArrayList<String>();
				File file3 = new File("MAPLIST.txt");
				try {
					//写入之前的地图列表
					Scanner sc1 = new Scanner(file3);
					while(sc1.hasNext()) {
						map_list.add(sc1.next());
					}	
					sc1.close();
					if(!map_list.contains(a[1])) {
						map_list.add(a[1]);
					}
					System.out.println(map_list.size());
					
					BufferedWriter sc2 = new BufferedWriter(new FileWriter(file3));
					for(int i=0;i<map_list.size();i++) {
						sc2.write(map_list.get(i)+"\n");
					}
					sc2.close();
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}	
	}
}
