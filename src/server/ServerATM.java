package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerATM extends Thread {
	public static ArrayList<Socket> listSK=new ArrayList<Socket>();
	@Override
	public void run() {
		try {
			ServerSocket server =new ServerSocket(8888);
			System.out.println("Server is ready...");
			while(true) {
				Socket socket =server.accept();
				listSK.add(socket);
				new SocketThread(socket).start();
			}
		} catch (IOException e) {
			System.out.println("Client đã ngắt kết nối");
		}
	}
}
