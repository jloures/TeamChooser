package com.example.capstone.teamchooser.helperClasses;


//Pretty straightforward no need for comments here :P
public class Game {

    private String m_gameName = null;
    private String m_teamAName = null;
    private String m_teamBName = null;
    private Boolean m_isUsingSO;
    private Boolean m_isBODCount;
    private Boolean m_isUsingBalanceODRatings;
    private long m_gameId;

    public Game(
        String gameName,
        String teamAName,
        String teamBName,
        Boolean isUsingSO,
        Boolean isBODCount,
        Boolean isUsingBalanceODRatings
    ) {
        this.m_gameName = gameName;
        this.m_teamAName = teamAName;
        this.m_teamBName = teamBName;
        this.m_isBODCount = isBODCount;
        this.m_isUsingSO = isUsingSO;
        this.m_isUsingBalanceODRatings = isUsingBalanceODRatings;
    }

    public Boolean isUsingBalanceODRatings() {
        return m_isUsingBalanceODRatings;
    }

    public void setUsingBalanceODRatings(Boolean m_isUsingBalanceODRatings) {
        this.m_isUsingBalanceODRatings = m_isUsingBalanceODRatings;
    }

    public long getGameId() {
        return m_gameId;
    }

    public void setGameId(long m_gameId) {
        this.m_gameId = m_gameId;
    }

    public String getGameName() {
        return m_gameName;
    }

    public void setGameName(String m_gameName) {
        this.m_gameName = m_gameName;
    }

    public String getTeamAName() {
        return m_teamAName;
    }

    public void setTeamAName(String m_teamAName) {
        this.m_teamAName = m_teamAName;
    }

    public String getTeamBName() {
        return m_teamBName;
    }

    public void setTeamBName(String m_teamBName) {
        this.m_teamBName = m_teamBName;
    }

    public Boolean isUsingSO() {
        return m_isUsingSO;
    }

    public void setUsingSO(Boolean usingSO) {
        m_isUsingSO = usingSO;
    }

    public Boolean isBODCount() { return m_isBODCount; }

    public void setBODCount(Boolean BODCount) {
        m_isBODCount = BODCount;
    }

    public void setAllProperties(
        String gameName,
        String teamAName,
        String teamBName,
        Boolean isUsingSO,
        Boolean isBODCount,
        Boolean isUsingBalanceODRatings
    ) {
        this.m_gameName = gameName;
        this.m_teamAName = teamAName;
        this.m_teamBName = teamBName;
        this.m_isBODCount = isBODCount;
        this.m_isUsingSO = isUsingSO;
        this.m_isUsingBalanceODRatings = isUsingBalanceODRatings;
    }
}
