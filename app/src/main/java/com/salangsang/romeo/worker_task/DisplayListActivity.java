package com.salangsang.romeo.worker_task;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DisplayListActivity extends AppCompatActivity {

    //define the widget variable layout
    private TableLayout tableLayout;

    //define SQL controller variable
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);

        //to navigate back to MainActivity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get reference to the layout widget
        tableLayout = (TableLayout) findViewById(R.id.activity_test);

        //instantiate the handler constructor
        dbHandler = new MyDBHandler(this);

        //call the build table method
        BuildTable();
    }

    //responsible for building the table
    private void BuildTable() {
        dbHandler.open();
        Cursor c = dbHandler.readEntry();

        int rows = c.getCount();
        int columns = c.getColumnCount();

        c.moveToFirst();

        //outer for loop
        for(int i = 0; i < rows; i++) {
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            //inner for loop
            for(int j = 0; j< columns; j++) {
                TextView tv = new TextView(this);
                tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv.setGravity(Gravity.CENTER);
                tv.setTextSize(18);
                tv.setPadding(0, 5, 0, 5);
                tv.setText(c.getString(j));
                row.addView(tv);
            }
            c.moveToNext();
            tableLayout.addView(row);
        }
        //close the database
        dbHandler.close();
    }
}
