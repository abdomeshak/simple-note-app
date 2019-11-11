package com.example.dtagtask.dao;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.example.dtagtask.models.NotesEntity;

import java.util.List;

@Dao
public interface NoteDAO {

    @Query("SELECT * FROM notes")
    LiveData<List<NotesEntity>> getAllNotes();

    @Insert
    void insertNote(NotesEntity notesEntity);

    @Update
    void updateNote(NotesEntity notesEntity);

    @Delete
    void deleteNote(NotesEntity notesEntity);

/*
    // for paging
    @Query("SELECT * FROM notes ORDER BY id DESC")
    DataSource<Long, NotesEntity> notesById();
*/


}
