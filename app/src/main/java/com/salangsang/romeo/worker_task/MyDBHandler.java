package com.salangsang.romeo.worker_task;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "workers.db";
    public static final String TABLE_NAME = "workers";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_WORK_NAME = "workerName";
    public static final String COLUMN_WORK_TASK = "workerTask";
    public static final String COLUMN_DATE = "date";

    //define SQLite DB variable
    private SQLiteDatabase database;

    //constructor
    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //responsible for creating a table for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+TABLE_NAME + "("+
                COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_WORK_NAME + " TEXT NOT NULL, "+
                COLUMN_WORK_TASK+" TEXT NOT NULL " +
                COLUMN_DATE + " TEXT NOT NULL);";
        db.execSQL(query);
    }

    //responsible for making updates on an existing table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //delete entire table if it exists
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        //recreate the table with the new properties
        onCreate(db);
    }

    public MyDBHandler open() throws SQLException {
        database = getWritableDatabase(); //get reference to the database
        return this;
    }

    //add new row to the database
    public void addWorkers(Workers worker) {
        //content values is built into android that allows you to add several values in one statement
        ContentValues values = new ContentValues();
        values.put(COLUMN_WORK_NAME, worker.getWorkerName());
        values.put(COLUMN_WORK_TASK, worker.getWorkerTask());
        values.put(COLUMN_DATE, worker.getDate());

        //call the open method to get reference to the database
        open();
        database.insert(TABLE_NAME, null, values);

        //once you are done with the database, close it out to give memory back
        close();
    }

    //delete a contact from the database
    public void deleteContact(String workerName, String workerTask, String date) {
        //call the open method to get reference to the database
        open();

        //delete the row with matching firstname and lastname
        database.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_WORK_NAME + "=\"" +
                workerName + "\"" + " AND " +
                COLUMN_WORK_TASK + "=\"" + workerTask + "\" " +
                COLUMN_DATE + "" + date + "\";");

    }
    //Delete all workers from the database
    public void deleteAllContacts() {
        open();
        //delete all records in the database
        database.execSQL("DELETE FROM "+TABLE_NAME+";");
    }

    public Cursor readEntry() {
        String[] allColumns = new String[] {
                COLUMN_ID, COLUMN_WORK_NAME, COLUMN_WORK_TASK, COLUMN_DATE
        };
        Cursor c = database.query(TABLE_NAME, allColumns, null, null, null, null, null);
        if(c!=null) {
            c.moveToFirst();
        }
        return c;
    }
}


