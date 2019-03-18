package com.hiddenbean.android.khbarmdinty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    public static FragmentManager FRAGMENT_MANAGER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.KhbarMdintyTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        FRAGMENT_MANAGER = getSupportFragmentManager();

        FRAGMENT_MANAGER
                .beginTransaction()
                .add(R.id.fragments_container, new Introduction1Fragment(), "Introduction 1")
                .commit();

    }
}
