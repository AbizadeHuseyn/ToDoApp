package com.example.todoapp.ui.fragments.todos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.model.entity.ToDo;
import com.example.todoapp.network.ApiInitHelper;
import com.example.todoapp.repository.ToDoRepository;
import com.example.todoapp.utils.lib.GetResponseCallBack;
import com.google.android.material.button.MaterialButton;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ToDosFragment extends Fragment {

    @BindView(R.id.recycler_view_todos)
    RecyclerView recyclerViewToDos;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.button_try_again)
    MaterialButton buttonTryAgain;

    private NavController navController;
    private ToDoRepository toDoRepository;
    private ToDoListAdapter toDoListAdapter;
    private GetResponseCallBack callBack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todos, container, false);
        ButterKnife.bind(this, view);

        navController = NavHostFragment.findNavController(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initialization
        toDoRepository = new ToDoRepository(ApiInitHelper.getInstance().getToDoService());

        //configurations
        configureRecyclerView();
        configureCallBack();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setObserves();
    }


    @Override
    public void onStart() {
        super.onStart();
        fetchToDos(callBack);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        toDoRepository.getListOfToDos().deleteObservers();
    }

    private void configureCallBack() {
        callBack = new GetResponseCallBack() {
            @Override
            public void handleTheResponseOfFailedGet() {
                progressBar.setVisibility(View.INVISIBLE);
                buttonTryAgain.setVisibility(View.VISIBLE);

                Toast.makeText(getContext(), "Something went wrong, try again!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleTheResponseOfSuccessfulGet() {
                progressBar.setVisibility(View.INVISIBLE);
            }
        };
    }

    private void fetchToDos(GetResponseCallBack callBack) {
        toDoRepository.getAllToDos(callBack);
    }

    private void configureRecyclerView() {
        toDoListAdapter = new ToDoListAdapter(new ToDosCallBack());
        recyclerViewToDos.setAdapter(toDoListAdapter);
    }

    private void setObserves() {
        toDoRepository.getListOfToDos().addObserver((observable, o) ->
                toDoListAdapter.submitList((List<ToDo>) o));
    }

    @OnClick(R.id.fab_share)
    void onFABClick() {
        navController.navigate(ToDosFragmentDirections.actionToDosFragmentToShareToDoFragment());
    }

    @OnClick(R.id.button_try_again)
    void onRetryClicked() {
        progressBar.setVisibility(View.VISIBLE);
        buttonTryAgain.setVisibility(View.INVISIBLE);
        fetchToDos(callBack);
    }

}
