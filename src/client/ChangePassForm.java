package client;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangePassForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private MainForm main;
	private String myaccount;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ChangePassForm(MainForm main,String myaccount) {
		setTitle("Đổi mật khẩu");
		this.myaccount=myaccount;
		this.main=main;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nhập thông tin bên dưới để đổi mật khẩu");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(140, 13, 322, 27);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Mật khẩu cũ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(70, 105, 110, 16);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		textField.setBounds(192, 99, 330, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Mật khẩu mới");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(70, 157, 110, 16);
		contentPane.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(192, 145, 330, 33);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Xác nhận mật khẩu mới");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(70, 203, 210, 27);
		contentPane.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(277, 191, 245, 38);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Đổi mật khẩu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changepwactionPerformed();
			}
		});
		btnNewButton.setBounds(257, 254, 117, 38);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Thoát");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				main.setVisible(true);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(ChangePassForm.class.getResource("/images/close.png")));
		btnNewButton_1.setBounds(397, 254, 110, 38);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("Lưu ý:Mật khẩu chỉ gồm chữ và số,không được chứa các kí tự đặc biệt");
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblNewLabel_4.setBounds(70, 44, 473, 27);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel1 = new JLabel("New label");
		lblNewLabel1.setIcon(new ImageIcon(ChangePassForm.class.getResource("/images/background_1.jpg")));
		lblNewLabel1.setBounds(0, 0, 592, 305);
		contentPane.add(lblNewLabel1);

		setVisible(true);
	}
	public void changepwactionPerformed() {
		if(textField.getText().length()>0&&textField_1.getText().length()>0&&textField_2.getText().length()>0) {
			if(!textField_1.getText().equals(textField_2.getText())) {
				JOptionPane.showMessageDialog(contentPane, "Trường mật khẩu mới không trùng", "Hãy nhập lại", JOptionPane.ERROR_MESSAGE);
				return;
			}
			int check_acp=JOptionPane.showConfirmDialog(contentPane, "Bạn chắn chắn muốn đổi mật khẩu");
			if(check_acp==0) {
				ClientThread.write("7"+"-"+myaccount+"-"+textField.getText()+"-"+textField_2.getText());
				String check_reset=ClientThread.getReceive();
				if(check_reset.equals("ERROR")) {
					JOptionPane.showMessageDialog(contentPane, "Mật khẩu cũ không đúng", "Hãy nhập lại", JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(contentPane, "Đã đổi mật khẩu", "Thành công", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}else {
			JOptionPane.showMessageDialog(contentPane, "Chưa nhập đủ thông tin", "Hãy nhập lại", JOptionPane.ERROR_MESSAGE);
		}
	}
}
