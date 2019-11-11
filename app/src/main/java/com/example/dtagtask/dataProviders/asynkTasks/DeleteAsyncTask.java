package com.example.dtagtask.dataProviders.asynkTasks;

import android.os.AsyncTask;

import com.example.dtagtask.dao.NoteDAO;
import com.example.dtagtask.models.NotesEntity;

public class DeleteAsyncTask extends AsyncTask<NotesEntity, Void, Void> {

    private NoteDAO asyncTaskDAO;

    public DeleteAsyncTask(NoteDAO noteDAO) {
        this.asyncTaskDAO = noteDAO;
    }

    @Override
    protected Void doInBackground(NotesEntity... dbNotesEntities) {
        asyncTaskDAO.deleteNote(dbNotesEntities[0]);
        return null;
    }
}
