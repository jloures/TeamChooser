package com.example.capstone.teamchooser.helper_classes;

//Again this class is self descriptive. No need for comments here
public final class Player {
    private String m_playerName = null;
    private long m_playerLevel = 0;
    private Boolean m_isPlayerInOffense;
    private Boolean m_isPlayerPreAssigned;
    private String m_playerTeamName = null;
    //PlayerId should never change, hence its final (a.k.a constant)
    private long m_playerId;
    //To which game this player belongs
    private long m_gameId;

    public Player(
        String playerName,
        long playerLevel,
        Boolean isPlayerInOffense,
        Boolean isPlayerPreAssigned,
        String playerTeamName,
        long gameId
    ) {
        this.m_playerName = playerName;
        this.m_playerLevel = playerLevel;
        this.m_isPlayerInOffense = isPlayerInOffense;
        this.m_isPlayerPreAssigned = isPlayerPreAssigned;
        this.m_playerTeamName = playerTeamName;
        this.m_gameId = gameId;
        this.m_playerId = PlayerListManager.GeneratePlayerId();
    }


    public long getPlayerGameId() {
        return m_gameId;
    }

    public long getPlayerId() {
        return m_playerId;
    }

    public String getPlayerName() {
        return m_playerName;
    }

    public void setPlayerName(String playerName) {
        m_playerName = playerName;
    }

    public long getPlayerLevel() {
        return m_playerLevel;
    }

    public void setPlayerLevel(long m_playerLevel) {
        this.m_playerLevel = m_playerLevel;
    }

    public Boolean isPlayerInOffense() {
        return m_isPlayerInOffense;
    }

    public void setIsPlayerInOffense(Boolean m_isPlayerInOffense) {
        this.m_isPlayerInOffense = m_isPlayerInOffense;
    }

    public Boolean isPlayerPreAssigned() {
        return m_isPlayerPreAssigned;
    }

    public void setIsPlayerPreAssigned(Boolean m_isPlayerPreAssigned) {
        this.m_isPlayerPreAssigned = m_isPlayerPreAssigned;
    }

    public String getPlayerTeamName() {
        return m_playerTeamName;
    }

    public void setPlayerTeamName(String m_playerTeamName) {
        this.m_playerTeamName = m_playerTeamName;
    }

}
