package com.lian.lianfinaleproject.screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.lian.lianfinaleproject.R;
import com.lian.lianfinaleproject.model.User;
import com.lian.lianfinaleproject.services.DatabaseService;
import com.lian.lianfinaleproject.utils.SharedPreferencesUtil;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RegisterActivity";

    private EditText etUEmail, etUPassword, etUFName, etULName, etUPhone;
    private Button btnSighUp, btnGoHome;
    private DatabaseService databaseService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            return insets;
        });

        databaseService = DatabaseService.getInstance();

        /// get the views
        etUEmail = findViewById(R.id.etUemail);
        etUPassword = findViewById(R.id.etUpassword);
        etUFName = findViewById(R.id.etUfname);
        etULName = findViewById(R.id.etUlname);
        etUPhone = findViewById(R.id.etUphone);
        btnSighUp = findViewById(R.id.btnSighUp);
        btnGoHome = findViewById(R.id.btnGoHome);

        /// set the click listener
        btnSighUp.setOnClickListener(this);
        btnGoHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnSighUp.getId()) {
            Log.d(TAG, "onClick: Register button clicked");

            /// get the input from the user
            String email = etUEmail.getText().toString();
            String password = etUPassword.getText().toString();
            String fName = etUFName.getText().toString();
            String lName = etULName.getText().toString();
            String phone = etUPhone.getText().toString();


            Log.d(TAG, "onClick: Registering user...");

            /// Register user
            registerUser(fName, lName, phone, email, password);

        }

        if (v.getId() == btnGoHome.getId()) {
            finish();
        }
    }

    /// Register the user
    private void registerUser(String fname, String lname, String phone, String email, String password) {
        Log.d(TAG, "registerUser: Registering user...");


        /// create a new user object
        User user = new User("kklkl", fname, lname, phone, email, password);

        /// proceed to create the user
        createUserInDatabase(user);

    }


    private void createUserInDatabase(User user) {
        databaseService.createNewUser(user, new DatabaseService.DatabaseCallback<String>() {
            @Override
            public void onCompleted(String uid) {
                Log.d(TAG, "createUserInDatabase: User created successfully");
                /// save the user to shared preferences
                user.setId(uid);
                Log.d(TAG, "createUserInDatabase: Redirecting to MainActivity");

                SharedPreferencesUtil.saveUser(SignUp.this, user);

                /// Redirect to MainActivity and clear back stack to prevent user from going back to register screen
                Intent mainIntent = new Intent(SignUp.this, CartActivity.class);
                /// clear the back stack (clear history) and start the MainActivity
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(mainIntent);
            }

            @Override
            public void onFailed(Exception e) {
                Log.e(TAG, "createUserInDatabase: Failed to create user", e);
                /// show error message to user
                Toast.makeText(SignUp.this, "Failed to register user", Toast.LENGTH_SHORT).show();
                /// sign out the user if failed to register

                SharedPreferencesUtil.signOutUser(SignUp.this);

            }
        });


    }


}