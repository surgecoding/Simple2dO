package com.example.databases;

public class ToDo {



    private String todo_id;
    private String time;

    private String todo_title;
    private String todo_status;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ToDo(String id, String todo_title, String todo_status, String time){
        this.todo_id=id;
this.todo_title = todo_title;
this.todo_status=todo_status;
this.time=time;
    }

    public String getTodo_id() {
        return todo_id;
    }

    public void setTodo_id(String todo_id) {
        this.todo_id = todo_id;
    }

    public String getTodo_title() {
        return todo_title;
    }

    public void setTodo_title(String todo_name) {
        this.todo_title = todo_name;
    }

    public String getTodo_status() {
        return todo_status;
    }

    public void setTodo_status(String todo_status) {
        this.todo_status = todo_status;
    }
}
