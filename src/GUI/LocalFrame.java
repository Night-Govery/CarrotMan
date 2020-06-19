package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class LocalFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
	ArrayList<String> map_list = new ArrayList<String>();
	
    DefaultListModel model;

	
	/**
	 * 
	 * 将列表中的账户打印到Jlist中
	 * 
	 * @param user_list 账户列表
	 */
    public void loadList() {
    	model.removeAllElements();
    	for(int i=0;i<map_list.size();i++) {
    		model.addElement(map_list.get(i));
    	}
    }
	

	/**
	 * Create the frame.
	 */
	public LocalFrame() {
		
		File file = new File("MAPLIST.txt");
		
		try {
			Scanner sc1 = new Scanner(file);
			
			while(sc1.hasNext()) {
				map_list.add(sc1.next());
			}
			
			sc1.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 428, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237ID");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(43, 31, 68, 36);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(119, 31, 225, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("\u89D2\u8272\u540D");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(43, 96, 68, 36);
		contentPane.add(label);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(119, 96, 225, 30);
		contentPane.add(textField_1);
		
		JLabel label_1 = new JLabel("\u5730\u56FE\u9009\u62E9");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(86, 156, 114, 36);
		contentPane.add(label_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 192, 258, 260);
		contentPane.add(scrollPane);
        model = new DefaultListModel();
		JList list = new JList(model);
		scrollPane.setViewportView(list);
		
		JButton btnNewButton = new JButton("\u8FDB\u5165\u6E38\u620F");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame mf = new mainFrame(textField.getText(), (String) model.get(list.getSelectedIndex()), textField_1.getText());	
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 15));
		btnNewButton.setBounds(294, 202, 97, 105);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("\u5730\u56FE\u5217\u8868");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 15));
		button.setBounds(294, 330, 97, 105);
		contentPane.add(button);
	}
}
