package com.example.dtagtask.dataProviders.asynkTasks;

import android.os.AsyncTask;

import com.example.dtagtask.dao.NoteDAO;
import com.example.dtagtask.models.NotesEntity;

public class UpdateAsyncTask extends AsyncTask<NotesEntity, Void, Void> {

    private NoteDAO asyncTaskDAO;

    public UpdateAsyncTask(NoteDAO noteDAO) {
        this.asyncTaskDAO = noteDAO;
    }

    @Override
    protected Void doInBackground(NotesEntity... dbNotesEntities) {
        asyncTaskDAO.updateNote(dbNotesEntities[0]);
        return null;
    }
}

