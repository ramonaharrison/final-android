package nyc.c4q.android.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import nyc.c4q.android.R;

public class LoginActivity extends Activity {

  private EditText emailField;
  private EditText passwordField;
  private Button loginButton;
  private final AuthenticationManager manager;
  private AlertDialog dialog;

  public LoginActivity() {
    // fix this
    manager = new RealAuthenticationManager();
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // load view hierarchy in R.layout.activity_login
    setContentView(R.layout.activity_login);

    // get references to views, and other setup
    emailField = (EditText) findViewById(R.id.email);
    passwordField = (EditText) findViewById(R.id.password);
    loginButton = (Button) findViewById(R.id.login);
    setupAlertDialog();

    // call checkCredentials via OnClickListener
    loginButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        checkCredentials(email, password);
      }
    });
  }

  private void setupAlertDialog() {
    // 1. Instantiate an AlertDialog.Builder with its constructor
    AlertDialog.Builder builder = new AlertDialog.Builder(this);

// 2. Chain together various setter methods to set the dialog characteristics
    builder.setMessage(R.string.alert_dialog_body)
            .setTitle(R.string.alert_dialog_title);

    builder.setNegativeButton(R.string.alert_dialog_dismiss, new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
        dialog.dismiss();
      }
    });

// 3. Get the AlertDialog from create()
    dialog = builder.create();
  }

  private void checkCredentials(String email, String password) {
    if(manager.validateLogin(email, password)) {
      // go to EmailListActivity
      Intent intent = new Intent(this, EmailListActivity.class);
      startActivity(intent);
    }
    else {
      // launch alert dialog on failed login
      dialog.show();
    }
  }
}
