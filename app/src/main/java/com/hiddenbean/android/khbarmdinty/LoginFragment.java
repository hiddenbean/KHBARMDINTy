package com.hiddenbean.android.khbarmdinty;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LoginFragment extends Fragment {

    Button accessRecoveryButton, singinButton, facebookButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        ((FrontActivity)getActivity()).setTitle(R.string.login_title);
        ((FrontActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        accessRecoveryButton = view.findViewById(R.id.access_recovery);
        singinButton = (Button) view.findViewById(R.id.singin);
        facebookButton = (Button) view.findViewById(R.id.facebook_button);

        accessRecoveryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FrontActivity
                        .fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragments_container, new AccessRecoveryFragment(), "Access recovery")
                        .addToBackStack("BACK_TO_LOGIN_FROM_ACCESS_RECOVERY")
                        .commit();
            }
        });

        singinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
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
