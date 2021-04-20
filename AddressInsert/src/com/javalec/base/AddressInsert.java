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
		lbl_Telno.setBounds(22, 83, 61, 16);
		frame.getContentPane().add(lbl_Telno);
		
		tf_Telno = new JTextField();
		tf_Telno.setColumns(10);
		tf_Telno.setBounds(76, 78, 130, 26);
		frame.getContentPane().add(tf_Telno);
	}
	
	
	
	
	private void insertAction() {		// User Insert Action
		
		// It used also in JSP
		PreparedStatement ps = null;   // java.util    java.sql
		try {
		
			
			//-----Same Format-----//
			Class.forName("com.mysql.cj.jdbc.Driver");    // Package Name  in Referenced Library
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);  //java.sql
																// you can insert id and pw also here
			Statement stmt_mysql = conn_mysql.createStatement();
			//-----Same Format-----//
			
			
			String qeury = "insert into userinfo(name) values (?)";
															// Name that user going to insert
			ps = conn_mysql.prepareStatement(qeury);        // before compile
			ps.setString(1, tf_Name.getText().trim());
			ps.executeUpdate();   // it works to send to DB
			
			conn_mysql.close();
			
			JOptionPane.showMessageDialog(null, tf_Name.getText() + "Insert Success");
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		
		
		
		
		
	}
	
	
}