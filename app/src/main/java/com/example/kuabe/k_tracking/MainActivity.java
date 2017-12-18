package com.example.kuabe.k_tracking;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(this);
        // display time in 'date# textView
        int currDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int currMonth = Calendar.getInstance().get(Calendar.MONTH) + 1; // count starts at 0
        int currYear = Calendar.getInstance().get(Calendar.YEAR);

        TextView tvDate = findViewById(R.id.date);
        String currDateStr = currDay + "." + currMonth + "." + currYear;
        tvDate.setText(currDateStr);

        // set cursor in etUsedData in front of unit
        EditText etUsedData =  findViewById(R.id.usedData);
        etUsedData.setSelection( 1 );
        //TODO: open keyboard on opening
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        EditText etUsedData =  findViewById(R.id.usedData);
        TextView tvExpectedData = findViewById(R.id.expectedData);
        TextView tvResultingDataPDay = findViewById(R.id.resDataPDay);
        TextView tvDailyAverage = findViewById(R.id.dailyAverage);

        // Get data from edit text
        String inputUsedData = etUsedData.getText().toString();
        // calc average data
        double dailyAverage = DataCalculator.calcAvgData( inputUsedData );
        // calc expected Data to be used
        double expectedData = DataCalculator.calcExpectedData( inputUsedData );
        // calc remaining Data per day
        int dataLimit = getResources().getInteger(R.integer.DataLimit);
        double remDataPD = DataCalculator.calcRemainingDataPerDay( inputUsedData, dataLimit);


        @SuppressLint("DefaultLocale") String exDatStr = String.format("%.2f", expectedData);

        @SuppressLint("DefaultLocale") String remDataPDayStr = String.format("%.2f", remDataPD);

        @SuppressLint("DefaultLocale") String strDailyAverage = String.format("%.2f", dailyAverage);

        tvExpectedData.setText( getResources().getText(R.string.expectedData) + exDatStr + getResources().getText(R.string.uniMB) );
        tvResultingDataPDay.setText( getResources().getText(R.string.resData) + remDataPDayStr + getResources().getText(R.string.uniMB));
        tvDailyAverage.setText( getResources().getText(R.string.dailyAvg) + strDailyAverage + getResources().getText(R.string.uniMB));

    }
}