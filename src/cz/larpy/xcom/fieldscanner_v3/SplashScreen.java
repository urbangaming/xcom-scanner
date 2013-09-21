package cz.larpy.xcom.fieldscanner_v3;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class SplashScreen extends Activity {
	private static int SPLASH_MESSAGE_SWITCH = 1500;
	private static int SPLASH_MESSAGE_DOT = 450;
	private static int messages[] = { R.string.splash_message_1_kernel_initialization,
									  R.string.splash_message_2_key_verification,
									  R.string.splash_message_3_sensor_calibration,
									  R.string.splash_message_4_nsa_notification,
									  R.string.splash_message_5_facebook_scan };
	private static int counter = 0;
	
	private Runnable toggleMessageHandler = new Runnable() {
		@Override
		public void run() {
			toggleMessage();
		}
	};
	
	private Runnable dotter = new Runnable() {
		
		@Override
		public void run() {
			dotMessage();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		new Handler().postDelayed(toggleMessageHandler, SPLASH_MESSAGE_SWITCH);
		new Handler().postDelayed(dotter, SPLASH_MESSAGE_DOT);
	}
	
	private void toggleMessage() {
		counter++;
		if (counter < messages.length) {
			TextView tv = (TextView)findViewById(R.id.xcomSplashMessage);
			tv.setText(messages[counter]);
			new Handler().postDelayed(toggleMessageHandler, SPLASH_MESSAGE_SWITCH);
		} else {
			Intent i = new Intent(SplashScreen.this, HubActivity.class);
			startActivity(i);
			finish();
		}
	}
	
	private void dotMessage() {
		TextView tv = (TextView)findViewById(R.id.xcomSplashMessage);
		tv.setText("*" + tv.getText() + "*");
		new Handler().postDelayed(dotter, SPLASH_MESSAGE_DOT);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
		return true;
	}

}
