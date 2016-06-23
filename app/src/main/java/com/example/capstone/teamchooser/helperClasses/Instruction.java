package com.example.capstone.teamchooser.helperClasses;

import java.io.Serializable;

//Simple class just for holding name and instruction description
public class Instruction implements Serializable{
    private String m_instructionTitle = null;
    private String m_instructionDescription = null;

    public Instruction(String title, String description) {
        this.m_instructionDescription = description;
        this.m_instructionTitle = title;
    }
    public Instruction() {}
    public void setTitle( String title ) {
        this.m_instructionTitle = title;
    }
    public String getTitle() {
        return this.m_instructionTitle;
    }
    public void setDescription( String description ) { this.m_instructionDescription = description; }
    public String getDescription() {
        return this.m_instructionDescription;
    }

    //Nothing fancy here, just returning the title
    //We need to override this because by default the Adapter will call
    //the .toString() method on all elements that are present in the menu
    @Override
    public String toString() {
        return this.m_instructionTitle;
    }
}