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
import com.lian.lianfinaleproject.model.Cart;
import com.lian.lianfinaleproject.model.Group;
import com.lian.lianfinaleproject.services.DatabaseService;
import com.lian.lianfinaleproject.utils.SharedPreferencesUtil;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {
Button goback;
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
        Intent go=new Intent(AdminActivity.this, CartActivity.class);
        startActivity(go);
    }

    public void btnGoToMyList(View view) {
        Intent go=new Intent(AdminActivity.this, CartActivity.class);
        startActivity(go);
    }

    public void btnNewGroupList(View view) {
        Intent go=new Intent(AdminActivity.this, CreateNewGroup.class);
        startActivity(go);
    }

    // this is an example how to crate group:

    private void addGroupToDB(Group groupCart) {
        DatabaseService.getInstance().createNewGroup(groupCart, new DatabaseService.DatabaseCallback<Void>() {
            @Override
            public void onCompleted(Void v) {
                Intent intent = new Intent(AdminActivity.this, Group_Activity.class);
                intent.putExtra("id", groupCart.getId());
                startActivity(intent);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }

    public void btnGoToGroupList(View view) {
        Intent go=new Intent(AdminActivity.this, CartActivity.class);
        startActivity(go);
    }

    public void btnuserlist(View view) {
        Intent go=new Intent(AdminActivity.this, UserListActivity.class);
        startActivity(go);
    }
    public void btn_go_to_profile(View view) {
        Intent go=new Intent(AdminActivity.this, UserProfileActivity.class);
        startActivity(go);
    }


    public void goback(View view) {
        finish();
    }


}