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
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Deposit extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private String myaccount;
	private MainForm main;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Deposit(String myaccount,MainForm main) {
		setTitle("Nạp tiền");
		this.main=main;
		this.myaccount=myaccount;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 633, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nhập số tiền nạp vào tài khoản");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(170, 13, 358, 32);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(113, 90, 415, 40);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Nạp tiền");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				depositactionPerformed();
			}
		});
		btnNewButton.setBounds(261, 274, 113, 40);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Hủy Giao Dịch");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				main.setVisible(true);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(Deposit.class.getResource("/images/close.png")));
		btnNewButton_1.setBounds(431, 274, 153, 40);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Deposit.class.getResource("/images/background_1.jpg")));
		lblNewLabel.setBounds(0, 0, 669, 378);
		contentPane.add(lblNewLabel);
		
		setVisible(true);
	}
	private void depositactionPerformed() {
		try {
			Integer.parseInt(textField.getText().replace(".", ""));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Số tiền vừa nhập không hợp lệ", "Hãy nhập lại", JOptionPane.ERROR_MESSAGE);
			return;
		}
		ClientThread.write("6-"+myaccount+"-"+textField.getText());
		if(ClientThread.getReceive().equals("SUCCESSFULLY_DEPOSIT")){
			JOptionPane.showMessageDialog(contentPane, "Đã nạp "+textField.getText()+"đ vào tài khoản", "Nạp tiền thành công", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
