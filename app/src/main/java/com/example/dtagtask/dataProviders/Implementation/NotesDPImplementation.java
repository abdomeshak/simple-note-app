package com.example.dtagtask.dataProviders.Implementation;

import androidx.lifecycle.LiveData;

import com.example.dtagtask.dataProviders.NotesDP;
import com.example.dtagtask.dataProviders.asynkTasks.DeleteAsyncTask;
import com.example.dtagtask.dataProviders.asynkTasks.InsertAsyncTask;
import com.example.dtagtask.dataProviders.asynkTasks.UpdateAsyncTask;
import com.example.dtagtask.database.NoteDatabase;
import com.example.dtagtask.models.NotesEntity;

import java.util.List;


public class NotesDPImplementation implements NotesDP {

    private NoteDatabase noteDatabase;

    public NotesDPImplementation(NoteDatabase noteDatabase) {
        this.noteDatabase = noteDatabase;
    }

    @Override
    public LiveData<List<NotesEntity>> getAllNotes() {
        return noteDatabase.getNoteDao().getAllNotes();
    }

    @Override
    public void insertNote(NotesEntity note) {
        new InsertAsyncTask(noteDatabase.getNoteDao()).execute(note);
    }

    @Override
    public void updateNote(NotesEntity notesEntity) {
        new UpdateAsyncTask(noteDatabase.getNoteDao()).execute(notesEntity);
    }

    @Override
    public void deleteNote(NotesEntity note) {
        new DeleteAsyncTask(noteDatabase.getNoteDao()).execute(note);
    }
}
