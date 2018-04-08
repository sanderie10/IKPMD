package com.example.opdracht2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class notitiesActivity extends AppCompatActivity{

    String items[] = new String [] {"IARCH", "IIBPM", "IHBO", "IOPR1", "IMUML", "IRDB", "IIBUI", "IPODM", "IPOMEDT", "INET", "IWDR", "IOPR2", "IFIT", "IPOFIT", "IPOSE", "IPROV", "ICOMMP", "ISLP", "IIPFIT"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notities);

        ListView listView =(ListView) findViewById(R.id.ListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(notitiesActivity.this,items[position],Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), dialogActivity.class);
                intent.putExtra("detail", items[position]);
                startActivity(intent);

            }
        });
    }
}

