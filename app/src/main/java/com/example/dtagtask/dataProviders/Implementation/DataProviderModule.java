package com.example.dtagtask.dataProviders.Implementation;

import com.example.dtagtask.dataProviders.Implementation.NotesDPImplementation;
import com.example.dtagtask.dataProviders.NotesDP;
import com.example.dtagtask.database.NoteDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataProviderModule {

    @Provides
    @Singleton
    public NotesDP providesNotesDP(NoteDatabase persistence) {
        return new NotesDPImplementation(persistence);
    }



}
