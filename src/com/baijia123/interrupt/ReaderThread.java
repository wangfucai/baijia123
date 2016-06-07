package com.baijia123.interrupt;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ReaderThread extends Thread {
	private final Socket socket;
	private final InputStream in;

	public ReaderThread(Socket socket) throws IOException {
		super();
		this.socket = socket;
		this.in = socket.getInputStream();
	}

	@Override
	public void interrupt() {
		// TODO Auto-generated method stub
		try {
			socket.close();
		} catch (IOException ignored) {

		} finally {
			super.interrupt();
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			byte[] buf = new byte[1024];
			while (true) {
				int count = in.read(buf);
				if (count < 0)
					break;
				else if (count > 0)
					processBuffer(buf, count);
			}
		} catch (IOException e) {

		}
	}

	void processBuffer(byte[] buf, int count) {

	}

}
