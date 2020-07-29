package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.mysql.cj.protocol.Resultset;

public class SocketThread extends Thread {
	private Socket socket;
	private Database db;
	DataInputStream dis;
	static DataOutputStream dos;
	private int current_balance;
	public SocketThread(Socket socket) {
		this.socket=socket;
		db=new Database();
	}
	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		try {
			dis=new DataInputStream(socket.getInputStream());
			dos=new DataOutputStream(socket.getOutputStream());
			while(true) {
				String msg=dis.readUTF();
				String s[]=msg.split("-");
				switch (s[0]) {
				case "1":
					int check=db.checkLogin(s[1], s[2]);
					if(check==1) {
						dos.writeUTF("LOGIN_SUCCESSFULLY");
						dos.flush();
					}else if(check==-1) {
						dos.writeUTF("LOGIN_FAILED");
						dos.flush();
					}else {
						dos.writeUTF("LOGIN_DENIED");
						dos.flush();
					}
					break;
				case "2":
					if(ServerGUI.askSignup(s[1],s[2],s[3],s[4])==0) {
						Boolean check_signup=db.new_signup(s[1],s[2],s[3],s[4]);
						if(check_signup) {
							dos.writeUTF("SIGNUP_SUCCESSFULLY");
							ServerGUI.loadData();
							
						}else {
							dos.writeUTF("SIGNUP_FAILED");
							dos.flush();
						}
					}else {
						dos.writeUTF("DENIED");
					}
					break;
				case "3":
					withdrawal_money(s[1],Integer.parseInt(s[2]));
					break;
				case "4":
					transferMoney(s[1],s[2],Integer.parseInt(s[3]));
					break;
				case "5":
					String send="";
					String str=db.getAll(s[1]);
					try {
							dos.writeUTF(str);
							dos.flush();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case "6":
					try {
						depositMoney(s[1],Integer.parseInt(s[2].replace(".", "")));
						dos.flush();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case "7":
					String check_reset=db.resetPW(s[1], s[2], s[3]);
					dos.writeUTF(check_reset);
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/*public static void closeConection() {
		try {
			socket.close();
		} catch (Exception e) {
			
		}
	}*/
	public synchronized void withdrawal_money(String my_number,int money) {
		current_balance=db.getcurrentBalance(my_number);
		if(current_balance<money) {
			try {
				dos.writeUTF("NOT_ENOUGH");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			int new_balance=current_balance-money;
			db.updateBalance(my_number,new_balance,"Đã rút "+money+"đ.Số dư hiện tại "+new_balance+"đ.");
			try {
				dos.writeUTF("OK");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
	}
	@SuppressWarnings("unused")
	public void transferMoney(String my_number,String toUser,int money) {
		int current_Balance=db.getcurrentBalance(my_number);
		if(current_Balance<money) {
			try {
				dos.writeUTF("NOT_ENOUGH");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			Boolean check_user=db.checkExist(toUser);
			if(!check_user) {
				try {
					dos.writeUTF("NOT_EXISTS");
					return;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				int current_other_Balance=db.getcurrentBalance(toUser);
				try {
					db.con.setAutoCommit(false);
					int new_balance=current_Balance-money;
					db.updateBalance(my_number, new_balance,"Đã chuyển "+money+"đ cho "+toUser+".Số dư hiện tại "+new_balance+"đ.");
					new_balance=current_other_Balance+money;
					db.updateBalance(toUser, new_balance,my_number+" đã chuyển "+money+"đ vào tài khoản"+".Số dư hiện tại "+new_balance+"đ.");
					db.con.commit();
					dos.writeUTF("SUCCESSFULLY_TRANSFER");
				} catch (Exception e) {
					try {
						db.con.rollback();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		}
		
	}
	public void depositMoney(String my_account,int deposit_money) {
		
		int current_money=db.getcurrentBalance(my_account);
		System.out.println(current_money);
		current_money+=deposit_money;
		db.updateBalance(my_account, current_money,"Đã nạp "+deposit_money+"đ vào tài khoản.Số dư hiện tại "+current_money+"đ.");
		try {
			dos.writeUTF("SUCCESSFULLY_DEPOSIT");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
