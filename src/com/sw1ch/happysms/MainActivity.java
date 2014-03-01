package com.sw1ch.happysms;

import java.util.ArrayList;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	private String phone_number = "28814070"; 
	private int x;
	private String[] messages;
	private SmsManager smsMgr;
	private ArrayList<String> message;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		smsMgr = SmsManager.getDefault();
		Resources res = getResources();
		messages = res.getStringArray(R.array.message);
		x = 0;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void sendSMS(View v) {		
		x=  (int)(Math.random() * (messages.length + 1));
		SmsManager smsMgr = SmsManager.getDefault();
		message = smsMgr.divideMessage(messages[x]);
		smsMgr.sendMultipartTextMessage(phone_number, null, message , null, null);
		Toast.makeText(this, "Красава!" , Toast.LENGTH_SHORT).show();
	}
}
