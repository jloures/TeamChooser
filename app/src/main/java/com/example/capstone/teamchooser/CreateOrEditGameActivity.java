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

import com.example.capstone.teamchooser.helper_classes.Game;
import com.example.capstone.teamchooser.helper_classes.GameListManager;

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

        m_saveText = (TextView) findViewById(R.id.save_game_text);

        //Setup based on what the parent activity sent us
        //this.updateGameInfoFromExtras(extras);
        //this.populateActivityView();
        //Setting logic for SAVE button
        m_saveText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editOrCreateGame();
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

    private void editOrCreateGame() {
       this.updateGameInfo();
       GameListManager.addGame(
            new Game(
                m_gameName,
                "",
                "",
                false,
                false,
                false
            )
       );
       returnToGameList();
    }

    private void updateGameInfo() {
        m_gameName = ((EditText) findViewById(R.id.game_name_input)).getText().toString();
    }

    private void returnToGameList() {
        Intent intent = new Intent(this, GameListActivity.class);
        startActivity(intent);
    }
}
