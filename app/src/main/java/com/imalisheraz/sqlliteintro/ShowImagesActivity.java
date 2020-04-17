package com.imalisheraz.sqlliteintro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowImagesActivity extends AppCompatActivity {
    private DatabaseHandler objectDatabaseHandler;
    private RecyclerView objectRecyclerView;
    private RCVAdapter objectRCVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_images);
        try {
            objectRecyclerView = findViewById(R.id.recylerviewRV);
            objectDatabaseHandler = new DatabaseHandler(this);
        }
        catch (Exception e){
            Toast.makeText(this, "onCreate" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void getData(View view){
        try {
            objectRCVAdapter = new RCVAdapter(objectDatabaseHandler.getAllImageData());
            objectRecyclerView.setHasFixedSize(true);
            objectRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            objectRecyclerView.setAdapter(objectRCVAdapter);


        }
        catch (Exception e){
            Toast.makeText(this, "getData" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
}
