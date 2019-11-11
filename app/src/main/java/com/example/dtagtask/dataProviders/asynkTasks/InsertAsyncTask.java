package com.example.dtagtask.dataProviders.asynkTasks;

import android.os.AsyncTask;

import com.example.dtagtask.dao.NoteDAO;
import com.example.dtagtask.models.NotesEntity;

public class InsertAsyncTask extends AsyncTask<NotesEntity, Void, Void> {

    private NoteDAO asyncTaskDAO;

    public InsertAsyncTask(NoteDAO noteDAO) {
        this.asyncTaskDAO = noteDAO;
    }

    @Override
    protected Void doInBackground(NotesEntity... dbNotesEntities) {
        asyncTaskDAO.insertNote(dbNotesEntities[0]);
        return null;
    }
}
