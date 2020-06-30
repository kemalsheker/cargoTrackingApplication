package com.example.drn20;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GetData extends AppCompatActivity {
    private String TAG = "KEMAL";

    //////
    String xmlString;
    /// this was where ı was stucked
    //////
    ArrayList<String> myList;
    ArrayList<String> TrackNumberList = new ArrayList<>();
    ArrayList<String> ProcessTimeStamp = new ArrayList<>();
    ArrayList<String> OperationBranchName = new ArrayList<>();
    ArrayList<String> ProcessDescription1 = new ArrayList<>();
    ////
    String IdSession = "";
    int InformationLevel;
    String TrackingNumber;
    //////
    VerifyLogin object = null;
    TrackCargo object2 = null;
    final String CustomerNumber = " E7E097";
    final String UserName = "/me";
    final String Password = "aFkaHQKcBPb7lubl4UL6";
    // mThread mthread = null;
    mThread thread = null;
    mThread2 thread2 = null;
    mThread3 thread3 = null;
    mThread4 thread4 = null;
    mThread5 thread5 = null;
    //////
    //List<TrackCargo> ActionList = new ArrayList<>();
    /////
    TextView getData;
    TextView example;
    TextView OperationName;
    TextView Description;
    TextView TimeStamp;
    TextView OperationName1;
    TextView Description1;
    TextView TimeStamp1;
    TextView OperationName2;
    TextView Description2;
    TextView TimeStamp2;
    TextView OperationName3;
    TextView Description3;
    TextView TimeStamp3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getdata_layout);

        /////
        myList = new ArrayList<>();
        TrackingNumber = getIntent().getStringExtra("TRACKNO");
        //Celcius Edittext Kontrol
        //Fahrenheit Text Kontrol
        getData = (TextView) findViewById(R.id.data1);
        example = findViewById(R.id.example1);
        OperationName = findViewById(R.id.OperationName);
        OperationName1 = findViewById(R.id.OperationName1);
        OperationName2 = findViewById(R.id.OperationName2);
        Description = findViewById(R.id.Description);
        TimeStamp = findViewById(R.id.timeStamp);
        OperationName1 = findViewById(R.id.OperationName1);
        Description1 = findViewById(R.id.Description1);
        TimeStamp1 = findViewById(R.id.timeStamp1);
        OperationName2 = findViewById(R.id.OperationName2);
        Description2 = findViewById(R.id.Description2);
        TimeStamp2 = findViewById(R.id.timeStamp2);
        OperationName3 = findViewById(R.id.OperationName3);
        Description3 = findViewById(R.id.Description3);
        TimeStamp3 = findViewById(R.id.timeStamp3);

        //Buttona basıldığında webservis çağırılıyor.{
        thread = new mThread();
        thread2 = new mThread2();
        thread3 = new mThread3();
        thread4 = new mThread4();
        thread5 = new mThread5();


        try {
            // First thread
            thread.start();
            thread.join();
            //
            // Second thread
            thread2.start();
            thread2.join();
            ////
            thread3.start();
            thread3.join();
            /////
            thread4.start();
            thread4.join();
            /////
            thread5.start();
            thread5.join();
            //////
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ///

        //Eğer textview boşsa uyarı ver.

    }

    private class mThread extends Thread {
        @Override
        public void run() {
            object = LoginValidatorUPS.verifyLogin(CustomerNumber, UserName, Password);
            handler.sendEmptyMessage(0);
        }

        @SuppressLint("HandlerLeak")
        private Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                thread.interrupt();
                getData.setText(object.getSessionID());
            }
        };
    }

    private class mThread2 extends Thread {
        @Override
        public void run() {
            SoapHelper helper = new SoapHelper();
            TrackNumberList = helper.getSoapRequest(object.getSessionID(), 1, TrackingNumber);
            //object2  = TrackingUPS.trackCargo(object.getSessionID() , 1 , "1ZE7E0970426218990" );
            handler2.sendEmptyMessage(0);
        }

        @SuppressLint("HandlerLeak")
        private Handler handler2 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                thread2.interrupt();
                // example.setText(object2.getTrackingNumber());
                // ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.activity_list_item, myList);
                // CustomAdapter customAdapter = new CustomAdapter();
                //  listview.setAdapter(customAdapter);
                int counter = 9;
                int i = 0;
                if(TrackNumberList.size() >0 )
                {
                    while(i< TrackNumberList.size())
                    {
                        Log.i("TrackNumber" , TrackNumberList.get(i));
                        i++;

                    }
                }
                if(TrackNumberList.get(0) != null)
                  example.setText(TrackNumberList.get(0));
            }
        };
    }

    private class mThread3 extends Thread {
        @Override
        public void run() {
            SoapHelper2 helper2 = new SoapHelper2();
            ProcessTimeStamp = helper2.getSoapRequest(object.getSessionID() , 1, TrackingNumber);
            //object2  = TrackingUPS.trackCargo(object.getSessionID() , 1 , "1ZE7E0970426218990" );
            handler3.sendEmptyMessage(0);
        }

        @SuppressLint("HandlerLeak")
        private Handler handler3 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                thread3.interrupt();
                // example.setText(object2.getTrackingNumber());
                // ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.activity_list_item, myList);
                // CustomAdapter customAdapter = new CustomAdapter();
                //  listview.setAdapter(customAdapter);
                int counter = 9;
                int i = 0;
                if(ProcessTimeStamp.size() >0 )
                {
                    while(i< ProcessTimeStamp.size())
                    {
                        Log.i("TimeStamp" , ProcessTimeStamp.get(i));
                        i++;
                    }
                }

                if(ProcessTimeStamp.get(0) != null)
                    TimeStamp.setText(ProcessTimeStamp.get(0));

                if(ProcessTimeStamp.get(1) != null)
                    TimeStamp1.setText(ProcessTimeStamp.get(1));

                if(ProcessTimeStamp.get(2) != null)
                    TimeStamp2.setText(ProcessTimeStamp.get(2));

                if(ProcessTimeStamp.get(3) != null)
                    TimeStamp3.setText(ProcessTimeStamp.get(3));
            }
        };
    }

    private class mThread4 extends Thread {
        @Override
        public void run() {
            SoapHelper3 helper3 = new SoapHelper3();
            OperationBranchName = helper3.getSoapRequest(object.getSessionID() , 1, TrackingNumber);
            //object2  = TrackingUPS.trackCargo(object.getSessionID() , 1 , "1ZE7E0970426218990" );
            handler4.sendEmptyMessage(0);
        }

        @SuppressLint("HandlerLeak")
        private Handler handler4 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                thread4.interrupt();
                // example.setText(object2.getTrackingNumber());
                // ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.activity_list_item, myList);
                // CustomAdapter customAdapter = new CustomAdapter();
                //  listview.setAdapter(customAdapter);
                int counter = 9;
                int i = 0;
                if(OperationBranchName.size() >0 )
                {
                    while(i< OperationBranchName.size())
                    {
                        Log.i("OperationName" , OperationBranchName.get(i));
                        i++;
                    }
                }

                if(OperationBranchName.get(0) != null)
                    OperationName.setText(OperationBranchName.get(0));

                if(OperationBranchName.get(1) != null)
                    OperationName1.setText(OperationBranchName.get(1));

                if(OperationBranchName.get(2) != null)
                    OperationName2.setText(OperationBranchName.get(2));

                if(OperationBranchName.get(3) != null)
                    OperationName3.setText(OperationBranchName.get(3));
            }
        };
    }

    private class mThread5 extends Thread {
        @Override
        public void run() {
            SoapHelper4 helper4 = new SoapHelper4();
            ProcessDescription1 = helper4.getSoapRequest(object.getSessionID(), 1, TrackingNumber);
            //object2  = TrackingUPS.trackCargo(object.getSessionID() , 1 , "1ZE7E0970426218990" );
            handler5.sendEmptyMessage(0);
        }

        @SuppressLint("HandlerLeak")
        private Handler handler5 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                thread5.interrupt();
                // example.setText(object2.getTrackingNumber());
                // ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.activity_list_item, myList);
                // CustomAdapter customAdapter = new CustomAdapter();
                //  listview.setAdapter(customAdapter);
                int counter = 9;
                int i = 0;
                if(ProcessDescription1.size() >0 )
                {
                    while(i< ProcessDescription1.size())
                    {
                        Log.i("Process" , ProcessDescription1.get(i));
                        i++;
                    }
                }

                if(ProcessDescription1.get(0) != null)
                    Description.setText(ProcessDescription1.get(0));

                if(ProcessDescription1.get(1) != null)
                    Description1.setText(ProcessDescription1.get(1));

                if(ProcessDescription1.get(2) != null)
                    Description2.setText(ProcessDescription1.get(2));

                if(ProcessDescription1.get(3) != null)
                    Description3.setText(ProcessDescription1.get(3));
            }
        };
    }

}

