package com.example.dtagtask.dataProviders;

import androidx.lifecycle.LiveData;

import com.example.dtagtask.models.NotesEntity;

import java.util.List;


public interface NotesDP {

    LiveData<List<NotesEntity>> getAllNotes();

    void insertNote(NotesEntity note);

    void updateNote(NotesEntity notesEntity);

    void deleteNote(NotesEntity note);
}
