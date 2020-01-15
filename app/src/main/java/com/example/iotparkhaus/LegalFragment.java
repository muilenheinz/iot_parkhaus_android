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
import com.example.iotparkhaus.customDataStructs.constants;

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

        //this does not work when the complete tag is loaded from strings.xml,
        //so for syntactical reasons get the URLs from the constants class and the text from strings.xml
        impressumView = legalView.findViewById(R.id.impressum);
        String impressum = "<a href=\""+constants.getURL_impressum()+"\">" + getResources().getString(R.string.impressum) + "</a>";
        impressumView.append(Html.fromHtml(impressum));
        impressumView.setMovementMethod(LinkMovementMethod.getInstance());

        parkingRules = legalView.findViewById(R.id.parkingRules);
        String legal = "<a href=\"" + constants.getURL_parkingRules() + "\">" + getResources().getString(R.string.garageRules) +"</a>";
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
