package com.salangsang.romeo.worker_task;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

class CustomAdapter extends ArrayAdapter<String>{

    //String array for my Custom adapter from layout custom_row
    public CustomAdapter(Context context, String[] workers) {
        super(context,R.layout.custom_row , workers);
    }
    //prepare in rendering using inflater
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       LayoutInflater workerInflater = LayoutInflater.from(getContext());
        View customView = workerInflater.inflate(R.layout.custom_row, parent, false);


        //Get references to everything I have in custom row
        String singleWorker = getItem(position);
        TextView workerTV = (TextView)customView.findViewById(R.id.workerTV);
        TextView taskET = (TextView) customView.findViewById(R.id.taskET);
        TextView dateET = (TextView) customView.findViewById(R.id.dateET);
        CheckBox checkBox = (CheckBox)customView.findViewById(R.id.checkBox);
        ImageView workerPic = (ImageView)customView.findViewById(R.id.workerPic);

        workerPic.setImageResource(R.mipmap.pic2);
        workerTV.setText(singleWorker);
        taskET.setText("");
        dateET.setText("");
        checkBox.setChecked(Boolean.parseBoolean(""));
        return customView;

    }


}
