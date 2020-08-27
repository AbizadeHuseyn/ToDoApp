package com.example.todoapp.utils;

import com.example.todoapp.model.entity.ToDo;
import com.example.todoapp.model.pojo.ToDoPOJO;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<ToDo> ToDoPojoToToDoObject (List<ToDoPOJO> toDoPOJOS){
        List<ToDo> toDos = new ArrayList<>();
        for (ToDoPOJO itemPOJO: toDoPOJOS) {
            toDos.add(new ToDo(itemPOJO.getUuid(), itemPOJO.getTitle(), itemPOJO.getContent()));
        }
        return toDos;
    }
}
