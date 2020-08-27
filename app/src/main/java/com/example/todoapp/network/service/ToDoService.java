package com.example.todoapp.network.service;

import com.example.todoapp.model.entity.ToDo;
import com.example.todoapp.model.pojo.ToDoPOJO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ToDoService {
    @GET("/all")
    Call<List<ToDoPOJO>> getAllToDos();

    @POST("/add")
    Call<ToDo> addToDo(@Body ToDoPOJO toDoPOJO);
}
