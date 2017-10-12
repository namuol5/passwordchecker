package com.example.louman.passwordchecker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView.OnEditorActionListener;
import android.view.inputmethod.EditorInfo;
import android.content.Intent;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements  OnEditorActionListener, OnClickListener
{
    public static final String EXTRA_MESSAGE = "com.saccity362.scccismobiledesignclassportal";
    private Button buttonsubmit;
    private TextView textwelcome;
    private TextView textusername;
    private TextView textpassword;
    private EditText editusername;
    private EditText editpassword;
    private boolean match,submitOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonsubmit = (Button) findViewById(R.id.buttonsubmit);
        textusername = (TextView) findViewById(R.id.textusername);
        editusername = (EditText) findViewById(R.id.editusername);
        textpassword = (TextView) findViewById(R.id.textpassword);
        editpassword = (EditText) findViewById(R.id.editpassword);
        buttonsubmit.setOnClickListener(this);
        editusername.setOnEditorActionListener(this);
        editpassword.setOnEditorActionListener(this);
        String usernameString = editusername.getText().toString();
        String passwordString = editpassword.getText().toString();
    }
    public void sendMessage(View view) {
        Intent intent = new Intent(this, page2.class);
        EditText editusername =(EditText) findViewById(R.id.editusername);
        String message = editusername.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if ((v.getId() == R.id.buttonsubmit)&& match) sendMessage(v);

    }


    @Override

    public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
    {   String usernameString = editusername.getText().toString();
        String passwordString = editpassword.getText().toString();

        switch (v.getId())
        {
            case R.id.editusername :
                if (actionId ==  EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED)
                {
                    usernameString = editusername.getText().toString();
                }
                break;
            case R.id.editpassword :
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED)
                {   passwordString = editpassword.getText().toString();
                    if (passwordString.equalsIgnoreCase("louman")) match=true;
                    //{Prompt.setText("That's correct");match = true;}  else  Prompt.setText("Incorrect Password!");

                }

        }


        return false;
    }
}




