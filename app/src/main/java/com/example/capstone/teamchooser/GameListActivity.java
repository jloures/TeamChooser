package com.example.capstone.teamchooser;

import android.content.Intent;
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
import com.example.capstone.teamchooser.helper_classes.TeamChooserActivity;

import java.util.ArrayList;

public class GameListActivity extends TeamChooserActivity {

    private Toolbar m_toolbar = null;
    private ListView m_listView = null;
    private ArrayList<Game> m_listOfGames = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        //Telling android which layout is the one we're supposed to be showing for
        //this activity
        setContentView(R.layout.activity_game_list);
        //Finding our toolbar in the xml file
        m_toolbar = (Toolbar) findViewById(R.id.game_list_toolbar);
        //Setting it as our default toolbar
        setSupportActionBar(m_toolbar);

        //Populating list with existing games
        m_listOfGames = GameListManager.getAllGames();

        //Find our TextView element, we need a reference to it
        //to either hide it or set a custom font size defined in the superclass
        TextView addNewGameText = (TextView) findViewById(R.id.no_games_text_view);
        //We have to check and see if we have any games
        if( m_listOfGames.size() != 0 ) {
           //we have to create an adapter that will take care of putting our 'objects'
           //into a view inside listView.
           //Grab our ListView from the xml file
           m_listView = (ListView) findViewById(R.id.game_list_list_view);
           //Set the adapter that we want to use for our view
           m_listView.setAdapter(new GameListManager(this));
           addNewGameText.setVisibility(View.GONE);
        } else {
            addNewGameText.setTextSize(
                TypedValue.COMPLEX_UNIT_DIP,
                this.getFontSize() * (float)1.4
            );
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.add_game:
                this.callCreateGameActivity();
                break;

            case R.id.get_info:
                this.callAppInfoListActivity();
                break;

            default:
                break;

        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_list_menu, menu);
        return true;
    }

    private void callAppInfoListActivity() {
        Intent intent = new Intent(this, AppInfoListActivity.class);
        startActivity(intent);
    }

    private void callCreateGameActivity() {
        Intent intent = new Intent(this, CreateOrEditGameActivity.class);
        startActivity(intent);
    }
}
