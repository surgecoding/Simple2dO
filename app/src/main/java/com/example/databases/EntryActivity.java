package com.example.databases;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class EntryActivity extends AppCompatActivity implements NotesAdapter.Listener , NotesAdapter.CheckListener {
MyStorage storage;
NotesAdapter notesAdapter;
String todo_to_edit;
static int pos;
    ArrayList list;

    Cursor string;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
               super.onCreate(savedInstanceState);
               setContentView(R.layout.activity_entry);

         recyclerView = findViewById(R.id.recyclerview);

        storage= new MyStorage(this);


try {
    list = storage.getAllNotes();
  //  System.out.print(ToDo_Temp.getTodo_list().get(5));

}catch(Exception error){
    Toast.makeText(this,error.toString(),Toast.LENGTH_SHORT).show();
}



        FloatingActionButton go_to_create_button =findViewById(R.id.go_to_create_button);

        go_to_create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(getApplicationContext(),CreateNoteActivity.class);
                startActivity(go);
                finish();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        print();
    }

    void print(){
/*        for(int i=0;i<=list.size()-1;i++){
            System.out.print(list.get(i));
            Log.i("**********************",list.get(i).toString());
        }       */

        notesAdapter = new NotesAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(notesAdapter);

}

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void onNoteLongClick(int pos,View view,String current) {
        Toast.makeText(this,"cLICKED "+NotesAdapter.getHolder_pos()+" Id "+current,Toast.LENGTH_LONG).show();

  todo_to_edit=current;
  this.pos =pos;

   registerForContextMenu(view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

                super.onCreateOptionsMenu(menu);

                MenuInflater menuinfl = getMenuInflater();

                menuinfl.inflate(R.menu.menu,menu);

              return true  ;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId()==R.id.deleteall){

            AlertDialog.Builder myalert = new AlertDialog.Builder(this);
            myalert.setMessage("Do you really want to delete all the todos ?");
            myalert.setTitle("Delete all todos");

            myalert.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            myalert.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(EntryActivity.this,"Deleting",Toast.LENGTH_SHORT).show();
                    storage.deleteAll();
                    ToDo_Temp.getTodo_list().clear();
                    notesAdapter.notifyDataSetChanged();

                }
            });


myalert.show();
        }
        else if(item.getItemId()==R.id.info){
            Toast.makeText(this,"Simple todo app v0.1",Toast.LENGTH_LONG).show();
        }

            return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.todo_context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        super.onContextItemSelected(item);
        if(item.getItemId() ==R.id.deletetodo)
        {
          storage.delete(todo_to_edit);
        try {
            ToDo_Temp.getTodo_list().remove(NotesAdapter.getHolder_pos());
            notesAdapter.notifyDataSetChanged();

        }catch (Exception f){
            Log.i("%%%%%%%%%%",f.toString());
        }



            Toast.makeText(this,"Deleted",Toast.LENGTH_LONG).show();
        }
        return true;
    }

    @Override
    public void check(String complete,int pos) {
        if(complete=="complete"){
        storage.Setnoteincomplete(Integer.valueOf(ToDo_Temp.getTodo_list().get(NotesAdapter.getHolder_pos()).getTodo_id()));
            ToDo_Temp.getTodo_list().get(NotesAdapter.getHolder_pos()).setTodo_status("incomplete");

            if(!recyclerView.isComputingLayout()&&recyclerView.getScrollState()==RecyclerView.SCROLL_STATE_IDLE) {
                notesAdapter.notifyDataSetChanged();
            }

        }

        else if(complete=="incomplete"){
            storage.Setnotecomplete(Integer.valueOf(ToDo_Temp.getTodo_list().get(NotesAdapter.getHolder_pos()).getTodo_id()));
           ToDo_Temp.getTodo_list().get(NotesAdapter.getHolder_pos()).setTodo_status("complete");

           if(!recyclerView.isComputingLayout()&&recyclerView.getScrollState()==RecyclerView.SCROLL_STATE_IDLE) {
               notesAdapter.notifyDataSetChanged();
           }
    }
    }
}