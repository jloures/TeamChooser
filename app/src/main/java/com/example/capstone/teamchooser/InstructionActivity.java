package com.example.capstone.teamchooser;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.widget.TextView;

import com.example.capstone.teamchooser.helperClasses.Instruction;
import com.example.capstone.teamchooser.helperClasses.TeamChooserActivity;

public class InstructionActivity extends TeamChooserActivity {

    private Toolbar m_toolbar;
    private Instruction m_instruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Info being passed from the previous activity to this one
        Bundle extras = getIntent().getExtras();
        m_instruction = new Instruction();
        m_instruction.setDescription(extras.getString("description"));
        m_instruction.setTitle(extras.getString("title"));
        setContentView(R.layout.activity_instruction);
        //This defines our toolbar as our default toolbar
        m_toolbar = (Toolbar) findViewById(R.id.instruction_toolbar);
        setSupportActionBar(m_toolbar);
        getSupportActionBar().setTitle(m_instruction.getTitle());
        //All we are doing here is to set up the back button
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        //Making the back button custom ( in this case changing the color to white )
        ab.setHomeAsUpIndicator(R.drawable.back_button_white);
        TextView tv = (TextView) findViewById(R.id.instruction_description);
        tv.setText(m_instruction.getDescription());
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, this.m_fontSize);
    }
}
