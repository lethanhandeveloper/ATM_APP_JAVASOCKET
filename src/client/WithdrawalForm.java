package client;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class WithdrawalForm extends JFrame {

	private JPanel contentPane;
	private MainForm main;
	private String myaccount;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public WithdrawalForm(MainForm main,String myaccount) {
		this.myaccount=myaccount;
		setTitle("Chọn số tiền rút");
		this.main=main;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 633, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Quý khách vui lòng chọn số tiền rút");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel_2.setForeground(new Color(255, 0, 0));
		lblNewLabel_2.setBounds(146, 24, 440, 24);
		contentPane.add(lblNewLabel_2);
		
		JButton bt500k = new JButton("500.000");
		bt500k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				withdrawal(bt500k.getText());
			}
		});
		bt500k.setForeground(new Color(0, 0, 0));
		bt500k.setBackground(new Color(0, 255, 0));
		bt500k.setFont(new Font("SansSerif", Font.PLAIN, 18));
		bt500k.setBounds(37, 101, 205, 50);
		contentPane.add(bt500k);
		
		JButton bt1000k = new JButton("1.000.000");
		bt1000k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				withdrawal(bt1000k.getText());
			}
		});
		bt1000k.setBackground(new Color(0, 255, 0));
		bt1000k.setFont(new Font("SansSerif", Font.PLAIN, 18));
		bt1000k.setBounds(37, 171, 205, 50);
		contentPane.add(bt1000k);
		
		JButton bt1500k = new JButton("1.500.000");
		bt1500k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				withdrawal(bt1500k.getText());
			}
		});
		bt1500k.setBackground(Color.GREEN);
		bt1500k.setForeground(Color.BLACK);
		bt1500k.setFont(new Font("SansSerif", Font.PLAIN, 18));
		bt1500k.setBounds(37, 239, 205, 50);
		contentPane.add(bt1500k);
		
		JButton bt2000k = new JButton("2.000.000");
		bt2000k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				withdrawal(bt2000k.getText());
			}
		});
		bt2000k.setBackground(Color.GREEN);
		bt2000k.setFont(new Font("SansSerif", Font.PLAIN, 18));
		bt2000k.setBounds(37, 307, 205, 50);
		contentPane.add(bt2000k);
		
		JButton bt3000k = new JButton("3.000.000");
		bt3000k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				withdrawal(bt3000k.getText());
			}
		});
		bt3000k.setBackground(Color.GREEN);
		bt3000k.setFont(new Font("SansSerif", Font.PLAIN, 18));
		bt3000k.setBounds(380, 101, 184, 50);
		contentPane.add(bt3000k);
		
		JButton btanother = new JButton("Số tiền khác");
		btanother.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp=JOptionPane.showInputDialog(contentPane, "Nhập số tiền muốn rút\nPhải lớn hơn và bội số của 50.000đ\nVí dụ:150.000", "", JOptionPane.DEFAULT_OPTION);
				int withdrawal_money = 0;
				try {
					withdrawal_money=Integer.parseInt(temp.replace(".", ""));
					System.out.println(withdrawal_money);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(contentPane, "Số tiền vừa nhập không hợp lệ", "Hãy nhập lại", JOptionPane.ERROR_MESSAGE);
				}
				if(withdrawal_money>50000&&withdrawal_money%50000==0){
					withdrawal(Integer.toString(withdrawal_money));
				}else {
					JOptionPane.showMessageDialog(contentPane, "Số tiền vừa nhập không hợp lệ", "Hãy nhập lại", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btanother.setBackground(Color.GREEN);
		btanother.setFont(new Font("SansSerif", Font.PLAIN, 18));
		btanother.setBounds(380, 239, 184, 50);
		contentPane.add(btanother);
		
		JButton btexit = new JButton("Hủy giao dịch");
		btexit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				main.setVisible(true);
			}
		});
		btexit.setBackground(Color.GREEN);
		btexit.setFont(new Font("SansSerif", Font.PLAIN, 18));
		btexit.setIcon(new ImageIcon(WithdrawalForm.class.getResource("/images/close.png")));
		btexit.setBounds(380, 314, 184, 43);
		contentPane.add(btexit);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(WithdrawalForm.class.getResource("/images/background_1.jpg")));
		lblNewLabel.setBounds(0, 0, 626, 378);
		contentPane.add(lblNewLabel);
		
		setVisible(true);
	}
	private void withdrawal(String withdrawal_ammount) {
		int check=JOptionPane.
		showConfirmDialog(contentPane, "Xác nhận muốn rút "+withdrawal_ammount+" đ", "Thông báo", JOptionPane.YES_NO_OPTION);
		if(check==0) {
			ClientThread.write("3-"+myaccount+"-"+Integer.parseInt(withdrawal_ammount.replace(".", "")));
			String result=ClientThread.getReceive();
			if(result.equals("NOT_ENOUGH")) {
				JOptionPane.showMessageDialog(contentPane, "Số dư của quý khách không đủ", "", JOptionPane.ERROR_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(contentPane, "Đã rút "+withdrawal_ammount+" đ", "", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}
