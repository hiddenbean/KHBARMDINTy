package com.hiddenbean.android.khbarmdinty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Introduction1Fragment extends Fragment {

    Button nextButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_introduction_1, container, false);

        nextButton = (Button) view.findViewById(R.id.next);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WelcomeActivity
                        .FRAGMENT_MANAGER
                        .beginTransaction()
                        .replace(R.id.fragments_container, new Introduction2Fragment(), "Introduction2")
                        .commit();
            }
        });
        return view;
    }
}
