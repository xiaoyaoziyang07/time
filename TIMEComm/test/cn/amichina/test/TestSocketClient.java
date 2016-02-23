package cn.amichina.test;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestSocketClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		String ip = "127.0.0.1";
		Socket socket =new Socket(ip,12580);
	}

}
