package client;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class LoginSignUpForm extends JFrame {

	private JPanel contentPane;
	private JTextField nb_acctf;
	private JPasswordField pwfield;
	private JTextField stk_tf;
	private JTextField pass_tf;
	private JTextField name_tf;
	private JTextField sdt_tf;
	private DataInputStream dis;
	private DataOutputStream dos;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public LoginSignUpForm() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {}
		setTitle("Đăng Nhập/Đăng Ký");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 843, 335);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LoginSignUpForm.class.getResource("/images/vcblogo.png")));
		lblNewLabel.setBounds(50, 18, 346, 271);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(
				"K\u00EDnh ch\u00E0o.Qu\u00FD kh\u00E1ch vui l\u00F2ng \u0111\u0103ng nh\u1EADp \u0111\u1EC3 ti\u1EBFp t\u1EE5c!");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(370, 0, 408, 29);
		contentPane.add(lblNewLabel_1);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(370, 42, 408, 234);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 204, 51));
		tabbedPane.addTab("Đăng Nhập", null, panel, null);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Số tài khoản");
		lblNewLabel_2.setBounds(30, 28, 86, 16);
		panel.add(lblNewLabel_2);

		nb_acctf = new JTextField();
		nb_acctf.setBounds(128, 25, 263, 28);
		panel.add(nb_acctf);
		nb_acctf.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Mật khẩu");
		lblNewLabel_3.setBounds(30, 65, 56, 16);
		panel.add(lblNewLabel_3);

		pwfield = new JPasswordField();
		pwfield.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pwfield.setBounds(127, 62, 264, 28);
		panel.add(pwfield);
		
		JButton btnNewButton = new JButton("    Đăng Nhập");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loginactionPerformed();
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setIcon(new ImageIcon(LoginSignUpForm.class.getResource("/images/login.png")));
		btnNewButton.setBounds(94, 170, 160, 28);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Thoát");
		btnNewButton_1.setBackground(Color.RED);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(LoginSignUpForm.class.getResource("/images/close.png")));
		btnNewButton_1.setBounds(266, 170, 136, 28);
		panel.add(btnNewButton_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 204, 51));
		tabbedPane.addTab("Đăng Ký", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(17, 18, 55, 16);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Số tài khoản");
		lblNewLabel_5.setBounds(17, 18, 75, 16);
		panel_1.add(lblNewLabel_5);
		
		stk_tf = new JTextField();
		stk_tf.setBounds(104, 6, 274, 28);
		panel_1.add(stk_tf);
		stk_tf.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Mật khẩu");
		lblNewLabel_6.setBounds(17, 46, 55, 16);
		panel_1.add(lblNewLabel_6);
		
		pass_tf = new JTextField();
		pass_tf.setBounds(104, 40, 274, 28);
		panel_1.add(pass_tf);
		pass_tf.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Tên");
		lblNewLabel_7.setBounds(17, 87, 55, 16);
		panel_1.add(lblNewLabel_7);
		
		name_tf = new JTextField();
		name_tf.setBounds(104, 81, 274, 28);
		panel_1.add(name_tf);
		name_tf.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Số điện thoại");
		lblNewLabel_9.setBounds(17, 130, 75, 16);
		panel_1.add(lblNewLabel_9);
		
		sdt_tf = new JTextField();
		sdt_tf.setBounds(104, 124, 274, 28);
		panel_1.add(sdt_tf);
		sdt_tf.setColumns(10);
		
		JButton signup_bt = new JButton("Đăng ký");
		signup_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signupactionPerformed();
			}
		});
		signup_bt.setBackground(new Color(250, 128, 114));
		signup_bt.setBounds(139, 176, 90, 28);
		panel_1.add(signup_bt);
		
		JButton exit_bt = new JButton("Thoát");
		exit_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exit_bt.setBounds(252, 176, 90, 28);
		panel_1.add(exit_bt);
		
		JLabel lblNewLabel_8 = new JLabel("New label");
		lblNewLabel_8.setIcon(new ImageIcon(LoginSignUpForm.class.getResource("/images/background_1.jpg")));
		lblNewLabel_8.setBounds(0, 0, 825, 289);
		contentPane.add(lblNewLabel_8);
		
		setVisible(true);
		
		new ClientThread().start();
	}
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {}
		new LoginSignUpForm();
	}
	public void loginactionPerformed() {
		char[] temp=pwfield.getPassword();
		String pw="";
		pw=String.copyValueOf(temp);
		if(pw.equals("")||nb_acctf.getText().equals("")) {
			showerrorMessage("Số tài khoản hoặc mật khẩu trống", "Chưa nhập đủ");
		}else {
			ClientThread.write("1-"+nb_acctf.getText()+"-"+pw);
			String receive=ClientThread.getReceive();
			String s[]=receive.split("-");
			if(receive.equals("LOGIN_SUCCESSFULLY")) {
				showifMessage("Đăng nhập thành công", "Thông báo");
				setVisible(false);
				new MainForm(this,nb_acctf.getText(),pw);
			}else if(receive.equals("LOGIN_FAILED")) {
				showerrorMessage("Hãy tạo tài khoản mới", "Tài khoản không tồn tại");
			}else {
				showerrorMessage("Tài khoản của bạn tạm thời bị khóa.Hãy thử lại sau", "Đăng nhập thất bại");
			}
		}
	}
	public void signupactionPerformed() {
		String stk=stk_tf.getText();
		String pass=pass_tf.getText();
		String name=name_tf.getText();
		String sdt=sdt_tf.getText();
		if(stk.length()>0&&pass.length()>0&&name.length()>0&&sdt.length()>0) {
			ClientThread.write("2-"+stk+"-"+pass+"-"+name+"-"+sdt);
			if(ClientThread.getReceive().equals("SIGNUP_SUCCESSFULLY")) {
				int check=JOptionPane.showConfirmDialog(contentPane, "Đăng nhập ngay", "Đăng ký thành công", JOptionPane.INFORMATION_MESSAGE);
				if(check==0) {
					new MainForm(this,stk,pass);
					setVisible(false);
				}
			}else if(ClientThread.getReceive().equals("SIGNUP_FAILED")){
				showerrorMessage("Số tài khoản đã tồn tại", "Đăng ký thất bại");
			}else {
				showerrorMessage("Server từ chối đăng ký", "Đăng ký thất bại");
			}
		}else {
			showerrorMessage("CHưa nhập đủ thông tin", "Hãy nhập lại");
		}
	}
	public void showifMessage(String msg,String title) {
		JOptionPane.showMessageDialog(contentPane, msg, title, JOptionPane.INFORMATION_MESSAGE);
	}
	public void showerrorMessage(String msg,String title) {
		JOptionPane.showMessageDialog(contentPane, msg, title, JOptionPane.ERROR_MESSAGE);
	}
}
