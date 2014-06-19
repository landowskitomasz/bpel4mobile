package com.bpel4mobile.hotel.android.activity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.bpel4mobile.hotel.android.R;
import com.bpel4mobile.hotel.android.service.AuthenticationService;
import com.bpel4mobile.hotel.android.service.SessionManager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;


public class LoginActivity extends FragmentActivity {

	public static final String LOGIN_ACTION = "com.bpel4Mobile.hotel.LOGIN";

	private SessionManager sessionManager;
	
	private AuthenticationService authenticationService;

    private EditText loginEditText;

    private EditText passwordEditText;

    private String emptyLoginErrorMessage;

    private String emptyPasswordErrorMessage;

    private String wrongLoginOrPasswordErrorMessage;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.sessionManager = new SessionManager(this);
        if(this.sessionManager.isLoggedIn()){
            if(LOGIN_ACTION.equals(getIntent().getAction())){
                sessionManager.logoutUserQuietly();
            } else {
                navigateToMainActivity();
            }
        }
        this.authenticationService = new AuthenticationService(this);

        loginEditText = (EditText) findViewById(R.id.txtUsernameLoginFrgm);
        passwordEditText = (EditText) findViewById(R.id.txtPasswordLoginFrgm);

        wrongLoginOrPasswordErrorMessage = getString(R.string.wrong_login_or_password_error);
        emptyLoginErrorMessage = getString(R.string.empty_login_error);
        emptyPasswordErrorMessage = getString(R.string.empty_password_error);

        Button loginButton = (Button) findViewById(R.id.btnLoginLoginFrgm);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLogin();
            }
        });
    }

    private void navigateToMainActivity() {
        ContentResolver.requestSync(sessionManager.getCurrentUser(), "com.bpel4mobile.hotel.android.syncdata.provider", Bundle.EMPTY);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


	public void onLogin() {
		String login = getLogin();
		String password = getPassword();
        if( login == null || password == null ){
            return;
        }
        final ProgressDialog dialog = ProgressDialog.show(this, getString(R.id.progress_dialog_title), getString(R.id.logging_in_progress));
		new AsyncTask<String, Void, Boolean>() {
			@Override
			protected Boolean doInBackground(String... credentials) {
				
				 boolean result = authenticationService.authenticate(credentials[0], credentials[1]);
				 if(result){
					 sessionManager.createLoginSession(credentials[0], credentials[1]);
				 }
				 return result;
			}
			
			protected void onPostExecute(Boolean result) {
                dialog.dismiss();
				if(result){
					navigateToMainActivity();
				}
				else{
					displayWrongLoginOrPassword();
				}
			};
		}.execute(new String[]{ login, password});
	}

    public String getLogin(){
        String login = loginEditText.getText().toString();

        if(login == null || login.isEmpty()){
            loginEditText.setError(emptyLoginErrorMessage);
            return null;
        }
        return login;
    }

    public String getPassword(){
        String password = passwordEditText.getText().toString();
        if(password == null || password.isEmpty()){
            passwordEditText.setError(emptyPasswordErrorMessage);
            return null;
        }
        return password;
    }

    public void displayWrongLoginOrPassword() {
        passwordEditText.setError(wrongLoginOrPasswordErrorMessage);
    }
}
