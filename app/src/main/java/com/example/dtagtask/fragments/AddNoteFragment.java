package com.example.dtagtask.fragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dtagtask.dagger.NotesApplication;
import com.example.dtagtask.R;
import com.example.dtagtask.models.NotesEntity;
import com.example.dtagtask.viewModels.NotesVM;

import javax.inject.Inject;


public class AddNoteFragment extends Fragment {

    @Inject
    NotesVM notesVM;

    private EditText etTitle;
    private EditText etDetails;
    private Button btnAddNote;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((NotesApplication) getContext().getApplicationContext()).getComponent().inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_note, container, false);
        etTitle = view.findViewById(R.id.et_title);
        etDetails = view.findViewById(R.id.et_details);
        btnAddNote = view.findViewById(R.id.btn_add_note);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setClickListeners();
    }

    private void setClickListeners() {

        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validateNote(view);
            }
        });
    }

    private void validateNote(View view) {
        String title;
        String details;

        if (!TextUtils.isEmpty(etTitle.getText())
                && !TextUtils.isEmpty(etDetails.getText())) {
            title = etTitle.getText().toString();
            details = etDetails.getText().toString();

            addNote(title, details);
            navigateToHomeFragment(view);

        } else {
            Toast.makeText(getContext(), "There is empty field!", Toast.LENGTH_LONG).show();
        }
    }

    private void navigateToHomeFragment(View view) {
        Navigation.findNavController(view).navigate(R.id.action_addNoteFragment_to_homeFragment);
    }

    private void addNote(String title, String details) {
        NotesEntity notesEntity = new NotesEntity(title, details);
        notesVM.insertNote(notesEntity);
        Toast.makeText(getContext(), "note inserted", Toast.LENGTH_LONG).show();

    }
}
