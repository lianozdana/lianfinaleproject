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

public class choice_list_or_grouplist extends AppCompatActivity {
Button btn_go_to_mylist, btn_my_grouplist, btn_new_grouplist, bth_go_to_grouplist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_choice_list_or_grouplist);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_go_to_mylist = findViewById(R.id.btn_go_to_mylist);
        btn_my_grouplist = findViewById(R.id.btn_my_grouplist);
        btn_new_grouplist = findViewById(R.id.btn_new_grouplist);
        bth_go_to_grouplist = findViewById(R.id.bth_go_to_grouplist);



    }

    public void btnMyGroupList(View view) {
        Intent go=new Intent(choice_list_or_grouplist.this, CartActivity.class);
        startActivity(go);
    }

    public void btnGoToMyList(View view) {
        Intent go=new Intent(choice_list_or_grouplist.this, CartActivity.class);
        startActivity(go);
    }

    public void btnNewGroupList(View view) {
        Intent go=new Intent(choice_list_or_grouplist.this, CartActivity.class);
        startActivity(go);
    }

    public void btnGoToGroupList(View view) {
        Intent go=new Intent(choice_list_or_grouplist.this, CartActivity.class);
        startActivity(go);
    }


}
