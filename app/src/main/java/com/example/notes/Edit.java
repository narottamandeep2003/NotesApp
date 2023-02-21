package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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

public class Edit extends AppCompatActivity {

    class  Send{
        String title,des;
        public Send(String title,String des){
            this.title=title;
            this.des=des;
        }



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ImageView imageView=findViewById(R.id.Save);
        EditText titles=findViewById(R.id.editTextTitle);
        EditText dess=findViewById(R.id.editTextDes);
        RequestQueue queue = Volley.newRequestQueue(this);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("title",titles.getText().toString());
                    jsonObject.put("des",dess.getText().toString());
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
//                Send s=new Send(titles.getText().toString(),dess.getText().toString());

                String url="https://notesapi-production-a330.up.railway.app/add";

                JsonObjectRequest req = new JsonObjectRequest(url, jsonObject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.e("L", "done: "+response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(req);
                Toast t= Toast.makeText(getApplicationContext(),"Note added",Toast.LENGTH_SHORT);
                t.show();
                Intent intent = new Intent(Edit.this, MainActivity.class);
                startActivity(intent);


            }
        });
    }
}