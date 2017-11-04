package com.hogo.rahul.databaselr.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.hogo.rahul.databaselr.Model.DatabaseModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by rohit on 8/16/16.
 */
public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "addToCart.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "AddToCart";
    public static final String KEY_ID = "keyId";
    //    public static final String ITEM_ID = "itemId";
//    public static final String SUB_ITEM_ID = "subitemId";
    public static final String ITEM_NAME = "FullName";
    public static final String ITEM_Email = "Email";
    public static final String ITEM_Password = "Password";

    public static final String CREATE_TABLE_ADDTOCART = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + " VARCHAR, " + ITEM_NAME + " VARCHAR, " + ITEM_Email + " VARCHAR, " + ITEM_Password + " VARCHAR);";
    SQLiteDatabase sdb;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

            db.execSQL(CREATE_TABLE_ADDTOCART);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertToCartTable(String Name, String Email, String Password) {

        Cursor cursor = null;
        sdb = this.getReadableDatabase();

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ITEM_NAME + "='" + Name + "'";
        cursor = sdb.rawQuery(sql, null);

        if (cursor.getCount() == 0) {

            sdb = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(ITEM_Email, "" + Email);
            values.put(ITEM_Password, "" + Password);
            values.put(ITEM_NAME, Name);
            this.sdb.insert(TABLE_NAME, null, values);

            return true;
        }

        sdb.close();
        return false;
    }


    public void updateItemToCartTable(String itemId, String subitemid, String cost, int quantity) {

        sdb = this.getWritableDatabase();

        ContentValues values = new ContentValues();

//        values.put(ITEM_COST, cost);
//        values.put(ITEM_QUANTITY, quantity);

        Log.d("DB", "Count : " + quantity);

//        this.sdb.update(TABLE_NAME, values, ITEM_ID + " ='" + itemId + "' AND " + SUB_ITEM_ID + " ='" + subitemid + "'", null);
        sdb.close();
    }

    public void deleteAllItems() {

        sdb = this.getWritableDatabase();
        sdb.delete(TABLE_NAME, null, null);
        sdb.close();

    }

    public void deleteItemFromCartTable(String itemId, String subitemid) {

        sdb = this.getWritableDatabase();
//        sdb.delete(TABLE_NAME, ITEM_ID + "='" + itemId + "' AND " + SUB_ITEM_ID + "='" + subitemid + "'", null);

        sdb.close();
    }

//    public boolean isExit(String id, String subId) {
//
//
//        String[] columns = {ITEM_ID, SUB_ITEM_ID};
//        String selection = ITEM_ID + " =? And " + SUB_ITEM_ID + " =?";
//        String[] selectionArgs = {id, subId};
//        String limit = "1";
//        sdb = this.getReadableDatabase();
//        Cursor cursor = sdb.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null, limit);
//        if (cursor != null) {
//            return true;
//        } else {
//            return false;
//        }
//
//    }


    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public List<DatabaseModel> displayAllItems() {

        sdb = this.getWritableDatabase();

        Cursor cursor = sdb.query(TABLE_NAME, null, null, null, null, null, null);
        List<DatabaseModel> cartView = new ArrayList<>();
        DatabaseModel details;

        if (cursor.getCount() > 0) {

            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                details = new DatabaseModel();
                details.setFullName(cursor.getString(1));
                details.setEmail(cursor.getString(2));
                details.setPassword(cursor.getString(3));

                cartView.add(details);
            }
        }
        cursor.close();
        sdb.close();
        return cartView;
    }
}
