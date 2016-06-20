package com.example.capstone.teamchooser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.capstone.teamchooser.helperClasses.TeamChooserActivity;

public class MainActivity extends TeamChooserActivity {

    private Toolbar m_toolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_toolbar = (Toolbar) findViewById(R.id.create_game_toolbar);
        setSupportActionBar(m_toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.add_game:
                //We should go to another activity to add the game
                break;

            case R.id.get_info:
                this.callAppInfoActivity();
                break;

            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.add_game:
                //We should go to another activity to add the game
                break;

            case R.id.get_info:
                this.callAppInfoActivity();
                break;

            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    private void callAppInfoActivity() {
        Intent intent = new Intent(this, AppInfoListActivity.class);
        startActivity(intent);
    }
}
