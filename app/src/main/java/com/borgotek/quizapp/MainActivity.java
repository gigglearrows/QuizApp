package com.borgotek.quizapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {
    ArrayList<String[]> quest;
    TextView questionView;
    TextView questionTitle;
    int questionNum;
    int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionView = (TextView) findViewById(R.id.question_view);
        questionTitle = (TextView) findViewById(R.id.question_title);

        questionNum = 0;
        points = 0;
        quest = parseJSON();
        Collections.shuffle(quest);
        Log.v("Quest", quest.get(5)[0]);

        displayQuestion();
    }

    public void guessTrue(View v) {
        if (Integer.valueOf(quest.get(questionNum)[1])==1) {
            points++;
            Toast.makeText(this, "CORRECT",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "WRONG",Toast.LENGTH_SHORT).show();
        if (questionNum < 7-1) {
            questionNum++;
            displayQuestion();
        }
        else {
            results();
        }
    }

    public void guessFalse(View v) {
        if (Integer.valueOf(quest.get(questionNum)[1])==0) {
            points++;
            Toast.makeText(this, "CORRECT",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "WRONG",Toast.LENGTH_SHORT).show();
        if (questionNum < 7-1) {
            questionNum++;
            displayQuestion();
        }
        else {
            results();
        }
    }

    public void displayQuestion() {
        questionView.setText(quest.get(questionNum)[0]);
        questionTitle.setText(getString(R.string.question, questionNum+1));
    }

    public void results() {
        AlertDialog.Builder winDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        winDialogBuilder.setCancelable(false)
                .setTitle(getString(R.string.resultsHeader))
                .setMessage("You got " + points + "/" + 7 + " points")
                .setPositiveButton("Quit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Try again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        reset();
                    }
                });
        AlertDialog winDialog = winDialogBuilder.create();
        winDialog.show();
    }

    private void reset() {
        questionNum = 0;
        points = 0;
        Collections.shuffle(quest);

        displayQuestion();
    }

    public ArrayList<String[]> parseJSON() {
        //Get Data From Text Resource File Contains Json Data.
        InputStream inputStream = getResources().openRawResource(R.raw.questions_json);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int ctr;
        try {
            ctr = inputStream.read();
            while (ctr != -1) {
                byteArrayOutputStream.write(ctr);
                ctr = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // Parse the data into JSONobject to get original data in form of json.
            JSONObject jObject = new JSONObject(
                    byteArrayOutputStream.toString());
            JSONArray jArray = jObject.getJSONArray("questions");
            String q;
            String a;
            ArrayList<String[]> qdata = new ArrayList<String[]>();
            for (int i = 0; i < jArray.length(); i++) {
                q = jArray.getJSONObject(i).getString("q");
                a = jArray.getJSONObject(i).getString("a");
                qdata.add(new String[] { q, a });
            }
            return qdata;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

