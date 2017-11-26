package com.example.louman.passwordchecker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
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

public class page2 extends AppCompatActivity {

    private RadioGroup languageSelectors;
    private RadioButton rb1,rb2;
    private String languagePreference;
    private TextView questionString;
    private TextView cityPrompt;
    private TextView statePrompt;
    private TextView languagePreferencePrompt;
    private EditText cityField;
    private EditText stateField;
    private Button submitButton;
    private Spinner spinner;
    private ArrayAdapter<CharSequence> spinnerAdapter;
    private RatingBar ratingBar;
    private TextView txtRatingValue;
    private Button btnSubmit;

    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        languageSelectors = (RadioGroup) findViewById(R.id.languageSelectors);
        rb1= (RadioButton) findViewById(R.id.radioButton1);
        rb2= (RadioButton) findViewById(R.id.radioButton2);
        submitButton = (Button) findViewById(R.id.buttonSubmit);
        spinner = (Spinner) findViewById(R.id.spinner);
        addListenerOnRatingBar();
        addListenerOnButton();

        init();
    }

    public void testbtn_onclick(View v){
    }

    public void addListenerOnRatingBar() {

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        txtRatingValue = (TextView) findViewById(R.id.txtRatingValue);

        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                txtRatingValue.setText(String.valueOf(rating));

            }
        });
    }

    public void addListenerOnButton() {

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        //if click on me, then display the current rating value.
        btnSubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(page2.this,
                        String.valueOf(ratingBar.getRating()),
                        Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void init() {

        /******* Default settings ****/
        userInfo = new UserInfo();

        rb1.setChecked(true);
        // Create an ArrayAdapter using the string array and a default spinner layout
        spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.visitortype, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(spinnerAdapter);

        languageSelectors.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (radioGroup.getCheckedRadioButtonId() == rb1.getId()) {
                    languagePreference = "English";
                    setStringsToEnglish();

                } else if (radioGroup.getCheckedRadioButtonId() == rb2.getId()) {
                    languagePreference = "Spanish";
                    setStringsToSpanish();

                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUserInfoWithEnteredData();
                Intent intent = new Intent(view.getContext(), page3.class);
                startActivity(intent);
            }
        });

    }

    private void setStringsToEnglish() {

        questionString.setText(getResources().getString(R.string.question_english));
        languagePreferencePrompt.setText(getResources().getString(R.string.lang_pref_english));
        cityPrompt.setText(getResources().getString(R.string.city_prompt_english));
        statePrompt.setText(getResources().getString(R.string.state_prompt_english));
    }
/*
        //Set spinner data again
        spinnerAdapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.question_array_english, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }
*/
    private void setStringsToSpanish() {

        questionString.setText(getResources().getString(R.string.question_spanish));
        languagePreferencePrompt.setText(getResources().getString(R.string.lang_pref_spanish));
        cityPrompt.setText(getResources().getString(R.string.city_prompt_spanish));
        statePrompt.setText(getResources().getString(R.string.state_prompt_spanish));
/*
        //Set spinner data again
        spinnerAdapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.question_array_spanish, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
*/
    }

    private void setUserInfoWithEnteredData() {
        String memberStatus = spinner.getSelectedItem().toString();
        userInfo.setInfo(languagePreference, memberStatus, "test", "test");
        userInfo.print();
    }

    //We save all the information that was entered in into this object when you click the submit button.
    private class UserInfo {

        String languagePreference;
        String memberStatus;
        String city;
        String state;
        Date currentDate;

        public UserInfo() {

            //Sets the current date to "right now" based on phone's internal clock
            currentDate = new Date();

            //Default values
            languagePreference = "English";
            memberStatus = "Student";
            city = "Sacramento";
            state = "CA";
        }

        void setInfo(String languagePreference, String memberStatus, String city, String state) {
            this.languagePreference = languagePreference;
            this.memberStatus = memberStatus;
            this.city = city;
            this.state = state;
        }

        //For debugging purposes
        void print()  {
            Log.d("Filter", "Info "+languagePreference+" "+memberStatus+" "+city+" "+state+" "+ currentDate.toString());
        }
    }
}

