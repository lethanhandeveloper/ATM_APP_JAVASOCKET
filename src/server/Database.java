package server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class Database {
	Connection con;
    PreparedStatement pst;
    Statement stt;
    ResultSet rs;
    public String user_info;
	public String getUser_info() {
		return user_info;
	}
	public Database() {
		 try{
			 	Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atmdb", "root",
						"19062001");
	            pst=con.prepareStatement("select * from user where account_number=? and pass=?");  
	            stt=con.createStatement();
	      }
	      catch (Exception e) {
	            System.out.println(e);
	      }
	}
	public int checkLogin(String uname,String pwd){
        try {         
            pst.setString(1, uname); 
            pst.setString(2, pwd);    
            rs=pst.executeQuery();
            if(rs.next()){
                if(rs.getString("status").equals("Kích hoạt")) {
                	return 1;
                }else {
                	return 0;
                }
            }
            else{
                return -1;
            }
        } catch (Exception e) {
            return -1;
        }
    }
	public Boolean new_signup(String account,String pass,String name,String sdt) {
		try {
			stt.executeUpdate("insert into user values(\""+account+"\",\""+pass+"\",\""+name+"\",\""+sdt+"\",0,\""+"Kích hoạt"+"\")");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public Boolean checkExist(String number_account) {
		try {
			rs=stt.executeQuery("select * from user where account_number=\""+number_account+"\"");
			//System.out.println(rs.getString(1)+"-"+rs.getString("pass")+"-"+rs.getString("name")+"-"+rs.getString("sdt")+"-"+rs.getString("sodu"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(rs.next()) {
				return true;
			}
			else return false;
		} catch (SQLException e) {
			return false;
		}
	}
	public int getcurrentBalance(String number_account) {
		int sodu = 0;
		try {
			rs=stt.executeQuery("select sodu from user where account_number=\""+number_account+"\"");
			rs.next();
			sodu=rs.getInt("sodu");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sodu;
	}
	public void updateBalance(String number_account,int new_balance,String msg) {
		try {
			stt.executeUpdate("update user set sodu="+new_balance+" where account_number=\""+number_account+"\"");
			String history="insert into history values (\""+number_account+"\",\""+getTime()+msg+"\")";
			stt.executeUpdate(history);
		} catch (SQLException e) {
		
		}
	}
	public String getTime() {
		Date today=new Date(System.currentTimeMillis());;
		SimpleDateFormat timeFormat= new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
		return "["+timeFormat.format(today.getTime())+"]:";
	}
	public String getAll(String number_account) {
		String str="";
		try {
			rs=stt.executeQuery("select * from user where account_number=\""+number_account+"\"");
			rs.next();
			str=rs.getString("account_number")+"</>"+rs.getString("name")+"</>"+rs.getString("sdt")+"</>"+rs.getString("sodu")+"</>";
			rs=stt.executeQuery("select * from history where account_number=\""+number_account+"\"");
			while(rs.next()) {
				str+=rs.getString("history_msg")+"</>";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	public String resetPW(String number_account,String old_pw,String new_pw) {
		try {
			rs=stt.executeQuery("select pass from user where account_number=\""+number_account+"\"");
			rs.next();
			if(rs.getString("pass").equals(old_pw)) {
				stt.executeUpdate("update user set pass="+new_pw+" where account_number=\""+number_account+"\"");
				return "PASS_SUCCESSFULLY";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ERROR";
	}
	public ResultSet getUser() {
		try {
			rs=stt.executeQuery("select * from user");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public ResultSet getsearchUser(String number_account) {
		ResultSet rst=null;
		try {
			rst=stt.executeQuery("select * from user where account_number=\""+number_account+"\"");
		} catch (Exception e) {
			return rst;
		}
		return rst;
	}
	public void deleteUser(String account_number) {
		try {
			stt.executeUpdate("delete from history where account_number=\""+account_number+"\"");
			stt.executeUpdate("delete from user where account_number=\""+account_number+"\"");
		} catch (Exception e) {
			
		}
	}
	public void updateStatus(String account_number,String newStt) {
		try {
			stt.executeUpdate("update user set status=\""+newStt+"\" where account_number=\""+account_number+"\"");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
