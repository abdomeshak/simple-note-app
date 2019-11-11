package com.example.dtagtask.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import com.example.dtagtask.dagger.NotesApplication;
import com.example.dtagtask.R;
import com.example.dtagtask.viewModels.NotesVM;

import javax.inject.Inject;


public class HomeActivity extends AppCompatActivity {

    @Inject
     NotesVM notesVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ((NotesApplication) getApplication()).getComponent().inject(HomeActivity.this);
        getSupportActionBar().hide();

    }



}
