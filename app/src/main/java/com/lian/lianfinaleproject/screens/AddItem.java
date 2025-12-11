package com.lian.lianfinaleproject.screens;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.lian.lianfinaleproject.R;
import com.lian.lianfinaleproject.model.Cart;
import com.lian.lianfinaleproject.model.Item;
import com.lian.lianfinaleproject.model.User;
import com.lian.lianfinaleproject.services.DatabaseService;
import com.lian.lianfinaleproject.utils.ImageUtil;
import com.lian.lianfinaleproject.utils.SharedPreferencesUtil;

import java.util.function.UnaryOperator;

public class AddItem extends AppCompatActivity {

    private EditText etItemName, etItemCompany, etItemAmount;
    private Spinner spType;
    private Button btnGallery, btnTakePic, btnAddItem;
    private ImageView imageView;

    private DatabaseService databaseService;


    /// Activity result launcher for selecting image from gallery
    private ActivityResultLauncher<Intent> selectImageLauncher;
    /// Activity result launcher for capturing image from camera
    private ActivityResultLauncher<Intent> captureImageLauncher;



    // constant to compare
    // the activity result code
    int SELECT_PICTURE = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_item);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        InitViews();

        /// request permission for the camera and storage
        ImageUtil.requestPermission(this);

        /// get the instance of the database service
        databaseService = DatabaseService.getInstance();


        /// register the activity result launcher for capturing image from camera
        /// register the activity result launcher for selecting image from gallery
        selectImageLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri selectedImage = result.getData().getData();
                        imageView.setImageURI(selectedImage);
                        /// set the tag for the image view to null
                        imageView.setTag(null);
                    }
                });

        /// register the activity result launcher for capturing image from camera
        captureImageLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");
                        imageView.setImageBitmap(bitmap);
                        /// set the tag for the image view to null
                        imageView.setTag(null);
                    }
                });


        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImageFromGallery();
            }
        });

        btnTakePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureImageFromCamera();

            }
        });

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = etItemName.getText().toString();
                String itemCompany = etItemCompany.getText().toString();
                String itemAmount = etItemAmount.getText().toString();
                String itemType = spType.getSelectedItem().toString();

                String imageBase64 = ImageUtil.convertTo64Base(imageView);
                int amount = Integer.parseInt(itemAmount);

                if (itemName.isEmpty() || itemCompany.isEmpty() ||
                        itemAmount.isEmpty() || itemType.isEmpty()) {
                    Toast.makeText(AddItem.this, "אנא מלא את כל השדות", Toast.LENGTH_SHORT).show();
                } else {
                    /// generate a new id for the item
                    String id = databaseService.generateItemId();

                    Item newItem = new Item(id, itemName, itemType, imageBase64, itemCompany, amount, false);

                    String currentUserId = SharedPreferencesUtil.getUserId(AddItem.this);

                    assert currentUserId != null;
                    databaseService.updateUser(currentUserId, new UnaryOperator<User>() {
                        @Override
                        public User apply(User currentUser) {
                            if (currentUser == null) {
                                return null;
                            }

                            Cart userCart = currentUser.getCart();
                            if (userCart == null) {
                                userCart = new Cart();
                            }

                            userCart.addItem(newItem);

                            currentUser.setCart(userCart);

                            return currentUser;
                        }
                    }, new DatabaseService.DatabaseCallback<Void>() {
                        @Override
                        public void onCompleted(Void object) {
                            Log.d("TAG", "Item added successfully");
                            Toast.makeText(AddItem.this, "Item added successfully", Toast.LENGTH_SHORT).show();
                            /// clear the input fields after adding the item for the next item
                            Log.d("TAG", "Clearing input fields");

                            Toast.makeText(AddItem.this, "המוצר נוסף בהצלחה!", Toast.LENGTH_SHORT).show();

                            finish();
                        }

                        @Override
                        public void onFailed(Exception e) {
                            Log.e("TAG", "Failed to add item", e);
                            Toast.makeText(AddItem.this, "Failed to add food", Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }
        });
    }

    private void InitViews() {
        etItemName = findViewById(R.id.edItemN);

        etItemAmount = findViewById(R.id.edAmount);
        spType = findViewById(R.id.typespinner);
        etItemCompany = findViewById(R.id.edCompany);
        btnGallery = findViewById(R.id.btnAddFromGallery);
        btnTakePic = findViewById(R.id.btnAddFromCamera);
        btnAddItem = findViewById(R.id.btnAddThisItem);
        imageView = findViewById(R.id.imageView);
    }


    /// select image from gallery
    private void selectImageFromGallery() {

        imageChooser();
    }

    /// capture image from camera
    private void captureImageFromCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        captureImageLauncher.launch(takePictureIntent);
    }


    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    imageView.setImageURI(selectedImageUri);
                }
            }
        }
    }

}
