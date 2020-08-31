package com.example.todoapp.ui.fragments.sharetodo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.todoapp.R;
import com.example.todoapp.model.pojo.ToDoPOJO;
import com.example.todoapp.network.ApiInitHelper;
import com.example.todoapp.repository.ToDoRepository;
import com.example.todoapp.utils.lib.PostResponseCallBack;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShareToDoFragment extends Fragment {

    private ToDoRepository repository;
    private NavController navController;


    @BindView(R.id.text_input_layout_title)
    TextInputLayout textInputLayoutTitle;
    @BindView(R.id.text_input_layout_body)
    TextInputLayout textInputLayoutBody;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share_todo, container, false);
        ButterKnife.bind(this, view);

        navController = NavHostFragment.findNavController(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //initialization
        repository = new ToDoRepository(ApiInitHelper.getInstance().getToDoService());


    }

    @OnClick(R.id.button_share_button)
    void onShareButtonClick() {
        if (areInputsValid()) {
            ToDoPOJO toDoPOJOItem = creatingToDo();
            repository.addToDo(toDoPOJOItem, new PostResponseCallBack() {
                @Override
                public void handleTheResponseOfSuccessfulPost() {
                    Toast.makeText(getContext(), "Task is added successfully!", Toast.LENGTH_SHORT).show();
                    navController.popBackStack();

                }

                @Override
                public void handleTheResponseOfFailedPost() {
                    Toast.makeText(getContext(), "Couldn't add the task, try again!", Toast.LENGTH_SHORT).show();
                }

            });

        } else {
            Toast.makeText(getContext(), "Title or body is empty!", Toast.LENGTH_SHORT).show();
        }
    }


    private ToDoPOJO creatingToDo() {
        String uuid = java.util.UUID.randomUUID().toString();
        String title = Objects.requireNonNull(textInputLayoutTitle.getEditText()).getText().toString();
        String content = Objects.requireNonNull(textInputLayoutBody.getEditText()).getText().toString();

        ToDoPOJO newToDo = new ToDoPOJO(uuid, title, content);
        return newToDo;
    }

    private boolean areInputsValid() {
        return  !Objects.requireNonNull(textInputLayoutTitle.getEditText()).getText().toString().isEmpty()
                &&
                !Objects.requireNonNull(textInputLayoutBody.getEditText()).getText().toString().isEmpty();
    }


}
