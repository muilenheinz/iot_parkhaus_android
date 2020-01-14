package com.example.iotparkhaus.custom;

public class parkingStats {
    private int totalFreeSpots, womenFreeSpots, disabledFreeSpots;

    public parkingStats(int _totalFreeSpots, int _womenFreeSpots, int _disabledFreeSpots) {
        totalFreeSpots = _totalFreeSpots;
        womenFreeSpots = _womenFreeSpots;
        disabledFreeSpots = _disabledFreeSpots;
    }

    public void setDisabledFreeSpots(int disabledFreeSpots) {
        this.disabledFreeSpots = disabledFreeSpots;
    }

    public void setTotalFreeSpots(int totalFreeSpots) {
        this.totalFreeSpots = totalFreeSpots;
    }

    public void setWomenFreeSpots(int womenFreeSpots) {
        this.womenFreeSpots = womenFreeSpots;
    }

    public int getDisabledFreeSpots() {
        return disabledFreeSpots;
    }

    public int getTotalFreeSpots() {
        return totalFreeSpots;
    }

    public int getWomenFreeSpots() {
        return womenFreeSpots;
    }
}
