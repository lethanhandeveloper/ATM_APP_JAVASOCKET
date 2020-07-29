package client;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.awt.event.ActionEvent;

public class MainForm extends JFrame {

	private JPanel contentPane;
	private LoginSignUpForm loginSignUpForm;
	private String myaccount;
	private String pw;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public MainForm(LoginSignUpForm loginsignupform,String my_account,String pw) {
		this.myaccount=my_account;
		this.pw=pw;
		this.loginSignUpForm=loginsignupform;
		setTitle("Chọn Giao Dịch");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 923, 350);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MainForm.class.getResource("/images/vcblogo.png")));
		lblNewLabel.setBounds(12, 13, 250, 257);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		panel.setBounds(286, 13, 600, 261);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Quý Khách Vui Lòng Lựa Chọn Giao Dịch");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(93, 6, 428, 43);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("   R\u00FAt Ti\u1EC1n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				withdrawalactionPerformed();
			}
		});
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnNewButton.setIcon(new ImageIcon(getClass().getResource("/images/withdrawal.png")));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(61, 80, 208, 43);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("  Ki\u1EC3m Tra S\u1ED1 D\u01B0");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lookbalanceactionPerformed();
			}
		});
		btnNewButton_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnNewButton_1.setIcon(new ImageIcon(getClass().getResource("/images/search.png")));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBounds(61, 135, 208, 43);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("   \u0110\u1ED5i M\u1EADt Kh\u1EA9u");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changepassactionPerformed();
			}
		});
		btnNewButton_2.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnNewButton_2.setIcon(new ImageIcon(getClass().getResource("/images/password.png")));
		btnNewButton_2.setForeground(SystemColor.desktop);
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setBounds(61, 196, 208, 43);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton(" Chuy\u1EC3n Ti\u1EC1n");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trasferactionPerformed();
			}
		});
		btnNewButton_3.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnNewButton_3.setIcon(new ImageIcon(getClass().getResource("/images/money-transfer.png")));
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.setBounds(339, 80, 177, 43);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("        N\u1EA1p Ti\u1EC1n");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				depositactionPerformed();
			}
		});
		btnNewButton_4.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnNewButton_4.setIcon(new ImageIcon(getClass().getResource("/images/deposit.png")));
		btnNewButton_4.setBackground(Color.WHITE);
		btnNewButton_4.setBounds(339, 135, 177, 43);
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("        Tho\u00E1t");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loginsignupform.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_5.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnNewButton_5.setIcon(new ImageIcon(getClass().getResource("/images/logout.png")));
		btnNewButton_5.setForeground(new Color(0, 0, 0));
		btnNewButton_5.setBackground(Color.WHITE);
		btnNewButton_5.setBounds(339, 197, 177, 42);
		panel.add(btnNewButton_5);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(MainForm.class.getResource("/images/background_1.jpg")));
		lblNewLabel_2.setBounds(0, 0, 905, 304);
		contentPane.add(lblNewLabel_2);
		
		setVisible(true);
	}
	private void withdrawalactionPerformed() {
		new WithdrawalForm(this,myaccount);
		setVisible(false);
	}
	private void trasferactionPerformed() {
		new TransferForm(myaccount,this);
		setVisible(false);
	}
	public void lookbalanceactionPerformed() {
		ClientThread.write("5-"+myaccount);
		System.out.println(ClientThread.getReceive());
		//new LookBalanceForm(this,ClientThread.getReceive(),myaccount);
		setVisible(false);
	}
	public void depositactionPerformed() {
		new Deposit(myaccount,this);
		setVisible(false);
	}
	public void changepassactionPerformed() {
		setVisible(false);
		new ChangePassForm(this,myaccount);
	}
}
