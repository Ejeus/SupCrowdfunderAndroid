package com.supinfo.supcrowdfunder.activities;

import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.R.layout;
import com.supinfo.supcrowdfunder.R.menu;
import com.supinfo.supcrowdfunder.dao.DaoFactory;
import com.supinfo.supcrowdfunder.entity.User;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditProfileActivity extends Activity {
	private EditText mFirstNameView;
	private EditText mLastNameView;
	private EditText mPasswordView;
	private EditText mPasswordConfirmView;
	private EditText mCurrentPasswordView;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_profile);
		
		mFirstNameView = (EditText) findViewById(R.id.firstname);
		mLastNameView = (EditText) findViewById(R.id.lastname);
		mPasswordView = (EditText) findViewById(R.id.password);
		mPasswordConfirmView = (EditText) findViewById(R.id.passwordConfirm);
		mCurrentPasswordView = (EditText) findViewById(R.id.currentPassword);
		
		SupCrowdFunderApp app = (SupCrowdFunderApp) getApplication();
		mFirstNameView.setText(app.getUser().getFirstName());
		mLastNameView.setText(app.getUser().getLastName());
		
		Button editButton = (Button) findViewById(R.id.edit_profile_button);
		editButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				editProfile();
			}
		});
	}

	protected void editProfile() {
		SupCrowdFunderApp app = (SupCrowdFunderApp) getApplication();
		User currentUser = app.getUser();
		
		String firstName = mFirstNameView.getText().toString();
		String lastName = mLastNameView.getText().toString();
		String password = mPasswordView.getText().toString();
		String passwordConfirm = mPasswordConfirmView.getText().toString();
		String currentPassword = mCurrentPasswordView.getText().toString();
		
		if(!currentPassword.equals(currentUser.getPassword())) {
			Toast toast = Toast.makeText(this, "Your current password isn't correct.", 500);
			toast.show();
			
			return;
		}
		
		if(!password.equals(passwordConfirm)) {
			Toast toast = Toast.makeText(this, "Your password doesn't match password confirmation.", 500);
			toast.show();
			
			return;
		}
		
		User user = new User();
		user.setId(currentUser.getId());
		user.setEmail(currentUser.getEmail());
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		
		user = DaoFactory.getUserDao().updateUser(user);
		
		if(user != null) {
			app.setUser(user);
			finish();
		}
		else {
			Toast toast = Toast.makeText(this, "An error happened. Please recheck your informations.", 500);
			toast.show();
		}
	}

}
