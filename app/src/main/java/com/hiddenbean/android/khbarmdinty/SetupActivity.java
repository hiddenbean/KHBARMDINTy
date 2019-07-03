package com.hiddenbean.android.khbarmdinty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class SetupActivity extends AppCompatActivity {

    public static FragmentManager FRAGMENT_MANAGER;
    public static SharedPreferences GLOBAL_PREFERENCES;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.KhbarMdintyTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        GLOBAL_PREFERENCES = getSharedPreferences("GlobalPrefrences", Context.MODE_PRIVATE);

        FRAGMENT_MANAGER = getSupportFragmentManager();

        FRAGMENT_MANAGER
                .beginTransaction()
                .add(R.id.fragments_container, new SetupLocationFragment(), "SETUP_LOCATION")
                .commit();

    }
}
