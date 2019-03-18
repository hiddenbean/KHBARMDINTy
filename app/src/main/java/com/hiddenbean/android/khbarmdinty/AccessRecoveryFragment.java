package com.hiddenbean.android.khbarmdinty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AccessRecoveryFragment extends Fragment {

    Button recoveryButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_access_recovery, container, false);

        ((FrontActivity)getActivity()).setTitle(R.string.password_recovery_title);
        ((FrontActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recoveryButton = (Button) view.findViewById(R.id.recovery_button);

        recoveryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "veuillez réessayer ultérieurement", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
