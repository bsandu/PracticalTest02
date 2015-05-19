package ro.pub.cs.systems.pdsd.practicaltest02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class MyServer  extends Thread {
	
	private int          port         = 0;
	private ServerSocket serverSocket = null;
	private static String TAG = "Server";
	
	public MyServer() {
		try {
			this.serverSocket = new ServerSocket(0);
			this.port = this.serverSocket.getLocalPort();
		} catch (IOException ioException) {
			Log.e(TAG, "An exception has occurred: " + ioException.getMessage());
		}
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public int getPort() {
		return port;
	}
	
	public void setServerSocker(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	
	public ServerSocket getServerSocket() {
		return serverSocket;
	}
	
	@Override
	public void run() {
		try {		
			while (!Thread.currentThread().isInterrupted()) {
				Log.i(TAG, "[SERVER] Waiting for a connection...");
				Socket socket = serverSocket.accept();
				Log.i(TAG, "[SERVER] A connection request was received from " + socket.getInetAddress() + ":" + socket.getLocalPort());
				
				BufferedReader bufferedReader = Utilities.getReader(socket);
				PrintWriter    printWriter    = Utilities.getWriter(socket);
				String query = bufferedReader.readLine();
//				
//				HttpClient httpClient = new DefaultHttpClient();
//				HttpGet httpGet = new HttpGet("http://autocomplete.wunderground.com/aq?query=hel");
//				HttpResponse httpGetResponse = httpClient.execute(httpGet);
//				
//				ResponseHandler<String> responseHandler = new BasicResponseHandler();
//				String pageSourceCode = httpClient.execute(httpGet, responseHandler);
//
//				Log.d(TAG,pageSourceCode);

				String pageSourceCode = 'affasd';
				
				printWriter.println(pageSourceCode);
				printWriter.flush();
				
				
			}			
		} catch (ClientProtocolException clientProtocolException) {
			Log.e(TAG, "An exception has occurred: " + clientProtocolException.getMessage());
		} catch (IOException ioException) {
			Log.e(TAG, "An exception has occurred: " + ioException.getMessage());
		}
	}
	
	public void stopThread() {
		if (serverSocket != null) {
			interrupt();
			try {
				serverSocket.close();
			} catch (IOException ioException) {
				Log.e(TAG, "An exception has occurred: " + ioException.getMessage());
			}
		}
	}

}
