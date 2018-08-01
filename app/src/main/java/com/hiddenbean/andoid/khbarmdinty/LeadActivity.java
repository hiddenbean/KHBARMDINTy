package com.hiddenbean.andoid.khbarmdinty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

public class LeadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);
    }

    public void toLogin(View view)
    {
        Intent intent = new Intent(LeadActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void toSignup(View view)
    {
        Intent intent = new Intent(LeadActivity.this, SignupActivity.class);
        startActivity(intent);
    }
}
