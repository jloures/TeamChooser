package com.example.capstone.teamchooser;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.capstone.teamchooser.helperfunctions.GenerateInstructions;
import com.example.capstone.teamchooser.helperfunctions.Instruction;

import java.util.ArrayList;

public class AppInfo extends AppCompatActivity {

    //This is our toolbar, which has the title 'App Info' and a back button that is white
    private Toolbar m_toolbar;
    //This is an array with the default list of instructions of the app
    private ArrayList<Instruction> m_listOfInstructions;
    //The array adapter to hold all of our items
    private ArrayAdapter<Instruction> m_adapter;
    //Since we dont inherit from ListActivity, we need a reference to our default List view
    private ListView m_listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Defining what xml file we will display
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);
        //This defines our toolbar as our default toolbar
        m_toolbar = (Toolbar) findViewById(R.id.app_info_toolbar);
        setSupportActionBar(m_toolbar);
        //All we are doing here is to set up the back button
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        //Making the back button custom ( in this case changing the color to white )
        ab.setHomeAsUpIndicator(R.drawable.back_button_white);
        //Populating list with how tos ( a.k.a instructions )
        m_listOfInstructions = populateInstructionsList();
        m_adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_expandable_list_item_1,
                m_listOfInstructions
        );
        m_listView = (ListView) findViewById(R.id.app_info_list);
        m_listView.setAdapter(m_adapter);
    }

    private ArrayList<Instruction> populateInstructionsList() {
        GenerateInstructions.generateSimple("First Instruction", "Welcome");
        return GenerateInstructions.getAllInstructions();
    }
}
