package com.example.dtagtask.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.dtagtask.dagger.NotesApplication;
import com.example.dtagtask.adapters.HomeRvAdapter;
import com.example.dtagtask.models.NotesEntity;
import com.example.dtagtask.R;
import com.example.dtagtask.viewModels.NotesVM;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HomeFragment extends Fragment {

    @Inject
    NotesVM notesVM;

    private TextView tvNoNotes;
    private RecyclerView rvHome;
    private HomeRvAdapter rvAdapter;
    private List<NotesEntity> notesList = new ArrayList<>();
    private FloatingActionButton fab;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((NotesApplication) getContext().getApplicationContext()).getComponent().inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rvHome = view.findViewById(R.id.rv_home);
        fab = view.findViewById(R.id.fab);
        tvNoNotes = view.findViewById(R.id.tv_no_notes);
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setUpRecyclerViewItemClick();
        navigateToAddNoteFragment();
        setUpObserver();
        setUpRecyclerView();

    }

    private void showRvWhenHavingNotes() {

        if (notesList.isEmpty()) {
            tvNoNotes.setVisibility(View.VISIBLE);
            rvHome.setVisibility(View.GONE);

        } else {
            rvHome.setVisibility(View.VISIBLE);
            tvNoNotes.setVisibility(View.GONE);

        }
    }

    private void setUpRecyclerViewItemClick() {
        rvAdapter = new HomeRvAdapter(notesList, getContext(), new HomeRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final View view, final int position) {

                setUpEditOrDeleteDialog(view, position);

            }
        });
    }

    private void setUpEditOrDeleteDialog(View view, int position) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setMessage("do you want to edit or delete this note?");

        dialogEditButton(view, position, dialog);

        dialogDeleteButton(position, dialog);

        dialog.show();
    }

    private void dialogDeleteButton(final int position, AlertDialog.Builder dialog) {

        dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                deleteNote(position);
            }
        });
    }

    private void dialogEditButton(final View view, final int position, AlertDialog.Builder dialog) {
        dialog.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Bundle bundle = sendInsertedNoteToEditFragment(position);
                navigateToEditFragment(bundle, view);

            }
        });
    }

    private void navigateToEditFragment(Bundle bundle, View view) {
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_editNoteFragment, bundle);
    }

    private Bundle sendInsertedNoteToEditFragment(int position) {
        NotesEntity notesEntity = notesList.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("notesEntity", notesEntity);
        return bundle;
    }

    private void deleteNote(int position) {
        NotesEntity notesEntity = notesList.get(position);
        notesVM.deleteNote(notesEntity);
        rvAdapter.notifyDataSetChanged();
    }

    private void navigateToAddNoteFragment() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_addNoteFragment);

            }
        });
    }

    private void setUpObserver() {
        notesVM.getAllNotes().observe(HomeFragment.this, new Observer<List<NotesEntity>>() {
            @Override
            public void onChanged(List<NotesEntity> dbNotesEntities) {
                notesList.clear();
                notesList.addAll(dbNotesEntities);
                showRvWhenHavingNotes();
                rvAdapter.notifyDataSetChanged();
            }
        });


    }

    private void setUpRecyclerView() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);

        rvHome.setItemAnimator(new DefaultItemAnimator());

        rvHome.addItemDecoration(new DividerItemDecoration(rvHome.getContext(),
                layoutManager.getOrientation()));

        rvHome.setLayoutManager(layoutManager);
        rvHome.setAdapter(rvAdapter);
        rvAdapter.notifyDataSetChanged();
    }


}
