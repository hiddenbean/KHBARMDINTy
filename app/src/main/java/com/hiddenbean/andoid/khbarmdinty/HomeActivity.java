package com.hiddenbean.andoid.khbarmdinty;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    private LyomFragment lyomFragment;
    private TrendFragment trendFragment;
    private NotificationsFragment notificationsFragment;
    private AccountFragment accountFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);
        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        lyomFragment = new LyomFragment();
        trendFragment = new TrendFragment();
        notificationsFragment = new NotificationsFragment();
        accountFragment = new AccountFragment();

        setFragment(lyomFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_lyom :
                        setFragment(lyomFragment);
                        return true;

                    case R.id.nav_trend :
                        setFragment(trendFragment);
                        return true;

                    case R.id.nav_notifications :
                        setFragment(notificationsFragment);
                        return true;

                    case R.id.nav_account :
                        setFragment(accountFragment);
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    private void setFragment (Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_FADE );
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();

    }
}
