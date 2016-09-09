package com.example.capstone.teamchooser.helper_classes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.capstone.teamchooser.CreateOrEditGameActivity;
import com.example.capstone.teamchooser.R;

import java.util.ArrayList;

public class PlayerListManager extends BaseAdapter {
    //List will all the players
    private ArrayList<Player> m_players = null;
    //Variable that will take care of assigning ids to players
    //Ideally we should be using guids
    private static long m_numberOfPlayersCreated = 0;
    //Context variable for doing actions with activities
    private Context m_context = null;
    private LayoutInflater m_inflater = null;

    public PlayerListManager(Context context, long gameId ) {
        m_context = context;
        m_inflater = (LayoutInflater) m_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        m_players = GameListManager.getGameById(gameId).getAllPlayers();
    }

    public static long GeneratePlayerId() {
        return ++m_numberOfPlayersCreated;
    }

    @Override
    public int getCount() {
        return m_players.size();
    }

    @Override
    public Object getItem(int position) {
        return m_players.get(position);
    }

    @Override
    public long getItemId(int position) {
       return m_players.get(position).getPlayerId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Player player = m_players.get(position);
        //Check if we need to create our view (first time, a.k.a view recycling)
        View rowView = convertView;
        if( rowView == null ) {
            rowView = m_inflater.inflate(R.layout.generic_custom_row_item,null);
        }
        //Setting an onClick handler for the entire row
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        TextView playerName = (TextView) rowView.findViewById(R.id.generic_row_item_name);
        //Set the text in the TextView to be that of the players Name
        playerName.setText(player.getPlayerName());
        //Set the image to be that of the info
        ImageView infoIcon = (ImageView) rowView.findViewById(R.id.generic_row_item_info);
        infoIcon.setImageResource(R.drawable.get_info_white);
        infoIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( m_context, CreateOrEditGameActivity.class );
                m_context.startActivity(intent);
            }
        });
        return rowView;
    }
}
