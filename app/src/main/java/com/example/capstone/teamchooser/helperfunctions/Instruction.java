package com.example.capstone.teamchooser.helperfunctions;

import java.io.Serializable;

//Simple class just for holding name and instruction description
public class Instruction implements Serializable{
    private String m_instructionTitle = null;
    private String m_instructionDescription = null;

    public void setTitle( String name ) {
        this.m_instructionTitle = name;
    }
    public String getTitle() {
        return this.m_instructionTitle;
    }
    public void setDescription( String description ) {
        this.m_instructionDescription = description;
    }
    public String getDescription() {
        return this.m_instructionDescription;
    }

    //Nothing fancy here, just returning the title
    @Override
    public String toString() {
        return this.m_instructionTitle;
    }
}