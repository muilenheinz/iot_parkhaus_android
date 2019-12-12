package com.example.iotparkhaus;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class LegalFragment extends Fragment {

    private LegalViewModel mViewModel;
    private Context context = null;

    public static LegalFragment newInstance() {
        return new LegalFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //load the legal text as html, so it can be styled "inline"
        final View legalView = inflater.inflate(R.layout.legal_fragment, container, false);
        TextView legalTextView = legalView.findViewById(R.id.legalText);
        String legalText = getString(R.string.legalText);

        //first if case is the "current solution", other one is deprecated, use for backward compatibility
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            legalTextView.setText(Html.fromHtml(legalText,Html.FROM_HTML_MODE_LEGACY));
        } else {
            legalTextView.setText(Html.fromHtml(legalText));
        }

        return legalView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LegalViewModel.class);
        // TODO: Use the ViewModel
    }


}
