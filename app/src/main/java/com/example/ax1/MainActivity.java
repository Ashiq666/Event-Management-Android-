package com.example.ax1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button create_new;
    ListView listView;

    // Reference objects for handling event lists
    private ArrayList<Event> events = new ArrayList<Event>();
    private CustomEventAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        create_new = (Button) findViewById(R.id.createNewBtn);
        listView = findViewById(R.id.listViewEvents);

        create_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, new_event.class);
                startActivity(intent);
            }
        });
        LoadData();
    }

    private void LoadData() {
        events.clear();
        KeyValueDB db = new KeyValueDB(this);
        Cursor c = db.getAllKeyValues();

        while (c.moveToNext()){
            String key = c.getString(0);
            String value = c.getString(1);
            String [] values =value.split("--");

            String name = values[0];
            String place = values[1];
            String capacity = values[2];
            String budget = values[3];
            String phone = values[4];
            String email = values[5];
            String description = values[6];
            String dateTime = values[7];
            //String type = values[8];

            Event e = new Event(key, name, place, capacity, budget, email, phone, description, dateTime);
            events.add(e);
        }
        db.close();
        adapter = new CustomEventAdapter(this, events);
        listView.setAdapter(adapter);




    }
}