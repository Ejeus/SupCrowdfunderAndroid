package com.supinfo.supcrowdfunder.activities;

import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.R.layout;
import com.supinfo.supcrowdfunder.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends Activity {

	private String mEmail;
	private String mPassword;
	private String mPasswordConfirm;
	private String mFirstName;
	private String mLastName;
	
	private EditText mEmailView;
	private EditText mPasswordView;
	private EditText mPasswordConfirmView;
	private EditText mFirstNameView;
	private EditText mLastNameView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		mEmailView = (EditText) findViewById(R.id.email);
		mPasswordView = (EditText) findViewById(R.id.password);
		mPasswordConfirmView = (EditText) findViewById(R.id.passwordConfirm);
		mFirstNameView = (EditText) findViewById(R.id.firstname);
		mLastNameView = (EditText) findViewById(R.id.lastname);
		
		
		findViewById(R.id.register_button).setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					attendRegister();        
				}
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	
		
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}
	
	public void attendRegister() {
		mEmail = mEmailView.getText().toString();
		mFirstName = mFirstNameView.getText().toString();
		mLastName = mLastNameView.getText().toString();
		mPassword = mPasswordView.getText().toString();
		mPasswordConfirm = mPasswordConfirmView.getText().toString();
		
		System.out.println(mEmail);
	}

}
