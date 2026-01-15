package com.lian.lianfinaleproject.screens;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.lian.lianfinaleproject.model.Cart;
import com.lian.lianfinaleproject.model.Group;
import com.lian.lianfinaleproject.model.User;
import com.lian.lianfinaleproject.services.DatabaseService;
import com.lian.lianfinaleproject.utils.SharedPreferencesUtil;

import java.util.ArrayList;

public class CreateNewGroup extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    private EditText etName, etCode;
    private Button btnAddGroup;
    private DatabaseService databaseService;



User currentUser=null;
    private int code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_new_group);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        databaseService = DatabaseService.getInstance();

        /// get the views
        etCode = findViewById(R.id.et_Group_Code);
        etName = findViewById(R.id.et_Group_Name);
         code = Group.generateGroupCode();

        etCode.setText(code+"");


        //getUser
        databaseService.getUser(new DatabaseService.DatabaseCallback<User>() {
            @Override
            public void onCompleted(User user) {
                currentUser=user;
            }



            @Override
            public void onFailed(Exception e) {

            }
        });


    }








    public void createNewGroup(View view) {
     
        String groupName = etName.getText().toString();
        String groupCode = etCode.getText().toString();

        code=Integer.parseInt(groupCode);

        String id = DatabaseService.getInstance().generateGroupId();




            Group newGroup= new Group(id, groupName,currentUser , new ArrayList<>(), new Cart(), code);


            databaseService.createNewGroup(newGroup, new DatabaseService.DatabaseCallback<Void>() {
                @Override
                public void onCompleted(Void object) {
                    Intent go=new Intent(CreateNewGroup.this, Group_Activity.class);
                    startActivity(go);
                }

                @Override
                public void onFailed(Exception e) {

                }
            });

    }
}