package com.example.dtagtask.viewModels;

import androidx.lifecycle.LiveData;

import com.example.dtagtask.models.NotesEntity;

import java.util.List;


public interface NotesVM {


    LiveData<List<NotesEntity>> getAllNotes();

    void insertNote(NotesEntity note);

    void updateNote(NotesEntity notesEntity);

    void deleteNote(NotesEntity note);
}
