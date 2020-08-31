package com.example.todoapp.ui.fragments.todos;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.model.entity.ToDo;

public class ToDoViewHolder extends RecyclerView.ViewHolder {
    public ToDoViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(ToDo toDo) {
        ((TextView) itemView.findViewById(R.id.text_view_title_in_item)).setText(toDo.getTitle());
        ((TextView) itemView.findViewById(R.id.text_view_context_in_item)).setText(toDo.getContent());
    }
}
