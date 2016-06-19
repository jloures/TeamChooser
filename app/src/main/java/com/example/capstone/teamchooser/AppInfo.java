package com.example.capstone.teamchooser;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AppInfo extends AppCompatActivity {

    //This is our toolbar, which has the title 'App Info' and a back button that is white
    private Toolbar m_toolbar;
    //This is an array with the default list of the how tos of the app
    private ArrayList<String> m_listOfHowTos;
    //The array adapter to hold all of our items
    private ArrayAdapter<String> m_adapter;
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
        m_listOfHowTos = populateHowTosList();
        m_adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_expandable_list_item_1,
                m_listOfHowTos
        );
        m_listView = (ListView) findViewById(R.id.app_info_list);
        m_listView.setAdapter(m_adapter);
    }

    private ArrayList<String> populateHowTosList() {
        ArrayList<String> list = new ArrayList<String>();
        for( int i = 0; i < 30; i++) {
            list.add("My name is " + i);
        }
        return list;
    }
}
