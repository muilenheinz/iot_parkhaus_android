package com.example.iotparkhaus;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iotparkhaus.custom.parkingStats;

public class InfoFragment extends Fragment {

    private InfoViewModel mViewModel;
    private TextView totalSpotsNumber, womenSpotsNumber, disabledSpotsNumber;
    private View infoView;
    public static InfoFragment newInstance() {
        return new InfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        infoView = inflater.inflate(R.layout.info_fragment, container, false);

        totalSpotsNumber = infoView.findViewById(R.id.totalNumber);
        womenSpotsNumber = infoView.findViewById(R.id.womenNumber);
        disabledSpotsNumber = infoView.findViewById(R.id.disabledNumber);

        SocketViewModel socketViewModel = ViewModelProviders.of(requireActivity()).get(SocketViewModel.class);
        socketViewModel.getParkingStats().observe(requireActivity(), new Observer<parkingStats>() {
            @Override
            public void onChanged(@Nullable parkingStats s) {
                writeStats(s);
            }
        });

        return infoView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(InfoViewModel.class);
        // TODO: Use the ViewModel
    }

    private void writeStats(parkingStats _parkingStats) {
        totalSpotsNumber.setText(String.valueOf(_parkingStats.getTotalFreeSpots()));
        womenSpotsNumber.setText(String.valueOf(_parkingStats.getWomenFreeSpots()));
        disabledSpotsNumber.setText(String.valueOf(_parkingStats.getDisabledFreeSpots()));
    }

}
