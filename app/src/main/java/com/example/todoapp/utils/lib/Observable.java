package com.example.todoapp.utils.lib;

public class Observable<T> extends java.util.Observable {

    private T value;

    public void setValue(T value){
        this.value = value;
        setChanged();
        notifyObservers(value);
    }

    public T getValue() {
        return value;
    }
}
