package com.hiddenbean.android.khbarmdinty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SingupFragment extends Fragment {

    Button singupButton, facebookButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_singup, container, false);

        ((FrontActivity)getActivity()).setTitle(R.string.singup_title);
        ((FrontActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        singupButton = view.findViewById(R.id.singup);
        facebookButton = view.findViewById(R.id.facebook_button);

        singupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "veuillez réessayer ultérieurement", Toast.LENGTH_SHORT).show();
            }
        });

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "veuillez réessayer ultérieurement", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
