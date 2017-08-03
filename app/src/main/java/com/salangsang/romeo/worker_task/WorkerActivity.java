package com.salangsang.romeo.worker_task;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

public class WorkerActivity extends AppCompatActivity {

    //Declare all my variables
    private TextView displayDate;
    EditText editWorker, editTask;
    Button backButton, addWorker, showWorker ;

    //define database handler
    MyDBHandler dbHandler;

    //Set Listener toDatepicker dialog
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);

        //Instantiate MyDBHandler constructore
        dbHandler = new MyDBHandler(this);

        displayDate = (EditText) findViewById(R.id.dateTV);
        editWorker = (EditText) findViewById(R.id.editWorker);
        editTask = (EditText) findViewById(R.id.editTask);
        backButton = (Button) findViewById(R.id.backButton);
        addWorker = (Button) findViewById(R.id.addWorker);
        showWorker =(Button) findViewById(R.id.showWorker);

        editWorker.requestFocus();

        displayDate.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog = new DatePickerDialog(
                        WorkerActivity.this,
                        android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }

        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                //since January is valued 0 by default need to add one for month.
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy" + year + "/" + month + "/" + day);

                //Set date on my TextView
                String date = month + "/" + day + "/" + year;
                displayDate.setText(date);
            }

            ;
        };
    }
        //Use Intent in navigating to main activity class with data transfer
    public void backClick(View view) {
        Intent i = new Intent(this, MainActivity.class);
        //get text from the inputs
        final EditText editWorker = (EditText)findViewById(R.id.editWorker);
        final EditText editTask = (EditText)findViewById(R.id.editTask);
        final EditText dateET = (EditText) findViewById(R.id.dateTV);

        //pass the messages and input to the next page
        String userMessage = editWorker.getText().toString();
        String userMessage1 = editTask.getText().toString();
        String userDate = dateET.getText().toString();
        i.putExtra("editWorker", userMessage);
        i.putExtra("editTask", userMessage1);
        i.putExtra("dateTV", userDate);

        startActivity(i);
    }
    //Add task to workers
    public void addClick(View view) {
        Workers workers = new Workers(editWorker.getText().toString(), editTask.getText().toString(),
                displayDate.getText().toString());
        dbHandler.addWorkers(workers);

        //Clear the text field
        editWorker.setText("");
        editTask.setText("");
        displayDate.setText("");

        editWorker.requestFocus();
    }

    //delete a contact
    public void deleteClick(View view) {
        String workerName = editWorker.getText().toString();
        String workerTask = editTask.getText().toString();
        String date = displayDate.getText().toString();
        dbHandler.deleteContact(workerName, workerTask, date);

        //clear the text field
        editWorker.setText("");
        editTask.setText("");
        displayDate.setText("");

        //request focus on the worker name
        editWorker.requestFocus();
    }
    //show all contacts
    public void showAllClick(View view) {
        //open up a new screen
        Intent i = new Intent(this, DisplayListActivity.class);
        startActivity(i);
    }
}
