package com.example.iotparkhaus;

import android.os.Bundle;

import com.example.iotparkhaus.customDataStructs.parkingGarageOccupation;
import com.example.iotparkhaus.customDataStructs.parkingStats;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Ack;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.android.material.tabs.TabLayout;

import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.iotparkhaus.ui.main.SectionsPagerAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private Socket mSocket;
    private parkingStats stats;
    private SocketViewModel socketViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        socketViewModel = ViewModelProviders.of(this).get(SocketViewModel.class);
        connectToSocket();
    }

    public void connectToSocket() {
        System.out.println("connect to socket");
        try {
            IO.Options opt = new IO.Options();
            opt.transports = new String[]{
                    "websocket"
            };

            mSocket = IO.socket("https://iot-parkhaus.midb.medien.hs-duesseldorf.de/", opt);

            mSocket.on("parking patched", onParkingPatched);
            mSocket.on("stats patched", onStatsPatched);

            mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    System.out.println("socket connected!");
                }
            });
            mSocket.connect();

            mSocket.emit("find", "parking", new Ack() {
                @Override
                public void call(Object... args) {
                try {
                    JSONArray array = new JSONArray(args[1].toString());

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject row = array.getJSONObject(i);
                        parkingGarageOccupation.getInstance().addSpotOccupation(row.getInt("number"), row.getBoolean("available"));
                    }

                    socketViewModel.setOccupation(parkingGarageOccupation.getInstance());
                } catch (Exception e) {
                    System.out.println("alert!");
                    e.printStackTrace();
                }
                }
            });

            mSocket.emit("find", "stats", new Ack() {
                @Override
                public void call(Object... args) {
                    sendDataToViewModel(args[1].toString(), true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Emitter.Listener onParkingPatched = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONObject newSpotData = (JSONObject) args[0];
                        parkingGarageOccupation.getInstance().addSpotOccupation(newSpotData.getInt("number"), newSpotData.getBoolean("available"));
                        socketViewModel.setOccupation(parkingGarageOccupation.getInstance());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };

    private Emitter.Listener onStatsPatched = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    sendDataToViewModel(args[0].toString(), false);
                }
            });
        }
    };

    private JSONObject sendDataToViewModel(String input, boolean inputIsArray){
        try {
            JSONObject row;
            if (inputIsArray) {
                JSONArray array = new JSONArray(input);
                row = array.getJSONObject(0);
            } else {
                row = new JSONObject(input);
            }

            stats = new parkingStats(row.getInt("freeParking"), row.getInt("freeWomenParking"), row.getInt("freeDisabledParking"));
            socketViewModel.setParkingStats(stats);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onDestroy() {
        System.out.println("socket disconnected");
        mSocket.disconnect();
        super.onDestroy();
    }

}