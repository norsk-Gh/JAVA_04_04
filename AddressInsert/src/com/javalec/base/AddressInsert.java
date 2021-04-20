package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class AddressInsert {

	private JFrame frame;
	private JTextField tf_Name;
	private JTextField tf_Telno;
	
	
	// DataBase Env
												// we use this format, change only IP , DB name
												// to use other users DB -> change IP, ID, PW
	private final String url_mysql = "jdbc:mysql://172.20.10.6/useraddress?serverTimezone=UTC&characterEncoding=utf8&useSSL=FALSE";
	private final String id_mysql = "root";
	private final String pw_mysql = "qwer1234";
	private JTextField tf_Address;
	private JTextField tf_Email;
	private JTextField tf_Relation;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddressInsert window = new AddressInsert();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddressInsert() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl_Name = new JLabel("Name");
		lbl_Name.setBounds(22, 34, 61, 16);
		frame.getContentPane().add(lbl_Name);
		
		tf_Name = new JTextField();
		tf_Name.setBounds(76, 29, 130, 26);
		frame.getContentPane().add(tf_Name);
		tf_Name.setColumns(10);
		
		JButton btn_Insert = new JButton("INSERT");
		btn_Insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				// btn Action
				insertAction();
				
			}
		});
		btn_Insert.setBounds(302, 218, 117, 29);
		frame.getContentPane().add(btn_Insert);
		
		JLabel lbl_Telno = new JLabel("Tel.");
		lbl_Telno.setBounds(22, 67, 61, 16);
		frame.getContentPane().add(lbl_Telno);
		
		tf_Telno = new JTextField();
		tf_Telno.setColumns(10);
		tf_Telno.setBounds(76, 62, 130, 26);
		frame.getContentPane().add(tf_Telno);
		
		JLabel lbl_Address = new JLabel("Address");
		lbl_Address.setBounds(22, 100, 61, 16);
		frame.getContentPane().add(lbl_Address);
		
		tf_Address = new JTextField();
		tf_Address.setColumns(10);
		tf_Address.setBounds(76, 95, 130, 26);
		frame.getContentPane().add(tf_Address);
		
		JLabel lbl_Email = new JLabel("Email");
		lbl_Email.setBounds(22, 133, 61, 16);
		frame.getContentPane().add(lbl_Email);
		
		tf_Email = new JTextField();
		tf_Email.setColumns(10);
		tf_Email.setBounds(76, 128, 130, 26);
		frame.getContentPane().add(tf_Email);
		
		JLabel lbl_Relation = new JLabel("Relation");
		lbl_Relation.setBounds(22, 166, 61, 16);
		frame.getContentPane().add(lbl_Relation);
		
		tf_Relation = new JTextField();
		tf_Relation.setColumns(10);
		tf_Relation.setBounds(76, 161, 130, 26);
		frame.getContentPane().add(tf_Relation);
	}
	
	
//	private void errorCheck() {
//		int error = 0;
//		String message = "";
//		int action = 0;
//		
//		while(error==0) {
//
//			if(tf_Name.getText().equals("")) {     //이름이건 주소건 다 관계가 없기떄문에... 각각 if쓰는게 좋
//				message = "name"; action++;
//			}
//			if(tf_Telno.getText().toString().equals("")) {
//				message = "tel number"; action++;
//			}
//			if(tf_Address.getText().toString().equals("")) {
//				message = "address"; action++;
//			}
//			if(tf_Email.getText().toString().equals("")) {
//				message = "email"; action++;
//			}
//			if(tf_Relation.getText().toString().equals("")) {
//				message = "relation"; action++;
//			}
//			if(action==0){
//				error++; break;
//			}
//			if(action !=0) {
//				JOptionPane.showMessageDialog(null, "Please input your "+ message);
//			}
//			
//		}//while
//	}
//
//	private void error() {
//		String[] input = {tf_Name.getText(),tf_Telno.getText(),tf_Address.getText(),tf_Email.getText(),tf_Relation.getText()};
//		String[] result = {"name", "tel number","address","email","relation"};
//		for(int i =0; i<input.length; i++) {
//			if(input[i].isEmpty()) {
//				JOptionPane.showMessageDialog(null, "Please input your "+ result[i]);
//			};
//			
//		}
//		
//	}
//	
//	
//	
//	
	// 에러체크할떄.. 성명을 가장 마지막에 뜨게함! 관계~ 성명 순으로 올라와서 커서까지 두게 함
	
	private void insertAction() {		// User Insert Action
	
			
		// It used also in JSP
		PreparedStatement ps = null;   // java.util    java.sql
		try { // like insert query changes to language that JAVA understand
		
			
			//-----Same Format-----//
			Class.forName("com.mysql.cj.jdbc.Driver");    // Package Name  in Referenced Library
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);  //java.sql
																// you can insert id and pw also here
			Statement stmt_mysql = conn_mysql.createStatement();
			//-----Same Format-----//
			
			
			String qeury = "insert into userinfo(name,telno,address,email, relation) values (?,?,?,?,?)";
			
//			String qeury = "insert into userinfo(name,telno,address,email, relation)"
//			String query1 = " values (?,?,?,?,?)"; //space!!
//			ps = conn_mysql.prepareStatement(qeury+query1);  			
			
															// Name that user going to insert
			ps = conn_mysql.prepareStatement(qeury);        // before compile
			ps.setString(1, tf_Name.getText().trim());
			ps.setString(2, tf_Telno.getText().trim());
			ps.setString(3, tf_Address.getText().trim());
			ps.setString(4, tf_Email.getText().trim());
			ps.setString(5, tf_Relation.getText().trim());
			
			ps.executeUpdate(); 
			
			
			
//			String qeury_tel = "insert into userinfo(telno) values (?)";
//			
//			ps = conn_mysql.prepareStatement(qeury_tel);        // before compile
//			ps.setString(1, tf_Telno.getText().trim());
//			ps.executeUpdate();   // it works to send to DB

			
			conn_mysql.close();
			
			JOptionPane.showMessageDialog(null, "OK");
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
		}//catch
		
	}
	
	
}