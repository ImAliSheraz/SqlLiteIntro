package com.imalisheraz.sqlliteintro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.PublicKey;
import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    Context context;
    private static String  DB_NAME = "mydb.db";
    private static int  DB_VERSION = 1;
    private static String  DB_QUERY = "create table image_info(image_name varchar(255), image BLOB)";
    private ByteArrayOutputStream objectByteArrayOutputStream;
    private byte[] imageInByte;


    public DatabaseHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(DB_QUERY);
            Toast.makeText(context, "Table Created", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(context, "onCreate" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void storeImage(ModelClass objectModelClass){
        try{
            SQLiteDatabase objectSQLiteDatabase = this.getReadableDatabase();
            Bitmap imageToStoreBitmap = objectModelClass.getImage();

            objectByteArrayOutputStream = new ByteArrayOutputStream();
            imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG, 100,objectByteArrayOutputStream);

            imageInByte=objectByteArrayOutputStream.toByteArray();
            ContentValues objectContentValues = new ContentValues();

            objectContentValues.put("image_name", objectModelClass.getImageName());
            objectContentValues.put("image", imageInByte);

            long checkObjectRuns = objectSQLiteDatabase.insert("image_info",null,objectContentValues);
            if (checkObjectRuns!=-1){
                Toast.makeText(context, "Data Inserted in Database", Toast.LENGTH_SHORT).show();
                objectSQLiteDatabase.close();
            }
            else{
                Toast.makeText(context, "Failed to Insert in Database", Toast.LENGTH_SHORT).show();
            }


        }
        catch (Exception e){
            Toast.makeText(context, "storeImage" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<ModelClass> getAllImageData(){

        try{
            SQLiteDatabase objectSQLiteDatabase = this.getReadableDatabase();
            ArrayList<ModelClass> objectModelClassList = new ArrayList<>();
            Cursor objectCursor = objectSQLiteDatabase.rawQuery("select * from image_info",null);
            if (objectCursor.getCount()!=0){
                while(objectCursor.moveToNext()){
                    String nameofImage = objectCursor.getString(0);
                    byte [] imageBytes = objectCursor.getBlob(1);
                    Bitmap objectBitMap = BitmapFactory.decodeByteArray(imageBytes,0, imageBytes.length);
                    objectModelClassList.add(new ModelClass(nameofImage,objectBitMap));
                }
                return objectModelClassList;
            }
            else{
                Toast.makeText(context, "No Value Exit in Database", Toast.LENGTH_SHORT).show();
                return null;
            }
        }
        catch (Exception e){
            Toast.makeText(context, "getAllImageData" + e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }

    }
}
