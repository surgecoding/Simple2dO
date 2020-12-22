package com.example.databases;

import java.util.ArrayList;

public class ToDo_Temp {


    public static String getCurrent_todo_id() {
        return current_todo_id;
    }

    public static void setCurrent_todo_id(String current_todo_id) {
        ToDo_Temp.current_todo_id = current_todo_id;
    }

    private  static String current_todo_id;

    private static ArrayList<ToDo>todo_list = new ArrayList<>();

    public static ArrayList<ToDo> getTodo_list() {
        return todo_list;
    }

    public static void setTodo_list(ArrayList<ToDo> todo_list) {
        ToDo_Temp.todo_list = todo_list;
    }
}
