package cn.amichina.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import com.sun.mail.iap.ByteArray;

public class TestSocketServer {

	public static void main(String[] args) throws UnknownHostException, IOException {
		ServerSocket server =new ServerSocket(12580);
		String data ="11";
		byte [] bytes =data.getBytes();
		if(bytes.length>1){
			int  i =bytes[0];
			char c =8888;
			System.out.println(c);
			System.out.println(i);
			System.out.println(Integer.toBinaryString(127));
		}
	/*	System.out.println(data.getBytes());
		Socket socket =server.accept();
		OutputStream outStream =socket.getOutputStream();*/
	}

}
