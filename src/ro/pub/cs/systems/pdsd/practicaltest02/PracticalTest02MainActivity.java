package ro.pub.cs.systems.pdsd.practicaltest02;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PracticalTest02MainActivity extends Activity {

	private static String TAG = "MAIN";
	private MyServer myServer = null;
	private MyClient myClient = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test02_main);
		
		myServer = new MyServer();
		if (myServer.getServerSocket() != null) {
			myServer.start();
		} else {
			Log.e(TAG, "[MAIN ACTIVITY] Could not creat server thread!");
		}
		
		
		Button button = (Button)findViewById(R.id.button_search);
		
		
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText editText = (EditText)findViewById(R.id.editText_query);
				TextView textView = (TextView)findViewById(R.id.textView1);
				// TODO Auto-generated method stub
				String query = editText.getText().toString();
				if(query == null || query.isEmpty()) {
					Log.e(TAG,"Query shouldn't be an empty string");
					return;
				}
				Log.d(TAG,"" + myServer.getPort());
				
				myClient = new MyClient(
						"localhost", 
						myServer.getPort(), 
						editText.getText().toString(), 
						textView);
				myClient.start();
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test02_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
