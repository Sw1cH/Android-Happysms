package com.sw1ch.happysms;

import java.util.ArrayList;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements TextWatcher {
	private static final String TAG = "OnClick";
	private String phone_number = "28814070";
	private int x;
	private String[] messages;
	private SmsManager smsMgr;
	private ArrayList<String> message;
	private String number_text;
	private String number_hint;
	private EditText number_view;
	private Button number_button;
	private TextView numberInfo;

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		String number_input = s.toString();
		if (number_input.equals(number_hint))
			numberInfo.setVisibility(TextView.VISIBLE);
		else
			numberInfo.setVisibility(TextView.INVISIBLE);

		if (!(number_input.length() == 8)) {
			number_button.setEnabled(false);
		} else {
			number_button.setEnabled(true);
		}

		if (number_input.equals("20382813")) {
			numberInfo.setText("trollface.jpg");
			number_button.setEnabled(false);
			numberInfo.setVisibility(TextView.VISIBLE);
		} else {
			numberInfo.setText("Этот номер уже введен");
			numberInfo.setVisibility(TextView.INVISIBLE);
		}
		Log.d(TAG, "input : " + number_input + " and hint: " + number_hint);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		smsMgr = SmsManager.getDefault();

		// get string resources
		Resources res = getResources();
		messages = res.getStringArray(R.array.message);

		number_view = (EditText) findViewById(R.id.number);
		number_view.addTextChangedListener(this);
		number_hint = number_view.getHint().toString();
		number_button = (Button) findViewById(R.id.change_number);
		numberInfo = (TextView) findViewById(R.id.info);
	}

	// OnClick method for send button
	public void sendSMS(View v) {
		x = (int) (Math.random() * (messages.length + 1));
		SmsManager smsMgr = SmsManager.getDefault();
		phone_number = number_view.getHint().toString();
		Log.d(TAG, "got edit_hint: " + number_hint);
		message = smsMgr.divideMessage(messages[x]);
		smsMgr.sendMultipartTextMessage(phone_number, null, message, null, null);
		Toast.makeText(this, "Красава!", Toast.LENGTH_SHORT).show();
		Log.d(TAG, "sms send to " + phone_number);
	}

	// OnClick method for save button
	public void changeNumber(View v) {
		// get the number from view
		number_text = number_view.getText().toString();
		Log.d(TAG, "got edit_number: " + number_text);

		// get the hint from view
		number_hint = number_view.getHint().toString();
		Log.d(TAG, "got edit_hint: " + number_hint);

		number_view.setHint(number_text);
		Log.d(TAG, "set phone_number hint: " + number_text);

	}
}
