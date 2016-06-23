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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.example.capstone.teamchooser.helperClasses.Game;
import com.example.capstone.teamchooser.helperClasses.GamesManager;

import java.util.ArrayList;
import java.util.Objects;

public class CreateOrEditGameActivity extends AppCompatActivity {

    private Toolbar m_toolbar = null;
    //Info being passed from the previous activity to this one
    private boolean m_isNewActivity = true;
    //Save button
    private Button m_saveButton = null;
    //Duplicate button
    private Button m_duplicateButton = null;
    //String containing the game name
    private String m_gameName = null;
    //String containing the name of team a
    private String m_teamAName = null;
    //String containing the name of team b
    private String m_teamBName = null;
    //Boolean containing switch value for balance offence-defence count
    private Boolean m_isBODCount = false;
    //Boolean containing switch value for use super optimizer
    private Boolean m_isUsingSO = false;
    //Integer containing the gameId
    private Integer m_gameId = null;

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
        Bundle extras = getIntent().getExtras();
        m_isNewActivity = extras == null;
        if( m_isNewActivity ) {
            m_duplicateButton.setVisibility(View.GONE);
        } else {
            m_duplicateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editOrCreateGame( true );
                    returnToGameList();
                }
            });
        }

        //Setup based on what the parent activity sent us
        this.updateGameInfoFromExtras(extras);
        this.populateActivityView();
        //Setting logic for SAVE button
        m_saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editOrCreateGame( false );
                returnToGameList();
            }
        });

        //Setting the global values for all the variables in the xml of this activity
        //A.K.A game info
        Switch bodCountSwitch = (Switch) findViewById(R.id.balance_offense_defence_switch);
        if( bodCountSwitch != null ) {
            bodCountSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    m_isBODCount = isChecked;
                }
            });
        }

        Switch superOptimizerSwitch = (Switch) findViewById(R.id.use_super_optimizer_switch);
        if( superOptimizerSwitch != null ) {
            superOptimizerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    m_isUsingSO = isChecked;
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                ArrayList<Game> games = GamesManager.getAllGames();
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
        GamesManager.deleteGameById(m_gameId);
        this.returnToGameList();
    }

    private void editOrCreateGame( Boolean isDuplicate ) {
        //TODO error handling
       this.updateGameInfo();
       if( this.m_isNewActivity || isDuplicate ) {
           GamesManager.addGame(
                new Game(
                    m_gameName,
                    m_teamAName,
                    m_teamBName,
                    m_isUsingSO,
                    m_isBODCount
                )
           );
       } else {
           //We're referencing the game. Custom objects in java are references
           Game game = GamesManager.getGameById( m_gameId );
           game.setAllProperties(
               m_gameName,
               m_teamAName,
               m_teamBName,
               m_isUsingSO,
               m_isBODCount
           );

       }
    }

    private void updateGameInfo() {
        m_gameName = ((EditText) findViewById(R.id.game_name_input)).getText().toString();
        m_teamAName = ((EditText) findViewById(R.id.team_a_name_input)).getText().toString();
        m_teamBName = ((EditText) findViewById(R.id.team_b_name_input)).getText().toString();
    }

    private void updateGameInfoFromExtras(Bundle extras) {
        if(extras == null) {
            return;
        }
        m_gameId = Integer.parseInt(extras.getString("gameId"));
        m_gameName = extras.getString("gameName");
        m_teamAName = extras.getString("teamAName");
        m_teamBName = extras.getString("teamBName");
        m_isUsingSO = Objects.equals(extras.getString("isUsingSO"),"true");
        m_isBODCount = Objects.equals(extras.getString("isBODCount"),"true");
    }

    private void populateActivityView() {
        ((EditText) findViewById(R.id.game_name_input)).setText(m_gameName);
        ((EditText) findViewById(R.id.team_a_name_input)).setText(m_teamAName);
        ((EditText) findViewById(R.id.team_b_name_input)).setText(m_teamBName);
        ((Switch) findViewById(R.id.use_super_optimizer_switch)).setChecked(m_isUsingSO);
        ((Switch) findViewById(R.id.balance_offense_defence_switch)).setChecked(m_isBODCount);
    }

    private void returnToGameList() {
        Intent intent = new Intent(this, GameListActivity.class);
        startActivity(intent);
    }
}
