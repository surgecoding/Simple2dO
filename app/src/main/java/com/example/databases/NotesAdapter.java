package com.example.databases;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder>  {

Listener listener;

CheckListener checklistener;

    public static int getHolder_pos() {
        return holder_pos;
    }

    public static void setHolder_pos(int holder_pos) {
        NotesAdapter.holder_pos = holder_pos;
    }

    private static int holder_pos;

private ArrayList<ToDo>todo_list;


interface Listener{

    public void onNoteLongClick(int pos,View view,String id);
}

interface  CheckListener{
    void check(String complete,int id);
}




    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_list_item,parent,false);
        listener = (Listener)parent.getContext();
        checklistener = (CheckListener)parent.getContext();

        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NotesViewHolder holder, final int position) {
holder.note_title.setText(ToDo_Temp.getTodo_list().get(holder.getAdapterPosition()).getTodo_title());
        holder.datescreen.setText(ToDo_Temp.getTodo_list().get(holder.getAdapterPosition()).getTime());


if(ToDo_Temp.getTodo_list().get(holder.getAdapterPosition()).getTodo_status().equals("incomplete")){
    holder.checkBox.setChecked(false);
    holder.note_title.setPaintFlags(holder.note_title.getPaintFlags()&(~Paint.STRIKE_THRU_TEXT_FLAG));

}else if(ToDo_Temp.getTodo_list().get(holder.getAdapterPosition()).getTodo_status().equals("complete")){
    holder.checkBox.setChecked(true);
    holder.note_title.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);

}

holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
    @Override
    public boolean onLongClick(View v) {

        if(listener!=null && ToDo_Temp.getTodo_list().size()!=0||ToDo_Temp.getTodo_list()!=null){
            listener.onNoteLongClick(holder.getAdapterPosition(),holder.cardView,ToDo_Temp.getTodo_list().get(holder.getAdapterPosition()).getTodo_id());

            setHolder_pos(holder.getAdapterPosition());

        }

        return false;
    }
});

holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        setHolder_pos(holder.getAdapterPosition());

        if (holder.checkBox.isChecked()) {
            checklistener.check("incomplete",holder.getAdapterPosition());
            holder.note_title.setPaintFlags(holder.note_title.getPaintFlags()&(~Paint.STRIKE_THRU_TEXT_FLAG));

        }
        else{
            checklistener.check("complete",holder.getAdapterPosition());
            holder.note_title.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        }
    }
});

    }

    @Override
    public int getItemCount() {

        return ToDo_Temp.getTodo_list().size();
    }



   //*******************************************************************************
    public class NotesViewHolder extends RecyclerView.ViewHolder{
TextView note_title;
       TextView datescreen;

CheckBox checkBox;
CardView cardView;
        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            note_title = itemView.findViewById(R.id.note_title);
            datescreen = itemView.findViewById(R.id.datescreen);

            checkBox = itemView.findViewById(R.id.check_status);
cardView = itemView.findViewById(R.id.cardView);
        }
    }




}
