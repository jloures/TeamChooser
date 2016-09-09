package com.example.capstone.teamchooser;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.capstone.teamchooser.helper_classes.Game;
import com.example.capstone.teamchooser.helper_classes.GameListManager;
import com.example.capstone.teamchooser.helper_classes.Player;
import com.example.capstone.teamchooser.helper_classes.TeamChooserActivity;

import java.util.ArrayList;

//This activity will hold a list of players
public class PlayerListActivity extends TeamChooserActivity {

    private Toolbar m_toolbar = null;
    private ArrayList<Player> m_playerList;
    private long m_gameId;
    private ListView m_listView;
    private Game m_game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);

        //Finding our toolbar in the xml file
        m_toolbar = (Toolbar) findViewById(R.id.player_list_toolbar);
        //Setting it as our default toolbar
        setSupportActionBar(m_toolbar);

        //All we are doing here is to set up the back button
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        //Making the back button custom ( in this case changing the color to white )
        ab.setHomeAsUpIndicator(R.drawable.back_button_white);

        m_gameId = Long.parseLong(getIntent().getExtras().getString("gameId"));
        //Grab the correct game we are showing the players for
        m_game = GameListManager.getGameById(m_gameId);
        //set the title of the action bar to be the game name
        ab.setTitle(m_game.getGameName());
        m_playerList = m_game.getAllPlayers();

        //Find our TextView element, we need a reference to it
        //to either hide it or set a custom font size defined in the superclass
        TextView addNewPlayerText = (TextView) findViewById(R.id.no_players_text_View);
        //We have to check and see if we have any games
        if( m_playerList.size() != 0 ) {
            //we have to create an adapter that will take care of putting our 'objects'
            //into a view inside listView.
            //Grab our ListView from the xml file
            m_listView = (ListView) findViewById(R.id.player_list_list_view);
            //Set the adapter that we want to use for our view
            m_listView.setAdapter(new GameListManager(this));
            addNewPlayerText.setVisibility(View.GONE);
        } else {
            addNewPlayerText.setTextSize(
                    TypedValue.COMPLEX_UNIT_DIP,
                    getFontSize() * (float)1.4
            );
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.make_teams:
                this.goToTeamsActivity();
                break;

            case R.id.add_player:
                this.goToCreateOrEditPlayerActivity();
                break;

            default:
                break;

        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.player_list_menu, menu);
        return true;
    }

    private void goToCreateOrEditPlayerActivity() {
        Intent intent = new Intent(this, CreateOrEditPlayerActivity.class);
        startActivity(intent);
    }

    private void goToTeamsActivity() {
        return;
    }
}
