package com.example.dtagtask.viewModels.Implementation;

import com.example.dtagtask.dataProviders.NotesDP;
import com.example.dtagtask.database.NoteDatabase;
import com.example.dtagtask.viewModels.Implementation.NotesVMImplementation;
import com.example.dtagtask.viewModels.NotesVM;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelModule {

    @Provides
    @Singleton
    public NotesVM providesNotesVM(NotesDP notesDP) {
        return new NotesVMImplementation(notesDP);
    }
}
