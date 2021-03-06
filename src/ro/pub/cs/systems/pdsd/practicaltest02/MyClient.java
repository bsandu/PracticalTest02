package ro.pub.cs.systems.pdsd.practicaltest02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import android.util.Log;
import android.widget.TextView;

public class MyClient extends Thread {
	
	private static String TAG = "Client";
	private String   address;
	private int      port;
	private String   city;
	private TextView weatherForecastTextView;
	
	private Socket   socket;
	
	public MyClient(
			String address,
			int port,
			String city,
			TextView weatherForecastTextView) {
		this.address                 = address;
		this.port                    = port;
		this.city                    = city;
		this.weatherForecastTextView = weatherForecastTextView;
	}
	
	@Override
	public void run() {
		try {
			socket = new Socket(address, port);
			if (socket == null) {
				Log.e(TAG, "[CLIENT THREAD] Could not create socket!");
			}
			
			BufferedReader bufferedReader = Utilities.getReader(socket);
			PrintWriter    printWriter    = Utilities.getWriter(socket);
			if (bufferedReader != null && printWriter != null) {
				printWriter.println(city);
				printWriter.flush();
				String weatherInformation;
				while ((weatherInformation = bufferedReader.readLine()) != null) {
					final String finalizedWeatherInformation = weatherInformation;
					weatherForecastTextView.post(new Runnable() {
						@Override
						public void run() {
							weatherForecastTextView.append(finalizedWeatherInformation + "\n");
						}
					});
				}
			} else {
				Log.e(TAG, "[CLIENT THREAD] BufferedReader / PrintWriter are null!");
			}
			socket.close();
		} catch (IOException ioException) {
			Log.e(TAG, "[CLIENT THREAD] An exception has occurred: " + ioException.getMessage());
		}
	}

}
