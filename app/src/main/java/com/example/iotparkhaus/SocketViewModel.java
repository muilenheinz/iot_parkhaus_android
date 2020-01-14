package com.example.iotparkhaus;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.iotparkhaus.custom.parkingStats;

import java.util.HashMap;
import java.util.List;


public class SocketViewModel extends ViewModel {
    private MutableLiveData<parkingStats> parkingStats = new MutableLiveData<>();
    private MutableLiveData<HashMap<String, String>> map = new MutableLiveData<>();

    public void setName(parkingStats _stats) {
        parkingStats.setValue(_stats);
    }

    public LiveData<parkingStats> getName() {
        return parkingStats;
    }

}
