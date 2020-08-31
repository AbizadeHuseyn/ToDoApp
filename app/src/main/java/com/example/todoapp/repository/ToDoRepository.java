package com.example.todoapp.repository;

import com.example.todoapp.model.entity.ToDo;
import com.example.todoapp.model.pojo.ToDoPOJO;
import com.example.todoapp.network.service.ToDoService;
import com.example.todoapp.utils.Utils;
import com.example.todoapp.utils.lib.GetResponseCallBack;
import com.example.todoapp.utils.lib.Observable;
import com.example.todoapp.utils.lib.PostResponseCallBack;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ToDoRepository {

    private ToDoService toDoService;

    private Observable<List<ToDo>> listOfToDos = new Observable<>();

    public ToDoRepository(ToDoService toDoService) {
        this.toDoService = toDoService;

    }

    public Observable<List<ToDo>> getListOfToDos() {
        return listOfToDos;
    }

    public void getAllToDos(GetResponseCallBack callBack) {
        getAllToDosFromNetwork(callBack);
    }

    public void addToDo(ToDoPOJO toDoPOJO, PostResponseCallBack callBack) {
        addToDoToTheNetwork(toDoPOJO, callBack);
    }

    private void getAllToDosFromNetwork(GetResponseCallBack callBack) {
        toDoService.getAllToDos().enqueue(new Callback<List<ToDoPOJO>>() {
            @Override
            public void onResponse(Call<List<ToDoPOJO>> call, Response<List<ToDoPOJO>> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        List<ToDo> data = Utils.ToDoPojoToToDoObject(response.body());
                        listOfToDos.setValue(data);

                        callBack.handleTheResponseOfSuccessfulGet();

                    }
                }
            }

            @Override
            public void onFailure(Call<List<ToDoPOJO>> call, Throwable t) {

                callBack.handleTheResponseOfFailedGet();

            }
        });

    }

    private void addToDoToTheNetwork(ToDoPOJO toDoPOJO, PostResponseCallBack callBack) {

        Call<ToDo> call = toDoService.addToDo(toDoPOJO);
        call.enqueue(new Callback<ToDo>() {
            @Override
            public void onResponse(Call<ToDo> call, Response<ToDo> response) {
                callBack.handleTheResponseOfSuccessfulPost();
            }

            @Override
            public void onFailure(Call<ToDo> call, Throwable t) {
                callBack.handleTheResponseOfFailedPost();
            }
        });
    }


}
