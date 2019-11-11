package com.example.dtagtask.dagger;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.dtagtask.dataProviders.Implementation.DataProviderModule;
import com.example.dtagtask.database.NoteDatabase;
import com.example.dtagtask.viewModels.Implementation.ViewModelModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ViewModelModule.class, DataProviderModule.class})
public class AppModule {

    private Application app;

    AppModule(Application app) {
        this.app = app;
    }

    @Provides
    public Context providesContext() {
        return app;
    }


    @Provides
    @Singleton
    public NoteDatabase providesDatabaseObject(Context context) {
        return Room.databaseBuilder(context, NoteDatabase.class, "notes_database")
                .fallbackToDestructiveMigration()
                .build();
    }

}
