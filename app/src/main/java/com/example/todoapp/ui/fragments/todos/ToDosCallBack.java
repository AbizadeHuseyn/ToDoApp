package com.example.todoapp.ui.fragments.todos;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.todoapp.model.entity.ToDo;

public class ToDosCallBack extends DiffUtil.ItemCallback<ToDo> {
    @Override
    public boolean areItemsTheSame(@NonNull ToDo oldItem, @NonNull ToDo newItem) {
        return oldItem.getUuid().equals(newItem.getUuid());
    }

    @SuppressLint("DiffUtilEquals")
    @Override
    public boolean areContentsTheSame(@NonNull ToDo oldItem, @NonNull ToDo newItem) {
        return oldItem == newItem;
    }
}
