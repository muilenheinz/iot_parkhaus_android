package com.example.iotparkhaus;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class LegalFragment extends Fragment {

    private LegalViewModel mViewModel;
    private TextView parkingRules, impressumView;


    public static LegalFragment newInstance() {
        return new LegalFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View legalView = inflater.inflate(R.layout.legal_fragment, container, false);

        impressumView = legalView.findViewById(R.id.impressum);
        String impressum = "<a href=\"https://hs-duesseldorf.de/impressum\">Impressum / Datenschutz der HSD</a>";
        impressumView.append(Html.fromHtml(impressum));
        impressumView.setMovementMethod(LinkMovementMethod.getInstance());

        parkingRules = legalView.findViewById(R.id.parkingRules);
        String legal = "<a href=\"https://hs-duesseldorf.de/hochschule/verwaltung/parkraumordnung\">Parkraumordnung</a>";
        parkingRules.append(Html.fromHtml(legal));
        parkingRules.setMovementMethod(LinkMovementMethod.getInstance());

        return legalView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LegalViewModel.class);
        // TODO: Use the ViewModel
    }


}
