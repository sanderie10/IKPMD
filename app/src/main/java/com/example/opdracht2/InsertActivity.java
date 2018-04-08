package com.example.opdracht2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
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

import java.util.HashMap;
import java.util.Map;

public class InsertActivity extends AppCompatActivity {

    Button insertButton;
    EditText vakField;
    EditText cijferField;
    RequestQueue requestQueue;
    String insertUrl = "http://145.101.85.219/insert.php";
    TextView textField;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        insertButton = (Button) findViewById(R.id.sendButton);
        vakField = (EditText) findViewById(R.id.vakField);
        vakField.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        cijferField = (EditText) findViewById(R.id.cijferField);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        textField = (TextView) findViewById(R.id.textView);
        textField.setText("IARCH  " + "IIBPM  " + "IHBO  " + "IOPR1  " + "IMUML  "  + "\n" + "IRDB  " + "IIBUI  " + "IPODM  " + "IPOMEDT  " + "INET  " + "\n" + "IWDR  " + "IOPR2  " + "IFIT  " + "IPOFIT  " + "IPOSE  " + "\n" + "IPROV  " + "ICOMMP  " + "ISLP  " + "IIPFIT  ");

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
                        params.put("Vak",vakField.getText().toString());
                        params.put("Cijfer",cijferField.getText().toString());
                        return params;
                    }
                }; requestQueue.add(stringRequest);
                finish();
            }
        });


    }

}

