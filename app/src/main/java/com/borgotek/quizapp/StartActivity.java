package com.borgotek.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.list_mode_item, getResources().getStringArray(R.array.quizModes));

        ListView listView = (ListView) findViewById(R.id.quizModeList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch( position )
                {
                    case 0:
                        startActivity(new Intent(StartActivity.this, MainActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(StartActivity.this, StuffActivity.class));
                }
            }
        });
    }

}