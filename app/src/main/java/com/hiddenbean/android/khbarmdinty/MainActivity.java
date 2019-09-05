package com.hiddenbean.android.khbarmdinty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager FRAGMENT_MANAGER;
    Toolbar topToolbar;
    ExtendedFloatingActionButton newHtaryButton;
    NestedScrollView nestedScrollView;
    BottomNavigationView bottomNavigationView;
    final Fragment feedFragement = new FeedFragment();
    final Fragment notificationFragment = new NotificationFragment();
    final Fragment trendFragment = new TrendFragment();
    final Fragment followFragment = new FollowFragment();
    final Fragment menuFragment = new MenuFragment();
    Fragment active = feedFragement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.KhbarMdintyTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        SharedPreferences globalPreferences = getSharedPreferences("GlobalPrefrences", Context.MODE_PRIVATE);
        String userApiToken = globalPreferences.getString("user_api_token", "N/A");
        int kherejTaro = globalPreferences.getInt("kherj_taro", -1);
        boolean kherjTaroPhone = globalPreferences.getBoolean("kherj_taro_phone", false);
        boolean validPhone = globalPreferences.getBoolean("valid_phone", false);

        if(kherejTaro == -1 || kherjTaroPhone == true && validPhone == false) {
            startActivity(new Intent(this, KherjTaroActivity.class));
        }

        topToolbar = findViewById(R.id.top_toolbar);
        topToolbar.setTitle("");
        setSupportActionBar(topToolbar);

        newHtaryButton = findViewById(R.id.new_htary);
        nestedScrollView = findViewById(R.id.nested_scroll_view);

        nestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == 0) {
                    newHtaryButton.extend();
                }
                else {
                    newHtaryButton.shrink();
                }
            }
        });

        topToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "Back Button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        FRAGMENT_MANAGER = getSupportFragmentManager();

        FRAGMENT_MANAGER.beginTransaction()
                .add(R.id.fragments_container, notificationFragment, "Notifications")
                .hide(notificationFragment)
                .commit();

        FRAGMENT_MANAGER.beginTransaction()
                .add(R.id.fragments_container, feedFragement, "Feed")
                .commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_feed:
                        FRAGMENT_MANAGER.beginTransaction().hide(active).show(feedFragement).commit();
                        active = feedFragement;
                        newHtaryButton.show(true);
                        return true;

                    case R.id.navigation_trand:
                        FRAGMENT_MANAGER.beginTransaction().hide(active).show(trendFragment).commit();
                        active = trendFragment;
                        newHtaryButton.hide(true);
                        return true;

                    case R.id.navigation_follow:
                        FRAGMENT_MANAGER.beginTransaction().hide(active).show(followFragment).commit();
                        active = followFragment;
                        newHtaryButton.hide(true);
                        return true;

                    case R.id.navigation_menu:
                        FRAGMENT_MANAGER.beginTransaction().hide(active).show(menuFragment).commit();
                        active = menuFragment;
                        newHtaryButton.hide(true);
                        return true;

                    case R.id.navigation_notifications:
                        FRAGMENT_MANAGER.beginTransaction().hide(active).show(notificationFragment).commit();
                        active = notificationFragment;
                        newHtaryButton.hide(true);
                        return true;
                }
                return false;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_toolbar_menu, menu);
        return true;
    }

}


