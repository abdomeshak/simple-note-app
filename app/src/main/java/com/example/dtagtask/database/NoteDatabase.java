package com.example.dtagtask.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.dtagtask.dao.NoteDAO;
import com.example.dtagtask.models.NotesEntity;

@Database(entities = {NotesEntity.class}, version = 1 ,exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    public abstract NoteDAO getNoteDao();
}