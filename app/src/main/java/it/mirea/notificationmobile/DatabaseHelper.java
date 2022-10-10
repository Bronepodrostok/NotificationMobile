package it.mirea.notificationmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.time.OffsetDateTime;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "Database";

    private static final String TABLE_NAME = "notification_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "Info";
    private static final String COL3 = "Description";
    private static final String COL4 = "Date";
    private static final String COL5 = "Month";


    public DatabaseHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableCommand = "CREATE TABLE " + TABLE_NAME + " ( " +
                COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT, " +
                COL3 + " TEXT, " +
                COL4 + " INT, " +
                COL5 + " INT  )";
        sqLiteDatabase.execSQL(createTableCommand);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean addData(String name, String description, int day, int month ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, name);
        contentValues.put(COL3, description);
        contentValues.put(COL4, day);
        contentValues.put(COL5, month);


        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getName() {
        SQLiteDatabase db = this.getWritableDatabase();
        OffsetDateTime today = OffsetDateTime.now();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE Date=" + today.getDayOfMonth();
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void printColums() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        String[] collumNames = cursor.getColumnNames();
        for (String i : collumNames) {
            Log.d(TAG, i);
        }
    }

    public Integer deleteFromDb(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {String.valueOf(id)});
    }
}
