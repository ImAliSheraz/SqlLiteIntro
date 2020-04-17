package com.imalisheraz.sqlliteintro;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText imageEV;
    private ImageView imageIV;
    private Uri imageFilePath;
    private Bitmap imageToStore;
    private static final int PIC_IMAGE_REQUEST = 10;
    DatabaseHandler objectDatabaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            imageEV = findViewById(R.id.imageEV);
            imageIV = findViewById(R.id.imageIV);
            objectDatabaseHandler = new DatabaseHandler(this);
        }
        catch (Exception e){
            Toast.makeText(this, "onCreate" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void selectImage(View view){

        try {
            Intent objectIntent = new Intent();
            objectIntent.setType("image/*");
            objectIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(objectIntent,PIC_IMAGE_REQUEST);

        }
        catch (Exception e){
            Toast.makeText(this, "selectImage" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode==PIC_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
                imageFilePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(),imageFilePath);
                imageIV.setImageBitmap(imageToStore);
            }
        }
        catch (Exception e){
            Toast.makeText(this, "onActivityResult" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void storeImage(View view){
        try {
            if (!imageEV.getText().toString().isEmpty() && imageIV.getDrawable()!=null && imageToStore != null){
                objectDatabaseHandler.storeImage(new ModelClass(imageEV.getText().toString(),imageToStore));
            }
            else{
                Toast.makeText(this, "Enter Text and Select Image" , Toast.LENGTH_SHORT).show();
            }



        }
        catch (Exception e){
            Toast.makeText(this, "storeImage" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void moveToActivity(View view){
        startActivity(new Intent(this,ShowImagesActivity.class));

    }
}
