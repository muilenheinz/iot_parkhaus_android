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

import com.example.iotparkhaus.custom.parkingStats;

public class InfoFragment extends Fragment {

    private InfoViewModel mViewModel;

    public static InfoFragment newInstance() {
        return new InfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        SocketViewModel socketViewModel = ViewModelProviders.of(requireActivity()).get(SocketViewModel.class);
        socketViewModel.getName().observe(requireActivity(), new Observer<parkingStats>() {
            @Override
            public void onChanged(@Nullable parkingStats s) {
                System.out.println("observer listened to change: " + s.getDisabledFreeSpots());
            }
        });


        return inflater.inflate(R.layout.info_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(InfoViewModel.class);
        // TODO: Use the ViewModel
    }

}
