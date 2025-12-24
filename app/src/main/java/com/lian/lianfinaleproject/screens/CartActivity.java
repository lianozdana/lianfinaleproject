package com.lian.lianfinaleproject.screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.lian.lianfinaleproject.R;
import com.lian.lianfinaleproject.adapters.CartAdapter;
import com.lian.lianfinaleproject.model.Cart;
import com.lian.lianfinaleproject.model.Item;
import com.lian.lianfinaleproject.services.DatabaseService;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnAddItem ;

    RecyclerView rcCartItems;

    DatabaseService databaseService;

  Cart cart;

  CartAdapter cartAdapter;



    String uid;



    FirebaseAuth mAuth;





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


        // שירותים
        mAuth = FirebaseAuth.getInstance();
        uid = mAuth.getUid();

        databaseService = DatabaseService.getInstance();


        rcCartItems = findViewById(R.id.rcCartItems);
        rcCartItems.setLayoutManager(new LinearLayoutManager(this));



        // אתחול סל ריק בתחילה כדי למנוע NullPointer
        cart = new Cart(new ArrayList<Item>());
        cartAdapter = new CartAdapter(this, cart);
        rcCartItems.setAdapter(cartAdapter);


        // טען את הסל מהמסד
        databaseService.getCart(uid, new DatabaseService.DatabaseCallback<Cart>() {
            @Override
            public void onCompleted(Cart resultCart) {
                if (resultCart != null && resultCart.getItemUserlist() != null) {


                    Log.d("cart", resultCart.toString());
                    cart.setItemUserlist(resultCart.getItemUserlist());
                    cartAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailed(Exception e) {
                Log.e("error", e.getMessage());
                Toast.makeText(CartActivity.this, "שגיאה בטעינת הסל", Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public void onClick(View v) {
        Intent go=new Intent(CartActivity.this, AddItem.class);
        startActivity(go);

    }

    public void bthAddItem(View view) {
    }
}