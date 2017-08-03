package com.salangsang.romeo.worker_task;



public class Workers {

    //define variables for the columns
    private int _id;
    private String workerName;
    private String workerTask;
    private String date;

    public Workers() {

    }
     //constructor
    public Workers(String workerName, String workerTask, String date) {
            this.workerName = workerName;
            this.workerTask = workerTask;
            this.date = date;

        }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public void setWorkerTask(String workerTask) {
        this.workerTask = workerTask;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int get_id() {
        return _id;
    }

    public String getWorkerName() {
        return workerName;
    }

    public String getWorkerTask() {
        return workerTask;
    }

    public String getDate() {
        return date;
    }
}
