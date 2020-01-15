package com.example.iotparkhaus;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.iotparkhaus.customDataStructs.parkingGarageOccupation;
import com.example.iotparkhaus.customDataStructs.parkingStats;


public class SocketViewModel extends ViewModel {
    private MutableLiveData<parkingStats> parkingStats = new MutableLiveData<>();
    private MutableLiveData<parkingGarageOccupation> occupation = new MutableLiveData<>();

    public void setParkingStats(parkingStats _stats) {
        //use postValue instead of setValue because the thread is running in the background
        parkingStats.postValue(_stats);
    }

    public LiveData<parkingStats> getParkingStats() {
        return parkingStats;
    }

    public void setOccupation(parkingGarageOccupation _occupation) {
        //use postValue instead of setValue because the thread is running in the background
        occupation.postValue(_occupation);
    }

    public LiveData<parkingGarageOccupation> getOccupation() {
        return occupation;
    }

}
