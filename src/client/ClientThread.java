package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import server.ServerGUI;


public class ClientThread extends Thread{
	private Socket socket;
	private static DataOutputStream dos;
	private static DataInputStream dis;
	private static String receive;
	public ClientThread() {
		setPriority(MAX_PRIORITY);
	}
	@Override
	public void run() {
		try {
			socket=new Socket(InetAddress.getByName("localhost"),8888);
			dos=new DataOutputStream(socket.getOutputStream());
			dis=new DataInputStream(socket.getInputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String getReceive() {
		return receive;
	}
	public static void write(String str) {
		try {
			dos.writeUTF(str);
			receive=dis.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
