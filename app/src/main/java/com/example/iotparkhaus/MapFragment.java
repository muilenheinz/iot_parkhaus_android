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


public class MapFragment extends Fragment {

    private MapViewModel mViewModel;
    private View mapView;
    private Socket mSocket;
    private int i = 0;

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
        sendMsg = mapView.findViewById(R.id.sendMsg);

        final TextView connStateText = mapView.findViewById(R.id.connState);

        try {
            IO.Options opts = new IO.Options();
//            opts.forceNew = true;
//            opts.reconnection = true;
//            opts.upgrade = false;

                mSocket = IO.socket("https://socketio-chat.now.sh/", opts);
                mSocket.connect();
                mSocket.open();


            mSocket.io().on(Manager.EVENT_TRANSPORT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Transport transport = (Transport) args[0];
                    transport.on(Transport.EVENT_ERROR, new Emitter.Listener() {
                        @Override
                        public void call(Object... args) {
                            Exception e = (Exception) args[0];
//                            Log.e(TAG, "Transport error " + e);
//                            e.printStackTrace();
//                            e.getCause().printStackTrace();
                              connStateText.setText(e.getMessage());
//                            Writer writer = new StringWriter();
//                            e.printStackTrace(new PrintWriter(writer));
//                            String s = writer.toString();
//                            connStateText.setText(s);
                        }
                    });
                }
            });
//
//
////            mSocket.open();
//
            if (mSocket.connected()) {
                connStateText.setText("connected");
            } else {
                connStateText.setText("not connected");
            }
//
//            mSocket.emit("add user", "test123");
//            mSocket.on("new message", handleIncomingMessages);
//
//
//            sendMsg.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View v) {
//                    //sendMsg.setText("Tried sending to socket.io");
//                    mSocket.emit("message", "this is a message to toju");
//                }
//            });
        } catch (Exception e) {
            connStateText.setText(e.getMessage());
        }

        return mapView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MapViewModel.class);
        // TODO: Use the ViewModel
    }



    private Emitter.Listener handleIncomingMessages = new Emitter.Listener(){
        @Override
        public void call(final Object... args){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String message;
                    String imageText;
                    try {
                        message = data.getString("text").toString();
                        sendMsg.setText(message);
                    } catch (JSONException e) {
                        // return;
                    }
                    try {
                        imageText = data.getString("image");
                        //addImage(decodeImage(imageText));
                    } catch (JSONException e) {
                        //retur
                    }

                }
            });
        }
    };



    @Override
    public void onDestroy() {
        super.onDestroy();
        mSocket.disconnect();
    }


}
