package com.hiddenbean.android.khbarmdinty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class KherjTaroActivity extends AppCompatActivity {

    public static FragmentManager FRAGMENT_MANAGER;
    public static SharedPreferences GLOBAL_PREFERENCES;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.KhbarMdintyTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kherj_taro);

        GLOBAL_PREFERENCES = getSharedPreferences("GlobalPrefrences", Context.MODE_PRIVATE);


        boolean kherjTaroPhone = GLOBAL_PREFERENCES.getBoolean("kherj_taro_phone", false);
        boolean validPhone = GLOBAL_PREFERENCES.getBoolean("valid_phone", false);

        FRAGMENT_MANAGER = getSupportFragmentManager();

        if(kherjTaroPhone == true && validPhone == false) {
            FRAGMENT_MANAGER
                    .beginTransaction()
                    .add(R.id.fragments_container, new KherjTaroSetupDistancePhoneFragment(), "SETUP_DISTANCE_PHONE_KHERJ_TARO")
                    .commit();
        }
        else {
            FRAGMENT_MANAGER
                    .beginTransaction()
                    .add(R.id.fragments_container, new KherjTaroWelcomeFragment(), "WELCOME_KHERJ_TARO")
                    .commit();
        }



    }
}
