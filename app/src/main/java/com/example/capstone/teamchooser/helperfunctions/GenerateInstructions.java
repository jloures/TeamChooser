package com.example.capstone.teamchooser.helperfunctions;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.parsers.*;
import java.io.*;

//We will be using this class to read the instructions from a file and add it
//the different sections to the list in AppInfo

public class GenerateInstructions {
    private static ArrayList<Instruction> m_instructionsList = null;

    public static ArrayList<Instruction> getAllInstructions() {
        if( m_instructionsList == null ) {
            generateSimple("Intruction Title", "Instruction Description");
        }
        return m_instructionsList;
    }

    public static ArrayList<Instruction> generateFromFile() {
        return null;
    }

    //This is a method for adding new instructions directly from a different class and with no
    //need for having a file
    public static void generateSimple( String title, String description ) {
        if ( m_instructionsList == null ) {
            m_instructionsList = new ArrayList<>();
        }

        Instruction newInstruction = new Instruction();
        newInstruction.setTitle( title );
        newInstruction.setDescription( description );

        m_instructionsList.add( newInstruction );
    }
}
