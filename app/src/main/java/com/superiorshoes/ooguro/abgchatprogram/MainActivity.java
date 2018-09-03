package com.superiorshoes.ooguro.abgchatprogram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.quickblox.auth.session.QBSettings;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

public class MainActivity extends AppCompatActivity {

    static final String APP_ID = "73474";
    static final String AUTH_KEY = "gKOzN4CfyQBGTHR";
    static final String AUTH_SECRET = "rHqE5u32MB3tRqz";
    static final String ACCOUNT_KEY = "9zkLsucEageXRHTPArvS";

    Button btnLogin, btnSignUp;
    EditText ssUser,ssPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeFramwork();

        btnLogin = (Button)findViewById(R.id.main_btnLogin);
        btnSignUp = (Button)findViewById(R.id.main_btnSignUp);

        ssUser = (EditText)findViewById(R.id.main_editLogin);
        ssPassword = (EditText)findViewById(R.id.main_editPassword);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SignUpActivity.class));

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = ssUser.getText().toString();
                String password = ssPassword.getText().toString();

                QBUser qbUser = new QBUser(user,password);

                QBUsers.signIn(qbUser).performAsync(new QBEntityCallback<QBUser>() {
                    @Override
                    public void onSuccess(QBUser qbUser, Bundle bundle) {
                        Toast.makeText(getBaseContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(QBResponseException e) {
                        Toast.makeText(getBaseContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private void initializeFramwork() {
        QBSettings.getInstance().init(getApplicationContext(),APP_ID,AUTH_KEY,AUTH_SECRET);
        QBSettings.getInstance().setAccountKey(ACCOUNT_KEY);

    }
}
