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


public class EditNoteFragment extends Fragment {

    @Inject
    NotesVM notesVM;

    private EditText etTitle;
    private EditText etDetails;
    private Button btnEdit;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((NotesApplication) getContext().getApplicationContext()).getComponent().inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_note, container, false);
        etTitle = view.findViewById(R.id.et_title);
        etDetails = view.findViewById(R.id.et_details);
        btnEdit = view.findViewById(R.id.btn_edit_note);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final NotesEntity updatedNoteEntity = getInsertedNoteFromHomeFragment();

        setUpClickListeners(updatedNoteEntity);


    }

    private void setUpClickListeners(final NotesEntity updatedNoteEntity) {

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validateNote(view, updatedNoteEntity);


            }
        });
    }

    private void validateNote(View view, NotesEntity updatedNoteEntity) {

        String title = "";
        String details = "";

        if (!TextUtils.isEmpty(etTitle.getText()) && !TextUtils.isEmpty(etDetails.getText())) {

            title = etTitle.getText().toString();
            details = etDetails.getText().toString();
            updateNote(updatedNoteEntity, title, details);

            Toast.makeText(getContext(), "Note has been updated successfully!", Toast.LENGTH_LONG).show();

            navigateToHomeFragment(view);

        } else {
            Toast.makeText(getContext(), "There is empty field!", Toast.LENGTH_LONG).show();
        }
    }

    private void navigateToHomeFragment(View view) {
        Navigation.findNavController(view).navigate(R.id.action_editNoteFragment_to_homeFragment);
    }

    private void updateNote(NotesEntity updatedNoteEntity, String title, String details) {
        updatedNoteEntity.setTitle(title);
        updatedNoteEntity.setDetails(details);
        notesVM.updateNote(updatedNoteEntity);
    }

    private NotesEntity getInsertedNoteFromHomeFragment() {
        NotesEntity notesEntity = null;
        Bundle args = getArguments();
        if (args != null) {
            notesEntity = (NotesEntity) args.getSerializable("notesEntity");
            etTitle.setText(notesEntity.getTitle());
            etDetails.setText(notesEntity.getDetails());

        }
        return notesEntity;
    }
}
