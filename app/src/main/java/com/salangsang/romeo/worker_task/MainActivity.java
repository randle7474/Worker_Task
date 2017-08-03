package com.salangsang.romeo.worker_task;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle workerData = getIntent().getExtras();
        if (workerData == null) {
            return;
        }
        //Write input assignment from workerActivity
        String userMessage = workerData.getString("userMessage");
        final TextView assignmentTV = (TextView)findViewById(R.id.assignmentTV);
        assignmentTV.setText(userMessage);

        //Write input name from workerActivity
        String userMessage1 = workerData.getString("userMessage1");
        final TextView nameTV = (TextView)findViewById(R.id.nameTV);
        nameTV.setText(userMessage1);

        //Write input date from worker activity
        String userDate = workerData.getString("userDate");
        final TextView dateTV = (TextView)findViewById(R.id.dateTV);
        dateTV.setText(userDate);


        // Make a String array of workers
        String[] workers = {"Gary", "Randy", "Elie", "Sal", "Rashid"};

        // List items strings needs adapter
        ListAdapter workerAdapter = new CustomAdapter(this, workers);
        ListView workerListView = (ListView) findViewById(R.id.workerLV);
        workerListView.setAdapter(workerAdapter);

        //Make workers clickable
        workerListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    //The Listener
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // set the value of position and convert it to string
                        String worker = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(MainActivity.this, worker, Toast.LENGTH_LONG).show();

                    }


                }
        );
    }
    //Use intent to navigate to second page using onclick
    public void showClick(View view) {
        Intent i = new Intent(this, WorkerActivity.class);
        startActivity(i);
    }

}