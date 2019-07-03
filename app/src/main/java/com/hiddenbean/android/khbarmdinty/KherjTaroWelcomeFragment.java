package com.hiddenbean.android.khbarmdinty;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class KherjTaroWelcomeFragment extends Fragment {

    Button startButton, laterButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kherj_taro_welcome, container, false);

        startButton = view.findViewById(R.id.start);
        laterButton = view.findViewById(R.id.later);

        laterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KherjTaroActivity.FRAGMENT_MANAGER
                        .beginTransaction()
                        .replace(R.id.fragments_container, new KherjTaroSetupFragment(), "SETUP_KHERJ_TARO")
                        .commit();
            }
        });
        return view;
    }
}
