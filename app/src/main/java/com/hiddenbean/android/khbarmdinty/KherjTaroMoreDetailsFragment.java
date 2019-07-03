package com.hiddenbean.android.khbarmdinty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class KherjTaroMoreDetailsFragment extends Fragment {

    Toolbar toolbar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kherj_taro_more_details, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        ((KherjTaroActivity)getActivity()).setSupportActionBar(toolbar);
        ((KherjTaroActivity)getActivity()).getSupportActionBar().setTitle("Savoir plus");
        ((KherjTaroActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return view;
    }
}
