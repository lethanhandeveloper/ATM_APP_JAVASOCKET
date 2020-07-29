package server;

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
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;

public class ServerGUI extends JFrame implements MouseListener,ActionListener{

	public static JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JTable table_1;
	public static Vector data=new Vector();
	public static Vector<String> title=new Vector<String>();
	public static DefaultTableModel model;
	private static Database db;
	private int selectedRow=-1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerGUI frame = new ServerGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ServerGUI() {
		db=new Database();
		setTitle("Server ATM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 551);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(152, 251, 152));
		panel.setBounds(0, 0, 754, 78);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ATM Management System");
		lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.ITALIC, 25));
		lblNewLabel.setBounds(238, 0, 305, 70);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(160, 90, 341, 34);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Tìm kiếm tài khoản");
		btnNewButton.addActionListener(this);
		btnNewButton.setIcon(new ImageIcon(ServerGUI.class.getResource("/images/searchlist.png")));
		btnNewButton.setBounds(513, 90, 187, 34);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 136, 687, 274);
		contentPane.add(scrollPane);
		
		title.add("Số tài khoản");
		title.add("Tên");
		title.add("Số điện thoại");
		title.add("Số dư (đ)");
		title.add("Trạng thái");
		
		loadData();
		
		model=new DefaultTableModel(data,title);
		table_1 = new JTable(model);
		table_1.addMouseListener(this);
		scrollPane.setViewportView(table_1);
		
		JButton btnNewButton_1 = new JButton("Xem lịch sử giao dịch");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedRow!=-1) {
					Vector t=(Vector) ServerGUI.data.elementAt(selectedRow);
					String account_number=(String) t.elementAt(0);
					String[] arr=db.getAll(account_number).split("</>");
					new ViewHistoryForm(arr);
					
				}
				
			}
		});
		btnNewButton_1.setBounds(88, 436, 148, 34);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Xóa");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedRow!=-1) {
					if(JOptionPane.showConfirmDialog(contentPane, "Xóa tài khoản này")==0) {
						    //SocketThread.closeConection();
							Vector t=(Vector) ServerGUI.data.elementAt(selectedRow);
							String account_number=(String) t.elementAt(0);
							db.deleteUser(account_number);
							loadData();
					}
				}
			}
		});
		btnNewButton_2.setBounds(263, 436, 90, 34);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Khóa/Kích hoạt");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedRow!=-1) {
					Vector t=(Vector) ServerGUI.data.elementAt(selectedRow);
					String account_number=(String) t.elementAt(0);
					String currentStt=(String) t.elementAt(4);
					String newStt;
					if(currentStt.equals("Kích hoạt")) {
						newStt="Khóa";
					}else {
						newStt="Kích hoạt";
					}
					if(JOptionPane.showConfirmDialog(contentPane, "Tài khoản này đang "+currentStt+".Xác nhận "+newStt)==0) {
						db.updateStatus(account_number, newStt);
						loadData();
					}
				}
			}
		});
		btnNewButton_3.setBounds(372, 436, 126, 34);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Làm mới");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadData();
			}
		});
		btnNewButton_4.setBounds(525, 436, 90, 34);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel_1 = new JLabel("Số tài khoản");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 19));
		lblNewLabel_1.setBounds(35, 90, 113, 27);
		contentPane.add(lblNewLabel_1);
		
		/*JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ServerGUI.class.getResource("/images/background_1.jpg")));
		lblNewLabel_1.setBounds(0, 0, 754, 505);
		contentPane.add(lblNewLabel_1);*/
		
		new ServerATM().start();
		
		
	}
	public static int askSignup(String account,String pass,String name,String sdt) {
		return JOptionPane.showConfirmDialog(contentPane, "Xác nhận đăng ký cho "
				+ "tài khoản:\nSố tài khoản:"+account+"\n Mật khẩu:"
						+ ""+pass+"\nTên chủ tài khoản:"+name
						+"\nSố điện thoại:"+sdt, "Xác nhận đăng ký cho tài khoản", JOptionPane.INFORMATION_MESSAGE);
	}
	@SuppressWarnings("unchecked")
	public static void loadData() {
		data.clear();
		ResultSet rst=db.getUser();
		try {
			while(rst.next()) {
				Vector row=new Vector(5);
				for(int i=1;i<=6;i++) {
					if(i==2) {
						continue;
					}else {
						row.add(rst.getString(i));
					}
				}
				data.add(row);
			}
			model.fireTableDataChanged();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Tìm kiếm tài khoản")) {
			try {
				if(textField.getText().length()>0) {
					ResultSet rs=db.getsearchUser(textField.getText());
						if(rs.next()) {
							data.clear();
							Vector row=new Vector(5);
							for(int i=1;i<=6;i++) {
								if(i==2) {
									continue;
								}else {
									row.add(rs.getString(i));
								}
							}
							data.add(row);
							model.fireTableDataChanged();
							textField.setText("");
						}
				}else {
					JOptionPane.showMessageDialog(contentPane, "Hãy nhập số tài khoản", "", JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e2) {
				
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		selectedRow=table_1.getSelectedRow();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
