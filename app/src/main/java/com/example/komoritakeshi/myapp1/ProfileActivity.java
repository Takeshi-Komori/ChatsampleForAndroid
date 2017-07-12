package com.example.komoritakeshi.myapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    TextView nameTextView;
    TextView genderAndAgeTextView;
    TextView regionTextView;
    TextView bioTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nameTextView = (TextView) findViewById(R.id.name_prof);
        genderAndAgeTextView = (TextView) findViewById(R.id.gender_age_prof);
        regionTextView = (TextView) findViewById(R.id.region_prof);
        bioTextView = (TextView) findViewById(R.id.biography_prof);
        setValue();

    }

    public void myProfEditBtnClick(View view) {
        switch (view.getId()) {
            case R.id.profileEditBtn: {
                Intent intent = new Intent(this, ProfileEditActivity.class);
                startActivity(intent);
            }
        }
    }

    public void setValue() {
        if (Me.getInstance().isRegisterd(ProfileActivity.this)) {
            nameTextView.setText(Me.getInstance().getNameFromLocal(ProfileActivity.this));
            genderAndAgeTextView.setText(Me.getInstance().getGenderFromLocal(ProfileActivity.this) + " / "
                    + Me.getInstance().getAgeFromLocal(ProfileActivity.this));
            regionTextView.setText(Me.getInstance().getRegionFromLocal(ProfileActivity.this));
            bioTextView.setText(Me.getInstance().getBiographyFromLocal(ProfileActivity.this));
        }
    }
}
