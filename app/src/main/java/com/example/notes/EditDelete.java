package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.FloatLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

public class EditDelete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete);
        Intent intent= getIntent();
        String id=intent.getStringExtra("id");
        String title=intent.getStringExtra("title");
        String des=intent.getStringExtra("des");
        EditText editText1=findViewById(R.id.TextTitle);
        EditText editText2=findViewById(R.id.TextDes);
        editText1.setText(title);
        editText2.setText(des);
        RequestQueue queue = Volley.newRequestQueue(this);
        FloatingActionButton deletebtn=findViewById(R.id.delete);
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("id",id);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
//                Send s=new Send(titles.getText().toString(),dess.getText().toString());

                String url="https://notesapi-production-a330.up.railway.app/delete";

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
                Toast t= Toast.makeText(getApplicationContext(),"Note Deleted",Toast.LENGTH_SHORT);
                t.show();
                Intent intent = new Intent(EditDelete.this, MainActivity.class);
                startActivity(intent);
                      }


        });
        FloatingActionButton updatebtn=findViewById(R.id.Update);
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("id",id);
                    jsonObject.put("title",editText1.getText());
                    jsonObject.put("des",editText2.getText());

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
//                Send s=new Send(titles.getText().toString(),dess.getText().toString());

                String url="https://notesapi-production-a330.up.railway.app/update";

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
                Toast t= Toast.makeText(getApplicationContext(),"Note Updated",Toast.LENGTH_SHORT);
                t.show();

                Intent intent = new Intent(EditDelete.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}