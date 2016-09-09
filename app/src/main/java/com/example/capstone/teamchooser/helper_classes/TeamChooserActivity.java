package com.example.capstone.teamchooser.helper_classes;

import android.support.v7.app.AppCompatActivity;

//This class will hold any global variables and methods that
//will be avaible to all activities in this project
public class TeamChooserActivity extends AppCompatActivity {
    //Size of the font in dip units
    private static int m_fontSize = 15;

    public static int getFontSize() {
        return m_fontSize;
    }

    public static void setFontSize(int m_fontSize) {
        TeamChooserActivity.m_fontSize = m_fontSize;
    }
}
