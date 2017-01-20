package com.example.shaikmoulali.bluscan;

import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import android.widget.Toast;
import java.util.ArrayList;
import java.util.Set;

import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;




public class MainActivity extends Activity {
    Button b1, b2, b3, b4;
    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;
    ListView lv;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerReceiver(receiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));

        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        b4 = (Button) findViewById(R.id.button4);


        BA = BluetoothAdapter.getDefaultAdapter();
        lv = (ListView) findViewById(R.id.listView);
        b3 = (Button) findViewById(R.id.button3);
        b3.setOnClickListener(new OnClickListener() {
                                  public void onClick(View v) {
                                      BA.startDiscovery();
                                  }
                              }
        );
    }

        public void on(View v){
            if (!BA.isEnabled()) {
                Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(turnOn, 0);
                Toast.makeText(getApplicationContext(), "Turned on", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Already on", Toast.LENGTH_LONG).show();
            }
        }

    public void off(View v) {
        BA.disable();
        Toast.makeText(getApplicationContext(), "Turned off", Toast.LENGTH_LONG).show();
    }


    public void visible(View v) {
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);
    }


    /* public void list(View v) {
       pairedDevices = BA.getBondedDevices();

        ArrayList list = new ArrayList();

        for(BluetoothDevice bt : pairedDevices) list.add(bt.getName());
        Toast.makeText(getApplicationContext(), "Showing Paired Devices",Toast.LENGTH_SHORT).show();

        final ArrayAdapter adapter = new  ArrayAdapter(this,android.R.layout.simple_list_item_1, list);

        lv.setAdapter(adapter);*/


    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int dist=0;
            String action = intent.getAction();
            //if conditions for printing the distance.
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                int rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, Short.MIN_VALUE);
                if(rssi>-45) {
                    dist=0;
                }
                else
                if(-45>rssi && rssi>=-48)
                    dist=1;
                else
                if(-48>rssi && rssi>=-51)
                    dist=2;
                else
                if(-51>rssi && rssi>=-54)
                    dist=3;
                else
                if(-54>rssi && rssi>=-58)
                    dist=4;
                else
                if(-58>rssi && rssi>=-61)
                    dist=5;
                else
                if(-61>rssi && rssi>=-68)
                    dist=6;
                else
                if(-68>rssi && rssi>=-71)
                    dist=7;
                else
                if(-71>rssi && rssi>=-76)
                    dist=8;
                else
                if(-76>rssi && rssi>=-80)
                    dist=9;
                else
                if(-80>rssi)
                    dist=10;



                String name = intent.getStringExtra(BluetoothDevice.EXTRA_NAME);
                TextView rssi_msg = (TextView) findViewById(R.id.textView3);
                rssi_msg.setText(rssi_msg.getText() + name + " => " + rssi + "dBm\nDistance =" + dist +" metres\n");  //to print the rssi value.






            }
        }
    };


}

