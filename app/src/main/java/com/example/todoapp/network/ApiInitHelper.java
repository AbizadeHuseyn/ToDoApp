package com.example.todoapp.network;

import com.example.todoapp.network.service.ToDoService;
import com.example.todoapp.utils.Constants;
import com.squareup.moshi.Moshi;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static com.example.todoapp.utils.Constants.BASE_URL;

public class ApiInitHelper {

    private static ApiInitHelper instance;

    private Retrofit retrofit;

    private ToDoService toDoService;

    private ApiInitHelper() {
        toDoService = getClient().create(ToDoService.class);
    }

    private Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(
                            MoshiConverterFactory.create(
                                    new Moshi.Builder().build()
                            )
                    )
                    .baseUrl(Constants.BASE_URL)
                    .build();
        }
        return retrofit;
    }

    public static ApiInitHelper getInstance() {
        if (instance == null) {
            instance = new ApiInitHelper();
        }
        return instance;
    }

    public ToDoService getToDoService() {
        return toDoService;
    }

}
