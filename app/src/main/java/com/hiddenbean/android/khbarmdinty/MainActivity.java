package com.hiddenbean.android.khbarmdinty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.hiddenbean.android.khbarmdinty.models.TextPost;
import com.hiddenbean.android.khbarmdinty.models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager FRAGMENT_MANAGER;
    Toolbar bottomToolbar, topToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.KhbarMdintyTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences globalPreferences = getSharedPreferences("GlobalPrefrences", Context.MODE_PRIVATE);
        String userApiToken = globalPreferences.getString("user_api_token", "N/A");
        int kherejTaro = globalPreferences.getInt("kherj_taro", -1);
        boolean kherjTaroPhone = globalPreferences.getBoolean("kherj_taro_phone", false);
        boolean validPhone = globalPreferences.getBoolean("valid_phone", false);

        if(kherejTaro == -1 || kherjTaroPhone == true && validPhone == false) {
            startActivity(new Intent(this, KherjTaroActivity.class));
        }

        topToolbar = findViewById(R.id.top_toolbar);
        topToolbar.setTitle(R.string.home_title);


        topToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "Back Button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        bottomToolbar = findViewById(R.id.bottom_toolbar);
        setSupportActionBar(bottomToolbar);

        FRAGMENT_MANAGER = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = FRAGMENT_MANAGER.beginTransaction();
        fragmentTransaction
                .add(R.id.fragments_container, new FeedFragment(), "Feed")
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return true;
    }
}
