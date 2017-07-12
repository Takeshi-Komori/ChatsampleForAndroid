package com.example.komoritakeshi.myapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class ProfileEditActivity extends AppCompatActivity {
    EditText nameEditText;
    Spinner genderSpinner;
    Spinner ageSpinner;
    Spinner regionSpinner;
    EditText bioEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        nameEditText = (EditText) findViewById(R.id.name_field);
        genderSpinner = (Spinner) findViewById(R.id.gender_spinner);
        ageSpinner = (Spinner) findViewById(R.id.age_spinner);
        regionSpinner = (Spinner) findViewById(R.id.region_spinner);
        bioEditText = (EditText) findViewById(R.id.bio_field);

    }

    void saveBtnClick(View view) {
        switch (view.getId()) {
            case R.id.profileSaveBtn: {
                Me.getInstance().createMe4Database(nameEditText.getText().toString(),
                        genderSpinner.getSelectedItem().toString(),
                        Integer.parseInt(ageSpinner.getSelectedItem().toString()),
                        regionSpinner.getSelectedItem().toString(),
                        bioEditText.getText().toString(),ProfileEditActivity.this);
                finish();
            }
        }
    }
}
