package client;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class LookBalanceForm extends JFrame implements ChangeListener {

	private JPanel contentPane;
	private MainForm main;
	private String allmsg;
	private Locale localeVN = new Locale("vi", "VN");
	private NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
	private String myaccount;
	private JTabbedPane tabbedPane;
	private JLabel lblNewLabel,lblNewLabel_1,lblNewLabel_2,lblNewLabel_3,lblNewLabel_4,lblNewLabel_5,lblNewLabel_6,lblNewLabel_7;
	private JTextArea textArea;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public LookBalanceForm(MainForm main,String allmsg,String myaccount) {
		setTitle("Thông tin tài khoản và số dư");
		this.main=main;
		this.allmsg=allmsg;
		this.myaccount=myaccount;
		String arr[]=allmsg.split("</>");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 637, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 13, 595, 296);
		tabbedPane.addChangeListener(this);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 255, 204));
		tabbedPane.addTab("Thông tin tài khoản và số dư", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Số tài khoản:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(43, 34, 128, 26);
		panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Tên chủ thẻ:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(43, 73, 113, 30);
		panel.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Số điện thoại:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(43, 116, 138, 38);
		panel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Số dư hiện tại:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(43, 167, 138, 26);
		panel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel(arr[0]);
		lblNewLabel_4.setForeground(new Color(255, 0, 0));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_4.setBounds(295, 34, 222, 24);
		panel.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel(arr[1]);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(295, 73, 222, 30);
		panel.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel(arr[2]);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_6.setBounds(295, 116, 222, 30);
		panel.add(lblNewLabel_6);
		
		String str1 = currencyVN.format(Long.parseLong(arr[3]));
		
		lblNewLabel_7 = new JLabel(str1);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_7.setBounds(295, 164, 222, 32);
		panel.add(lblNewLabel_7);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(153, 255, 204));
		tabbedPane.addTab("Lịch sử biến động số dư", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 566, 240);
		panel_1.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		for(int i=4;i<arr.length;i++) {
			textArea.append(arr[i]+"\n");
		}
		
		JButton btnNewButton = new JButton("Làm mới");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClientThread.write("5-"+myaccount);
				String arr[]=ClientThread.getReceive().split("</>");
				lblNewLabel_4.setText(arr[0]);
				lblNewLabel_5.setText(arr[1]);
				lblNewLabel_6.setText(arr[2]);
				String str1 = currencyVN.format(Long.parseLong(arr[3]));
				lblNewLabel_7.setText(str1);
				textArea.setText("");
				for(int i=arr.length-1;i>=4;i--) {
					textArea.append(arr[i]+"\n");
				}
			}
		});
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setBounds(330, 322, 97, 42);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Thoát");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				main.setVisible(true);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(LookBalanceForm.class.getResource("/images/close.png")));
		btnNewButton_1.setBounds(439, 322, 115, 42);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel1 = new JLabel("");
		lblNewLabel1.setBounds(0, 0, 647, 387);
		lblNewLabel1.setIcon(new ImageIcon(LookBalanceForm.class.getResource("/images/background_1.jpg")));
		contentPane.add(lblNewLabel1);
		
		setVisible(true);
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		if(tabbedPane.getSelectedIndex()==1) {
			setTitle("Lịch sử biến động số dư");
			ClientThread.write("5-"+myaccount);
			String arr[]=ClientThread.getReceive().split("</>");
			String str1 = currencyVN.format(Long.parseLong(arr[3]));
			lblNewLabel_7.setText(str1);
			textArea.setText("");
			for(int i=arr.length-1;i>=4;i--) {
				textArea.append(arr[i]+"\n");
			}
		}else {
			setTitle("Thông tin tài khoản và số dư");
		}
	}
}
