package client;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class TransferForm extends JFrame {

	private JPanel contentPane;
	private JTextField accnbtf;
	private JTextField transfermntf;
	private String myaccount;
	private MainForm main;
	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public TransferForm(String myaccount,MainForm main) {
		this.main=main;
		this.myaccount=myaccount;
		setTitle("Chuyển tiền");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 633, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Quý khách nhập thông tin bên dưới để chuyển tiền");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(116, 13, 421, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Số tài khoản chuyển đến");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(55, 116, 184, 16);
		contentPane.add(lblNewLabel);
		
		accnbtf = new JTextField();
		accnbtf.setBounds(251, 108, 286, 34);
		contentPane.add(accnbtf);
		accnbtf.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Số tiền chuyển");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(55, 159, 113, 20);
		contentPane.add(lblNewLabel_2);
		
		transfermntf = new JTextField();
		transfermntf.setBounds(251, 153, 286, 34);
		contentPane.add(transfermntf);
		transfermntf.setColumns(10);
		
		JButton btnNewButton = new JButton("Chuyển Tiền");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBackground(new Color(51, 204, 102));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transferMoney();
			}
		});
		btnNewButton.setBounds(246, 287, 135, 41);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Hủy Giao Dịch");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setIcon(new ImageIcon(TransferForm.class.getResource("/images/close.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				main.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(415, 287, 163, 41);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel1 = new JLabel("");
		lblNewLabel1.setBackground(new Color(60, 179, 113));
		lblNewLabel1.setIcon(new ImageIcon(TransferForm.class.getResource("/images/background_1.jpg")));
		lblNewLabel1.setBounds(0, 0, 699, 378);
		contentPane.add(lblNewLabel1);
		setVisible(true);
	}
	private void transferMoney() {
		try {
			Integer.parseInt(transfermntf.getText().replace(".", ""));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, "Số tiền không hợp lệ", "Nhập lại số tiền", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(accnbtf.getText().equals(myaccount)) {
			JOptionPane.showMessageDialog(contentPane, "Bạn không thể chuyển tiền cho chính mình", "Nhập lại stk", JOptionPane.ERROR_MESSAGE);
		}else {
			ClientThread.write("4-"+myaccount+"-"+accnbtf.getText()+"-"+transfermntf.getText().replace(".", ""));
			String msg=ClientThread.getReceive();
			if(msg.equals("NOT_ENOUGH")) {
				JOptionPane.showMessageDialog(contentPane, "Số dư của quý khách không đủ", "Hãy nhập lại", JOptionPane.ERROR_MESSAGE);
			}else if(msg.equals("OKE")){
				JOptionPane.showMessageDialog(contentPane, "Đã chuyển "+transfermntf.getText()+"đ"+" cho "+accnbtf.getText(), "Thành công", JOptionPane.INFORMATION_MESSAGE);
			}else if(msg.equals("NOT_EXISTS")){
				JOptionPane.showMessageDialog(contentPane, "Tài khoản "+accnbtf.getText()+" không tồn tại", "Hãy nhập lại", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
}
