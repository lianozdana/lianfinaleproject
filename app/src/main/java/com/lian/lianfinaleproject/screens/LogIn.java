package com.lian.lianfinaleproject.screens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.lian.lianfinaleproject.R;
import com.lian.lianfinaleproject.model.User;
import com.lian.lianfinaleproject.services.DatabaseService;
import com.lian.lianfinaleproject.utils.SharedPreferencesUtil;

public class LogIn extends AppCompatActivity {

    EditText editTextEmail,editTextPassword;
    Button btnLogIn;

    String emailUserInput, passwordUserInput;
    DatabaseService databaseService;

    SharedPreferences sharedPreferences;

    public static final String myPrefSTRING="myPref";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            return insets;
        });


        sharedPreferences=getSharedPreferences(myPrefSTRING,MODE_PRIVATE);

        emailUserInput=sharedPreferences.getString("email","");
        passwordUserInput=sharedPreferences.getString("password","");



        databaseService = DatabaseService.getInstance();

        editTextPassword=findViewById(R.id.editTextPassword);
        editTextEmail=findViewById(R.id.editTextEmail);
        btnLogIn=findViewById(R.id.btnLogIn);


        editTextEmail.setText(emailUserInput);
        editTextPassword.setText(passwordUserInput);


    }

    private void loginUser(String emailUserInput, String passwordUserInput) {
        databaseService.LoginUser(emailUserInput, passwordUserInput, new DatabaseService.DatabaseCallback<String>() {
            @Override
            public void onCompleted(String userId) {
                saveUserById(userId);


                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("email", emailUserInput);
                editor.putString("password", passwordUserInput);
                editor.commit();
            }

            @Override
            public void onFailed(Exception e) {
                SharedPreferencesUtil.signOutUser(LogIn.this);
            }
        });
    }

    private void saveUserById(String userId) {
        databaseService.getUser(userId, new DatabaseService.DatabaseCallback<User>() {
            @Override
            public void onCompleted(User currentUser) {
              SharedPreferencesUtil.saveUser(LogIn.this, currentUser);


                Intent go=new Intent(LogIn.this,CartActivity.class);
                startActivity(go);

            }

            @Override
            public void onFailed(Exception e) {
                SharedPreferencesUtil.signOutUser(LogIn.this);
            }
        });
    }

    public void btnGoLogIn(View view) {
        emailUserInput=editTextEmail.getText().toString()+"";
        passwordUserInput=editTextPassword.getText().toString()+"";

        loginUser(emailUserInput, passwordUserInput);
    }
}