package com.example.dtagtask.dagger;

import com.example.dtagtask.activities.HomeActivity;
import com.example.dtagtask.fragments.AddNoteFragment;
import com.example.dtagtask.fragments.EditNoteFragment;
import com.example.dtagtask.fragments.HomeFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(HomeActivity homeActivity);
    void inject(HomeFragment homeFragment);
    void inject(AddNoteFragment addNoteFragment);
    void inject(EditNoteFragment noteDetailsFragment);




}
