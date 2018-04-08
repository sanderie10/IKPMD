package com.example.opdracht2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class cijferlijstActivity extends AppCompatActivity {

    TextView tv;
    Button getButton,insertButton;

    RequestQueue requestQueue;
    String getUrl = "http://145.101.85.219/show.php";
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cijferlijst);

        tv = (TextView) findViewById(R.id.textView1);
        getButton = (Button) findViewById(R.id.showButton);
        insertButton = (Button) findViewById(R.id.insertButton);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(cijferlijstActivity.this,InsertActivity.class));
            }
        });

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(cijferlijstActivity.this,InsertActivity.class));
            }
        });

        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, getUrl, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            JSONArray cijfers = response.getJSONArray("cijfers");
                            tv.setText("");

                            for(int i =0; i < cijfers.length(); i ++)
                            {
                                JSONObject user = cijfers.getJSONObject(i);

                                double IARCH = user.getDouble("IARCH");
                                double IIBPM = user.getDouble("IIBPM");
                                double IHBO = user.getDouble("IHBO");
                                double IOPR1 = user.getDouble("IOPR1");
                                double IMUML = user.getDouble("IMUML");
                                double IRDB = user.getDouble("IRDB");
                                double IIBUI = user.getDouble("IIBUI");
                                double IPODM = user.getDouble("IPODM");
                                double IPOMEDT = user.getDouble("IPOMEDT");
                                double INET = user.getDouble("INET");
                                double IWDR = user.getDouble("IWDR");
                                double IOPR2 = user.getDouble("IOPR2");
                                double IFIT = user.getDouble("IFIT");
                                double IPOFIT = user.getDouble("IPOFIT");
                                double IPOSE = user.getDouble("IPOSE");
                                double IPROV = user.getDouble("IPROV");
                                double ICOMMP = user.getDouble("ICOMMP");
                                double ISLP = user.getDouble("ISLP");
                                double IIPFIT= user.getDouble("IIPFIT");

                                int currentEcts = 0;

                                if (IARCH >= 5.5) {
                                    currentEcts = currentEcts + 3;
                                }
                                if (IIBPM >= 5.5) {
                                    currentEcts = currentEcts + 3;
                                }
                                if (IHBO >= 5.5) {
                                    currentEcts = currentEcts + 3;
                                }
                                if (IOPR1 >= 5.5) {
                                    currentEcts = currentEcts + 4;
                                }
                                if (IMUML >= 5.5) {
                                    currentEcts = currentEcts + 3;
                                }
                                if (IRDB >= 5.5) {
                                    currentEcts = currentEcts + 3;
                                }
                                if (IIBUI >= 5.5) {
                                    currentEcts = currentEcts + 3;
                                }
                                if (IPODM >= 5.5) {
                                    currentEcts = currentEcts + 2;
                                }
                                if (IPOMEDT >= 5.5) {
                                    currentEcts = currentEcts + 2;
                                }
                                if (INET >= 5.5) {
                                    currentEcts = currentEcts + 3;
                                }
                                if (IWDR >= 5.5) {
                                    currentEcts = currentEcts + 3;
                                }
                                if (IOPR2 >= 5.5) {
                                    currentEcts = currentEcts + 4;
                                }
                                if (IFIT >= 5.5) {
                                    currentEcts = currentEcts + 3;
                                }
                                if (IPOFIT >= 5.5) {
                                    currentEcts = currentEcts + 2;
                                }
                                if (IPOSE >= 5.5) {
                                    currentEcts = currentEcts + 2;
                                }
                                if (IPROV >= 5.5) {
                                    currentEcts = currentEcts + 3;
                                }
                                if (ICOMMP >= 5.5) {
                                    currentEcts = currentEcts + 3;
                                }
                                if (ISLP >= 5.5) {
                                    currentEcts = currentEcts + 1;
                                }
                                if (IIPFIT >= 5.5) {
                                    currentEcts = currentEcts + 10;
                                }

                                tv.append("IARCH: " + IARCH + "\n" + "IIBPM: " + IIBPM + "\n" + "IHBO: " + IHBO + "\n" + "IOPR1: " + IOPR1 + "\n" + "IMUML: " + IMUML + "\n" + "IRDB: " + IRDB + "\n" + "IIBUI: " + IIBUI + "\n" + "IPODM: " + IPODM + "\n" + "IPOMEDT: " + IPOMEDT + "\n" + "INET: " + INET + "\n" + "IWDR: " + IWDR + "\n" + "IOPR2: " + IOPR2 + "\n" + "IFIT: " + IFIT + "\n" + "IPOFIT: " + IPOFIT + "\n" + "IPOSE: " + IPOSE + "\n" + "IPROV: " + IPROV + "\n" + "ICOMMP: " + ICOMMP + "\n" + "ISLP: " + ISLP + "\n" + "IIPFIT: " + IIPFIT + "\n" + "Study Points: " + currentEcts + "\n");

                                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                                SharedPreferences.Editor editor = settings.edit();
                                editor.putInt("value", currentEcts);
                                editor.commit();

                            }
                        }catch(Exception e)
                        {

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tv.setText("Error: \n\n" + error.toString());
                    }
                }); requestQueue.add(jsonObjectRequest);
            }
        });
    }
}
