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

    public void setParkingStats(parkingStats _stats) {
        //use postValue instead of setValue because the thread is running in the background
        parkingStats.postValue(_stats);
    }

    public LiveData<parkingStats> getParkingStats() {
        return parkingStats;
    }

}
