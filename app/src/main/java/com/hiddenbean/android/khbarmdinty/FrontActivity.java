package com.hiddenbean.android.khbarmdinty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hiddenbean.android.khbarmdinty.interfaces.Auth.LoginUserInterface;
import com.hiddenbean.android.khbarmdinty.interfaces.Auth.UserInformationInterface;
import com.hiddenbean.android.khbarmdinty.models.User;
import com.hiddenbean.android.khbarmdinty.providers.RetrofitServiceProvider;
import com.hiddenbean.android.khbarmdinty.resources.UserResource;

public class FrontActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences sharedPref = getSharedPreferences("GlobalPrefrences", Context.MODE_PRIVATE);
        final Boolean tutorielCompleted = sharedPref.getBoolean("tutoriel_completed", false);
        String userApiToken = sharedPref.getString("user_api_token", "");
        long longitude = sharedPref.getLong("longitude", 0);
        long latitude = sharedPref.getLong("latitude", 0);
        boolean autoLocalization = sharedPref.getBoolean("auto_localization", false);

        if(!userApiToken.equals("")) {
            if(longitude != 0 && latitude != 0 || autoLocalization) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
            else {
                startActivity(new Intent(this, SetupActivity.class));
                finish();
            }
        }

        if(!tutorielCompleted) {
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
        }

        setTheme(R.style.KhbarMdintyTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction
                .add(R.id.fragments_container, new GateFragment(), "Gate")
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                fragmentManager.popBackStack(Integer.valueOf(fragmentManager.getBackStackEntryCount() - 1), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
