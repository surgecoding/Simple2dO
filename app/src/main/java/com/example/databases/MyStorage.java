package com.example.databases;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDoneException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyStorage extends SQLiteOpenHelper {

 private static   String db_name = "storage.db";

    public static ArrayList getNote_statuses() {
        return note_statuses;
    }

    private static ArrayList note_statuses;

  private static  int db_version = 1;

  private String table_name = "notes_table";
  private String    id_column="id_column";
  private String name_column = "name";
    private String date_column = "date_column";

    private String note_status = "note_status";
  private  String complete = "complete";

    public MyStorage(Context context) {
        super(context, db_name, null, db_version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL(" create table "+ table_name +"(id_column INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,note_status TEXT,date_column TEXT,note_time DATETIME DEFAULT CURRENT_TIMESTAMP )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {db.execSQL("drop table if exists "+ table_name);
        onCreate
(db);
    }

    public boolean createNote(String name,String note_status,String date){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name",name);
        cv.put("note_status",note_status);
        cv.put("date_column",date);

        db.insert("notes_table",null,cv);
    return true;

    }

     void  Setnotecomplete(int id){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("note_status","complete");
        db.update(table_name , cv,"id_column = "+id,null);

    }
    void  Setnoteincomplete(int id){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("note_status","incomplete");
        db.update(table_name , cv,"id_column = "+id,null);

    }

    public ArrayList getAllNotes(){
        ArrayList allnotes = new ArrayList();
        ArrayList <ToDo>todolist = new ArrayList();

        note_statuses = new ArrayList();

        SQLiteDatabase db = getReadableDatabase();
        Cursor rs = db.rawQuery("select * from " +table_name + " ORDER BY note_time DESC ",null);
rs.moveToFirst();
while(!rs.isAfterLast()){
   String  id =rs.getString(rs.getColumnIndex(id_column));

    String note =rs.getString(rs.getColumnIndex(name_column));

        String status = rs.getString(rs.getColumnIndex(note_status));
    String time =rs.getString(rs.getColumnIndex(date_column));

    todolist.add(new ToDo(id,note,status,time));


    allnotes.add(note);
    rs.moveToNext();
}
        ToDo_Temp.setTodo_list(todolist);

rs.close();
        return allnotes;
    }


    public void delete(String id){
        SQLiteDatabase db = getWritableDatabase();
try {
    db.delete(table_name,"id_column = "+id,null);
}catch(Exception f){
    Log.i("&&&&&&&&&&&&&&&&",f.toString());
}
    }


    public void deleteAll(){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from " + table_name);
    }



    }

