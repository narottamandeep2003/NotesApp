package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     public  class  noteM{
         String id;
          String title;
          String des;
          noteM(String id,String title,String des){
              this.id=id;
              this.title=title;
              this.des=des;
          }
          public  String d(){
              return  id;
          }



     }
//    private  RecyclerView recyclerView;
//    private Recycleviewadapter recycleviewadapter;

//    private ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        TextView textView =findViewById(R.id.texts);
        ArrayList<NotesModel> arrayList;
        arrayList=new ArrayList<>();
        RecyclerView recyclerView=findViewById(R.id.recycles);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerAdapter recyclerAdapter=new recyclerAdapter(this,arrayList);
//        String url = "https://www.google.com";
        String url="https://notesapi-production-a330.up.railway.app/get";
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url,null,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("l", "onResponse: "+response);

//                String s=response.toString();
                try {
                    for (int i=0;i<response.length();i++){
                        JSONObject j=response.getJSONObject(i);
                        NotesModel m=new NotesModel(j.getString("_id"),j.getString("title"),j.getString("des"));
                        arrayList.add(m);
                    }

                    Log.d("l", "onRes1ponsenn: "+arrayList);
                    recyclerView.setAdapter(recyclerAdapter);
//                    textView.setText(j.getString("title"));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("L", "onErrorResponse: ");
            }
        }
        );


        ImageView imageView=findViewById(R.id.imageView);
        imageView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, Edit.class);
                        startActivity(intent);

                    }
                }
        );
        queue.add(jsonArrayRequest);








    }
}