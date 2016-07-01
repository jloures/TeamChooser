package com.example.capstone.teamchooser.helperClasses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.capstone.teamchooser.CreateOrEditGameActivity;
import com.example.capstone.teamchooser.GameListActivity;
import com.example.capstone.teamchooser.R;

import java.util.ArrayList;

//Superclass for games. It will take care of generating the gameId as
//well as keeping track of the listOfGames
public class GamesManager extends BaseAdapter {

    //This variable will hold all the games
    //TODO make sure this data is persistent
    private static ArrayList<Game> m_listOfGames = null;
    //We need this to keep track of our gameIds
    private static long m_numberOfGames = 0;
    //This is the context of our GameListActivity
    private Context m_context = null;
    //Our inflater to add our row view to the the list view
    private static LayoutInflater m_inflater = null;

    public GamesManager( Context context ) {
        m_context = context;
        m_inflater = (LayoutInflater) m_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public static ArrayList<Game> getAllGames() {
        if( m_listOfGames == null ) {
            m_listOfGames = new ArrayList<>();
        }
        return m_listOfGames;
    }

    public static void addGame( Game newGame ) {
        if( m_listOfGames == null ) {
            m_listOfGames = new ArrayList<>();
        }
        newGame.setGameId( generateGameId() );
        m_listOfGames.add( newGame );
    }

    public static Game getGameById( long gameId ) {
        if( m_listOfGames == null ) {
            return null;
        }
        for ( Game game: m_listOfGames ) {
            if( game.getGameId() == gameId ) {
                return game;
            }
        }
        return null;
    }

    public static void deleteGameById( long gameId ) {
        if( m_listOfGames == null ) {
            return;
        }
        for ( Game game: m_listOfGames ) {
            if( game.getGameId() == gameId ) {
                m_listOfGames.remove( game );
                return;
            }
        }
    }

    public static long generateGameId() {
        return ++m_numberOfGames;
    }

    //These are the methods that must be overriden in order to inherit the class BaseAdapter
    //(a.k.a we need to do this in order to create custom views in our listView)
    @Override
    public int getCount() {
       return m_listOfGames.size();
    }

    //We don't care about this method because this class already knows how to find
    //an item based on its 'id'
    @Override
    public Object getItem(int position) {
        return m_listOfGames.get( position );
    }

    //We don't need this
    @Override
    public long getItemId(int position) {
        return (long) m_listOfGames.get( position ).getGameId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Game game = m_listOfGames.get(position);
        //Check if we need to create our view (first time, a.k.a view recycling)
        View rowView = convertView;
        if( rowView == null ) {
            rowView = m_inflater.inflate(R.layout.game_list_row_item,null);
        }
        TextView gameName = (TextView) rowView.findViewById(R.id.game_list_row_item_game_name_text);
        //Set the text in the TextView to be that of the game
        gameName.setText(game.getGameName());
        //Set the image to be that of the info
        ImageView infoIcon = (ImageView) rowView.findViewById(R.id.game_list_row_item_info_icon);
        infoIcon.setImageResource(R.drawable.get_info_white);
        infoIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //On click we want to take the user to the edit game page (a.k.a CreateOrEditGameActivity)
                //position is the index of our object in the list
                //adding some extra info to be passed to the next activity
                Intent intent = new Intent( m_context, CreateOrEditGameActivity.class );
                //TODO pass the Game object instead of doing this
                intent.putExtra("gameId", Long.toString(game.getGameId()));
                intent.putExtra("gameName", game.getGameName());
                intent.putExtra("teamAName", game.getTeamAName());
                intent.putExtra("teamBName", game.getTeamBName());
                intent.putExtra("isUsingSO", game.isUsingSO() ? "true" : "false");
                intent.putExtra("isBODCount", game.isBODCount() ? "true" : "false");
                intent.putExtra("isBalanceODRatings", game.isUsingBalanceODRatings() ? "true" : "false");
                m_context.startActivity(intent);
            }
        });
        return rowView;
    }
}
