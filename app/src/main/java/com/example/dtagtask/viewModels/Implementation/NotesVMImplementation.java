package com.example.dtagtask.viewModels.Implementation;

import androidx.lifecycle.LiveData;


import com.example.dtagtask.dataProviders.NotesDP;
import com.example.dtagtask.models.NotesEntity;
import com.example.dtagtask.viewModels.NotesVM;

import java.util.List;


public class NotesVMImplementation implements NotesVM {

    private NotesDP notesDP;


    public NotesVMImplementation(NotesDP notesDP) {
        this.notesDP = notesDP;
    }

    @Override
    public LiveData<List<NotesEntity>> getAllNotes() {
        return notesDP.getAllNotes();
    }

    @Override
    public void insertNote(NotesEntity note) {
        notesDP.insertNote(note);
    }

    @Override
    public void updateNote(NotesEntity notesEntity) {
        notesDP.updateNote(notesEntity);
    }

    @Override
    public void deleteNote(NotesEntity note) {
        notesDP.deleteNote(note);
    }
}
