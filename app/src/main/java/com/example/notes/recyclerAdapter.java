package com.example.notes;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.ViewHolder>{
    Context context;
    ArrayList<NotesModel> notesModels;
    recyclerAdapter(Context context, ArrayList<NotesModel> notesModels){
this.context=context;
this.notesModels=notesModels;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.row,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.title.setText(notesModels.get(position).title);
        holder.des.setText(notesModels.get(position).des);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(view.getContext(),EditDelete.class);
                intent.putExtra("title",notesModels.get(position).title);
                intent.putExtra("des",notesModels.get(position).des);
                intent.putExtra("id",notesModels.get(position).id);
                view.getContext().startActivity(intent);




            }


        });
    }

    @Override
    public int getItemCount() {
        return notesModels.size();
    }

    public  class  ViewHolder extends  RecyclerView.ViewHolder{
        TextView title;
        TextView des;
        CardView item;
        public ViewHolder(@NonNull  View a){
            super(a);
            title=a.findViewById(R.id.textView);
            des=a.findViewById(R.id.textView2);
            item=a.findViewById(R.id.row);

        }

    }

}
