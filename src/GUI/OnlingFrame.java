package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class OnlingFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
	PrintStream serverOut;
	Scanner serverIn;

	public ArrayList<String> map_list = new ArrayList<String>();
	
    DefaultListModel model;
    
	/**
	 * 
	 * 将列表中的账户打印到Jlist中
	 * 
	 * @param user_list 账户列表
	 */
    public void loadList() {
    	model.removeAllElements();
    	for(int i=1;i<map_list.size();i++) {
    		model.addElement(map_list.get(i));
    	}
    }
	

	/**
	 * Create the frame.
	 */
	public OnlingFrame(PrintStream serverOut, Scanner serverIn) {
		
		
		this.serverIn = serverIn;
		this.serverOut = serverOut;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237ID");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(124, 31, 68, 36);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(200, 31, 225, 30);
		contentPane.add(textField);
		
		JLabel label_1 = new JLabel("\u89D2\u8272\u540D");
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(124, 96, 68, 36);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(200, 96, 225, 30);
		contentPane.add(textField_1);
		
		JLabel label_2 = new JLabel("\u5730\u56FE\u9009\u62E9");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		label_2.setBounds(167, 156, 114, 36);
		contentPane.add(label_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(91, 192, 258, 260);
		contentPane.add(scrollPane);
		
        model = new DefaultListModel();
		JList list = new JList(model);
		scrollPane.setViewportView(list);
		
		JButton button = new JButton("\u5237\u65B0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serverOut.println("MAPLIST");
				loadList();
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 15));
		button.setBounds(373, 192, 97, 106);	
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u8FDB\u5165\u6E38\u620F");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame mf = new mainFrame(textField.getText(), (String) model.get(list.getSelectedIndex()), textField_1.getText());	
			}
		});
		
		button_1.setFont(new Font("宋体", Font.PLAIN, 15));
		button_1.setBounds(481, 192, 97, 260);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("\u4E0B\u8F7D");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serverOut.println("MAP");
				serverOut.println((String) model.get(list.getSelectedIndex()));
				serverOut.println("100");
			}
		});
		button_2.setFont(new Font("宋体", Font.PLAIN, 15));
		button_2.setBounds(373, 332, 97, 106);
		contentPane.add(button_2);
	}
}
