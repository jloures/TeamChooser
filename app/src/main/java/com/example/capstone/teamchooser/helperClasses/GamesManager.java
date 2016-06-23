package com.example.capstone.teamchooser.helperClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

//Superclass for games. It will take care of generating the gameId as
//well as keeping track of the listOfGames
public class GamesManager implements Serializable{

    //This variable will hold all the games
    //TODO make sure this data is persistent
    private static HashMap<Integer, Game> m_listOfGames = null;
    //This is a variable counting how many games currently exist
    //GameId
    private static Integer m_numberOfGamesCreated = 0;

    //TODO we could optimize this in the future
    public static ArrayList<Game> getAllGames() {
        if( m_listOfGames == null ) {
            m_listOfGames = new HashMap<>();
        }
        ArrayList<Game> listOfGames = new ArrayList<>();
        Iterator iterator = m_listOfGames.entrySet().iterator();
        while(iterator.hasNext()) {
            HashMap.Entry<Integer,Game> temp_gameEntry = (HashMap.Entry<Integer,Game>)iterator.next();
            listOfGames.add(temp_gameEntry.getValue());
        }
        return listOfGames;
    }

    public static Game getGameById( Integer gameId ) {
        if( m_listOfGames == null ) {
            return null;
        }
        return m_listOfGames.get( gameId );
    }

    public static void addGame( Game newGame ) {
        if( m_listOfGames == null ) {
            m_listOfGames = new HashMap<>();
        }
        m_listOfGames.put( newGame.getGameId(), newGame );
    }

    public static void deleteGameById( Integer gameId ) {
        if( m_listOfGames == null ) {
            return;
        }
        m_listOfGames.remove( gameId );
    }

    public static Integer getNewGameId() {
        return ++m_numberOfGamesCreated;
    }

}
