package com.lian.lianfinaleproject.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.lian.lianfinaleproject.R;
import com.lian.lianfinaleproject.utils.SharedPreferencesUtil;

public class MainActivity extends AppCompatActivity {

    Button btnSignUp, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            return insets;
        });

      // if (SharedPreferencesUtil.isUserLoggedIn(MainActivity.this)) {
        //    Intent intent = new Intent(MainActivity.this, CartActivity.class);
        //    startActivity(intent);
        //      finish();
     //     return;
     // }

        btnSignUp = findViewById(R.id.btn_main_to_signup);
        btnLogin = findViewById(R.id.btn_main_to_login);

        btnSignUp.setOnClickListener(MainActivity.this::btnGoSighUp);
        btnLogin.setOnClickListener(MainActivity.this::btnGoLogIn);
    }

    public void btnGoSighUp(View view) {
        Intent go=new Intent(MainActivity.this, SignUp.class);
        startActivity(go);
    }

    public void btnGoLogIn(View view) {
        Intent go=new Intent(MainActivity.this, LogIn.class);
        startActivity(go);
    }
}