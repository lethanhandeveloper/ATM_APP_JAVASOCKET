package server;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewHistoryForm extends JFrame {

	private JPanel contentPane;
	private String arr[];
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ViewHistoryForm(String arr[]) {
		setLocationRelativeTo(null);
		setTitle("Lịch sử giao dịch");
		this.arr=arr;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 408, 228);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		textArea.setText("");
		textArea.setEditable(false);
		
		JButton btnNewButton = new JButton("Đóng");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(307, 254, 97, 25);
		contentPane.add(btnNewButton);
		for(int i=arr.length-1;i>=4;i--) {
			textArea.append(arr[i]+"\n");
		}
		
		if(arr.length<5) textArea.setText("Tài khoản này chưa có giao dịch nào");
		
		setVisible(true);
	}
}
