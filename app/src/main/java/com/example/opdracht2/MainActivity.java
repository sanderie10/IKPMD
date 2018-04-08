package com.example.opdracht2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.graphics.Color;
import android.util.Log;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private PieChart mChart;
    public static final int MAX_ECTS = 61;
    public static int currentEcts = 0;
    RequestQueue requestQueue;
    String getUrl = "http://145.101.85.219/show.php";
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();
        CharSequence text = "Welkom gebruiker!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        mChart = (PieChart) findViewById(R.id.chart);
        mChart.setDescription("Voortgang");
        mChart.setTouchEnabled(false);
        mChart.setDrawSliceText(true);
        mChart.getLegend().setEnabled(false);
        mChart.setTransparentCircleColor(Color.rgb(130, 130, 130));
        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        setData(0);
    }

    public void  cijferlijst (View v) {
        Intent i = new Intent(this, cijferlijstActivity.class);
        startActivity(i);
    }

    public void  notities (View v) {
        Intent i = new Intent(this, notitiesActivity.class);
        startActivity(i);
    }

    private void setData(int aantal) {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        int currentEcts = settings.getInt("value", 0);


        ArrayList<Entry> yValues = new ArrayList<>();
        ArrayList<String> xValues = new ArrayList<>();

        yValues.add(new Entry(currentEcts, 0));
        xValues.add("Behaalde ECTS");

        yValues.add(new Entry(61 - currentEcts, 1));
        xValues.add("Resterende ECTS");

        ArrayList<Integer> colors = new ArrayList<>();
        if (currentEcts <51)
        {
            colors.add(Color.rgb(244,81,30));
        }
        else
        {
            colors.add(Color.rgb(67,160,71));
        }
        colors.add(Color.rgb(255,0,0));

        PieDataSet dataSet = new PieDataSet(yValues, "ECTS");
        dataSet.setColors(colors);

        PieData data = new PieData(xValues, dataSet);
        mChart.setData(data); // bind dataset aan chart.
        mChart.invalidate();  // Aanroepen van een redraw
        Log.d("aantal =", ""+currentEcts);

                    }

    }

