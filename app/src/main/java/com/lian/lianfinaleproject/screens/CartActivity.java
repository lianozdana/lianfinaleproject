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

public class CartActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnAddItem ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            return insets;


        });
        btnAddItem = findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent go=new Intent(CartActivity.this, AddItem.class);
        startActivity(go);

    }

    public void bthAddItem(View view) {
    }
}