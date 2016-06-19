package com.example.capstone.teamchooser;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class AppInfo extends AppCompatActivity {

    private Toolbar m_toolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);
        m_toolbar = (Toolbar) findViewById(R.id.app_info_toolbar);
        setSupportActionBar(m_toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeAsUpIndicator(R.drawable.back_button_white);
    }
}
