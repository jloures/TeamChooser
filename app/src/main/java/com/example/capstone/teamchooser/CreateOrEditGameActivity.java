package com.example.capstone.teamchooser;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.capstone.teamchooser.helperClasses.Game;
import com.example.capstone.teamchooser.helperClasses.GamesManager;

import java.util.Objects;

public class CreateOrEditGameActivity extends AppCompatActivity {

    private Toolbar m_toolbar = null;
    //Info being passed from the previous activity to this one
    private boolean m_isNewActivity = true;
    //Save Text that will serve as a Button
    private TextView m_saveText = null;
    //Duplicate Text that will also serve as a button
    private TextView m_duplicateText = null;
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
    //Boolean containing switch value for use o-d ratings
    private Boolean m_isUsingBalanceODRatings = false;
    //Integer containing the gameId
    private long m_gameId;
    //Linear layout with hidden switch
    private LinearLayout m_layoutWithODBalance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Telling android which layout is the one we're supposed to be showing for
        //this activity
        setContentView(R.layout.activity_create_or_edit_game);
        //Finding our toolbar in the xml file
        m_toolbar = (Toolbar) findViewById(R.id.create_game_toolbar_top);
        //Setting it as our default toolbar
        setSupportActionBar(m_toolbar);

        //All we are doing here is to set up the back button
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        //Making the back button custom ( in this case changing the color to white )
        ab.setHomeAsUpIndicator(R.drawable.back_button_white);


        //Setting visibility of switch to gone (should only show up once super
        //optimizer switch is on
        m_layoutWithODBalance = (LinearLayout) findViewById(R.id.balance_o_d_ratings_layout);
        //Now we'll be taking the extras being passed from the game list activity
        m_saveText = (TextView) findViewById(R.id.save_game_text);
        m_duplicateText = (TextView) findViewById(R.id.duplicate_game_text);
        Bundle extras = getIntent().getExtras();
        m_isNewActivity = extras == null;
        if( m_isNewActivity ) {
            m_duplicateText.setVisibility(View.GONE);
        } else {
            m_duplicateText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editOrCreateGame( true );
                }
            });
        }

        //Setup based on what the parent activity sent us
        this.updateGameInfoFromExtras(extras);
        this.populateActivityView();
        //Setting logic for SAVE button
        m_saveText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editOrCreateGame( false );
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
                    //Making the layout containing the other option (balance o-d ratings)
                    //appear or disappear
                    m_layoutWithODBalance.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                }
            });
        }

        Switch balanceODRatingsSwitch = (Switch) findViewById(R.id.balance_o_d_ratings_switch);
        if( balanceODRatingsSwitch != null ) {
            balanceODRatingsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    m_isUsingBalanceODRatings = isChecked;
                }
            });
        }
        //At onCreate, we should already know whether to display isUsingSO or not
        m_layoutWithODBalance.setVisibility(m_isUsingSO ? View.VISIBLE : View.GONE);
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
            inflater.inflate(R.menu.create_or_edit_game_menu_top, menu);
        }
        return true;
    }

    private void deleteGame() {
        GamesManager.deleteGameById( m_gameId );
        this.returnToGameList();
    }

    private void editOrCreateGame( Boolean isDuplicate ) {
        this.updateGameInfo();
        //error handling such as empty strings for Game Names
        if( Objects.equals(m_teamAName,"") || Objects.equals(m_teamBName,"") || Objects.equals(m_gameName,"") ) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Error");
            alertDialogBuilder.setMessage("One or more fields are empty");
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            return;
        }
       if( this.m_isNewActivity || isDuplicate ) {
           GamesManager.addGame(
                new Game(
                    m_gameName,
                    m_teamAName,
                    m_teamBName,
                    m_isUsingSO,
                    m_isBODCount,
                    m_isUsingSO ? m_isUsingBalanceODRatings : false
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
               m_isBODCount,
               m_isUsingBalanceODRatings
           );

       }
       returnToGameList();
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
        m_gameId = Long.parseLong(extras.getString("gameId"));
        m_gameName = extras.getString("gameName");
        m_teamAName = extras.getString("teamAName");
        m_teamBName = extras.getString("teamBName");
        m_isUsingSO = Objects.equals(extras.getString("isUsingSO"),"true");
        m_isBODCount = Objects.equals(extras.getString("isBODCount"),"true");
        m_isUsingBalanceODRatings = Objects.equals(extras.getString("isBalanceODRatings"),"true");
    }

    private void populateActivityView() {
        ((EditText) findViewById(R.id.game_name_input)).setText(m_gameName);
        ((EditText) findViewById(R.id.team_a_name_input)).setText(m_teamAName);
        ((EditText) findViewById(R.id.team_b_name_input)).setText(m_teamBName);
        ((Switch) findViewById(R.id.use_super_optimizer_switch)).setChecked(m_isUsingSO);
        ((Switch) findViewById(R.id.balance_offense_defence_switch)).setChecked(m_isBODCount);
        ((Switch) findViewById(R.id.balance_o_d_ratings_switch)).setChecked(m_isUsingBalanceODRatings);
    }

    private void returnToGameList() {
        Intent intent = new Intent(this, GameListActivity.class);
        startActivity(intent);
    }
}
