package com.borgotek.quizapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

public class StuffActivity extends AppCompatActivity {
    RadioButton correctRadio1;
    CheckBox longislandSprite;
    CheckBox longislandCola;
    CheckBox longislandTequila;
    CheckBox longislandOrange;
    CheckBox longislandPeach;
    CheckBox longislandRum;
    CheckBox longislandTea;
    RadioButton correctRadio2;
    EditText editPython;
    RadioButton correctRadio3;
    RadioButton correctRadio4;
    RadioButton correctRadio5;
    RadioButton correctRadio6;

    RadioGroup rg1;
    RadioGroup rg2;
    RadioGroup rg3;
    RadioGroup rg4;
    RadioGroup rg5;
    RadioGroup rg6;
    ScrollView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stuff);

        // Get all the views
        rg1 = (RadioGroup) findViewById(R.id.rg1);
        rg2 = (RadioGroup) findViewById(R.id.rg2);
        rg3 = (RadioGroup) findViewById(R.id.rg3);
        rg4 = (RadioGroup) findViewById(R.id.rg4);
        rg5 = (RadioGroup) findViewById(R.id.rg5);
        rg6 = (RadioGroup) findViewById(R.id.rg6);

        correctRadio1 = (RadioButton) findViewById(R.id.rg1_oslo);

        longislandSprite = (CheckBox) findViewById(R.id.chk_sprite);
        longislandCola = (CheckBox) findViewById(R.id.chk_cocacola);
        longislandTequila = (CheckBox) findViewById(R.id.chk_tequila);
        longislandOrange = (CheckBox) findViewById(R.id.chk_orangeliq);
        longislandPeach = (CheckBox) findViewById(R.id.chk_peachliq);
        longislandRum = (CheckBox) findViewById(R.id.chk_rum);
        longislandTea = (CheckBox) findViewById(R.id.chk_icedtea);

        correctRadio2 = (RadioButton) findViewById(R.id.rg2_nine);
        editPython = (EditText) findViewById(R.id.python);

        correctRadio3 = (RadioButton) findViewById(R.id.rg3_mars);
        correctRadio4 = (RadioButton) findViewById(R.id.rg4_head);
        correctRadio5 = (RadioButton) findViewById(R.id.rg5_magnetic);
        correctRadio6 = (RadioButton) findViewById(R.id.rg6_turnip);
        sv = (ScrollView) findViewById(R.id.scrollview);

        //hide keyboard when clicking outside edittext
        editPython.setOnFocusChangeListener(
            new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(editPython.getWindowToken(), 0);
                }
            }
        );
        (findViewById(R.id.scrollview)).setOnTouchListener(
            new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    editPython.clearFocus();
                    return false;
                }
            }
        );


    }

    public void submitAnswers(View v) {
        int correctAnswerCount = 0;

        String answer = editPython.getText().toString();
        if (answer.equalsIgnoreCase("kaa")) correctAnswerCount++;

        if (correctRadio1.isChecked()) correctAnswerCount++;

        if (longislandSprite.isChecked()) correctAnswerCount--;
        if (longislandCola.isChecked()) correctAnswerCount++;
        if (longislandTequila.isChecked()) correctAnswerCount++;
        if (longislandOrange.isChecked()) correctAnswerCount++;
        if (longislandPeach.isChecked()) correctAnswerCount--;
        if (longislandRum.isChecked()) correctAnswerCount++;
        if (longislandTea.isChecked()) correctAnswerCount--;

        if (correctRadio2.isChecked()) correctAnswerCount++;

        if (correctRadio3.isChecked()) correctAnswerCount++;
        if (correctRadio4.isChecked()) correctAnswerCount++;
        if (correctRadio5.isChecked()) correctAnswerCount++;
        if (correctRadio6.isChecked()) correctAnswerCount++;

        Toast.makeText(this, "You have answered correctly on " + correctAnswerCount + "/11 questions", Toast.LENGTH_SHORT).show();
        reset();
    }

    private void reset() {
        rg1.clearCheck();
        rg2.clearCheck();
        rg3.clearCheck();
        rg4.clearCheck();
        rg5.clearCheck();
        rg6.clearCheck();
        editPython.getText().clear();
        longislandSprite.setChecked(false);
        longislandCola.setChecked(false);
        longislandTequila.setChecked(false);
        longislandOrange.setChecked(false);
        longislandPeach.setChecked(false);
        longislandRum.setChecked(false);
        longislandTea.setChecked(false);

        //scroll to top
        sv.fullScroll(ScrollView.FOCUS_UP);
    }

}