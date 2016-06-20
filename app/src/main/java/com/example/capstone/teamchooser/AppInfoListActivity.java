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

public class AppInfoListActivity extends AppCompatActivity {

    //This is our toolbar, which has the title 'App Info' and a back button that is white
    private Toolbar m_toolbar;
    //This is an array with the default list of instructions of the app
    private ArrayList<Instruction> m_listOfInstructions = null;
    //The array adapter to hold all of our items
    private ArrayAdapter<Instruction> m_adapter;
    //Since we dont inherit from ListActivity, we need a reference to our default List view
    private ListView m_listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //Defining what xml file we will display
        setContentView(R.layout.activity_app_info_list);
        //This defines our toolbar as our default toolbar
        m_toolbar = (Toolbar) findViewById(R.id.app_info_list_toolbar);
        setSupportActionBar(m_toolbar);
        //All we are doing here is to set up the back button
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        //Making the back button custom ( in this case changing the color to white )
        ab.setHomeAsUpIndicator(R.drawable.back_button_white);
        //Populating list with intruction items
        m_listOfInstructions = GenerateInstructions.getAllInstructions();
        //we have to create an adapter that will take care of putting our 'objects'
        //into a view inside listView. Nothing custom here. Just had to override toString() of
        //Instruction because this is the class that the adapter calls by default to populate the
        //view with
        m_adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_expandable_list_item_1,
                m_listOfInstructions
        );
        //Grab our ListView from the xml file
        m_listView = (ListView) findViewById(R.id.app_info_list);
        //Set the adapter that we want to use for our view
        m_listView.setAdapter(m_adapter);
        //add a click handler for every item in the list
        m_listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               //position is the index of our object in the list
                Instruction ins = (Instruction) parent.getItemAtPosition(position);
               //adding some extra info to be passed to the next activity
                Intent intent = new Intent( getBaseContext(), InstructionActivity.class );
                intent.putExtra("description", ins.getDescription());
                intent.putExtra("title", ins.getTitle());
                startActivity(intent);
           }
       });
    }
}
