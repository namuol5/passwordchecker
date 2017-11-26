package com.example.louman.passwordchecker;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View.OnClickListener;
import java.util.Date;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.provider.MediaStore;
import android.widget.Toast;
import android.net.Uri;



public class page3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);

        //String mailto = "mailto:abmukarram@gmail.com" + "&subject=" + Uri.encode("Hello") + "&body =" + Uri.encode("Are you there");

        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        String [] to = {"abmukarram@gmail.com"};
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT," Sent from my app!");
        emailIntent.putExtra(Intent.EXTRA_TEXT," Next Meeting Next Month!");
        emailIntent.setType("message/rfc822");

        try {
            startActivity(emailIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No Mail App", Toast.LENGTH_LONG).show();
            System.out.println("Mail Not there");
        }
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://google.com"));

        try
        { startActivity(intent);}
        catch (ActivityNotFoundException e)
        {Toast.makeText(this, "Cannot Open Browser", Toast.LENGTH_LONG).show();}


    }
}