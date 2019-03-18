package com.hiddenbean.android.khbarmdinty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class GateFragment extends Fragment {

    Button loginButton,singupButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gate, container, false);


        ((FrontActivity)getActivity()).setTitle(null);
        ((FrontActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        loginButton = (Button) view.findViewById(R.id.singin);
        singupButton = (Button) view.findViewById(R.id.singup);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FrontActivity
                        .fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragments_container, new LoginFragment(), "Login")
                        .addToBackStack("BACK_TO_ROOT_FROM_LOGIN")
                        .commit();
            }
        });

        singupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FrontActivity
                        .fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragments_container, new SingupFragment(), "Singup")
                        .addToBackStack("BACK_TO_ROOT_FROM_SINGUP")
                        .commit();
            }
        });

        return view;
    }

}
