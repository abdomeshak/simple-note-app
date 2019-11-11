package com.example.dtagtask.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dtagtask.R;
import com.example.dtagtask.models.NotesEntity;

import java.util.List;

public class HomeRvAdapter extends RecyclerView.Adapter<HomeRvAdapter.StudentViewHolder> {


    private List<NotesEntity> notesList;
    private Context context;

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public HomeRvAdapter(List<NotesEntity> notesList, Context context, OnItemClickListener mOnItemClickListener) {
        this.notesList = notesList;
        this.context = context;
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.home_rv_items, parent, false);

        return setUpClickListenerInterface(view);
    }



    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, final int position) {

        NotesEntity notesModel = notesList.get(position);
        holder.tvTitle.setText(notesModel.getTitle());
        holder.tvDescription.setText(notesModel.getDetails());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    private StudentViewHolder setUpClickListenerInterface(View view) {
        final StudentViewHolder studentViewHolder = new StudentViewHolder(view);
        studentViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(view, studentViewHolder.getAdapterPosition());
            }
        });
        return studentViewHolder;
    }


    class StudentViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvDescription;

        StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_details);
        }

    }


}
