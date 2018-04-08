package com.example.opdracht2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class dialogActivity extends AppCompatActivity {

    Button insertButton;
    EditText vakField;
    TextView tv;
    String insertUrl = "http://145.101.85.219/insertnotes.php";
    String getUrl = "http://145.101.85.219/shownotes.php";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        Intent intent = getIntent();
        final String val = intent.getStringExtra("detail");

        tv = (TextView) findViewById(R.id.textView1);
        insertButton = (Button) findViewById(R.id.button1);
        vakField = (EditText) findViewById(R.id.editText1);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {

                tv.setText("");
                String test = response;
                int lengte2 = val.length();
                int lengte = test.length();
                int lengte3 = lengte - 4;
                test = response.substring(18 + lengte2 ,lengte3);
                tv.append(test);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Vak",val);
                return params;
            }
        }; requestQueue.add(stringRequest);



        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringRequest stringRequest = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Vak",val);
                        String Notes = vakField.getText().toString();
                        params.put("Notes", Notes);
                        return params;
                    }
                }; requestQueue.add(stringRequest);
                finish();
            }
        });
    }
}
