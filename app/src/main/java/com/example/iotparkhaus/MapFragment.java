package com.example.iotparkhaus;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.github.nkzawa.socketio.client.Ack;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.engineio.client.Transport;
import com.github.nkzawa.socketio.client.Manager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;


public class MapFragment extends Fragment {

    private MapViewModel mViewModel;
    private View mapView;
    private Socket mSocket;
    private int i = 0;
    private TextView connStateText;

    public static MapFragment newInstance() {
        return new MapFragment();
    }
    private Button sendMsg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {



        mapView = inflater.inflate(R.layout.map_fragment, container, false);
//        sendMsg = mapView.findViewById(R.id.sendMsg);

        connStateText = mapView.findViewById(R.id.connState);

        try {
            IO.Options opt = new IO.Options();
            opt.transports = new String[]{
                "websocket"
            };

            mSocket = IO.socket("https://iot-parkhaus.midb.medien.hs-duesseldorf.de/", opt);

//            mSocket.on("null", onParkingPatched);
//            mSocket.on("parking patched", onParkingPatched);
            mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    System.out.println("socket connected: " + String.valueOf(mSocket.connected()));
                }
            });
            mSocket.connect();

            mSocket.emit("find", "parking", new Ack() {
                @Override
                public void call(Object... args) {
                    System.out.println("heureka");
                    System.out.println(args[1]);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MapViewModel.class);
        // TODO: Use the ViewModel
    }




//    private Emitter.Listener onParkingPatched = new Emitter.Listener(){
//        @Override
//        public void call(final Object... args){
//            getActivity().runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                JSONObject data = (JSONObject) args[0];
//                String message;
//                connStateText.setText("Test");
////                connStateText.setText("got message");
////                try {
////                    message = data.getString("message");
////                    connStateText.setText(message);
////                } catch (JSONException e) {
////                    connStateText.setText(e.getMessage());
////                }
//                }
//            });
//        }
//    };



    @Override
    public void onDestroy() {
        super.onDestroy();
        mSocket.disconnect();
    }


}
