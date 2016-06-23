package com.example.capstone.teamchooser;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class CreateOrEditGameActivity extends AppCompatActivity {

    private Toolbar m_toolbar = null;
    //Info being passed from the previous activity to this one
    private boolean m_isNewActivity = true;
    //Save button
    private Button m_saveButton = null;
    //Duplicate button
    private Button m_duplicateButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Telling android which layout is the one we're supposed to be showing for
        //this activity
        setContentView(R.layout.activity_create_or_edit_game);
        //Finding our toolbar in the xml file
        m_toolbar = (Toolbar) findViewById(R.id.create_game_toolbar);
        //Setting it as our default toolbar
        setSupportActionBar(m_toolbar);

        //All we are doing here is to set up the back button
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        //Making the back button custom ( in this case changing the color to white )
        ab.setHomeAsUpIndicator(R.drawable.back_button_white);

        //Now we'll be taking the extras being passed from the game list activity
        m_saveButton = (Button) findViewById(R.id.save_game_button);
        m_duplicateButton = (Button) findViewById(R.id.duplicate_game_button);
        m_isNewActivity = getIntent().getExtras() == null;
        if( m_isNewActivity ) {
            m_duplicateButton.setVisibility(View.GONE);
        } else {
            m_duplicateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO duplicate game by making a copy and adding it to gameList
                    //return to GameListActivity afterwards
                    returnToGameList();
                }
            });
        }

        //Setting logic for SAVE button
        m_saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO add new game to gameList and return to GameListActivity
                returnToGameList();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent = new Intent(this, GameListActivity.class);
                startActivity(intent);
                break;
            case R.id.delete_game:
                this.deleteGame();
                break;
        }
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if( !m_isNewActivity ) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.create_or_edit_game_menu, menu);
        }
        return true;
    }

    private void deleteGame() {
        //TODO delete the game and return to GameListActivity;
        this.returnToGameList();
    }

    private void returnToGameList() {
        Intent intent = new Intent(this, GameListActivity.class);
        startActivity(intent);
    }
}
