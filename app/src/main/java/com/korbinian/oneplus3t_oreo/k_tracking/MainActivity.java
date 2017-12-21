package com.korbinian.oneplus3t_oreo.k_tracking;

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
        EditText etUsedData = findViewById(R.id.usedData);
        TextView tvExpectedData = findViewById(R.id.expectedData);
        TextView tvResultingDataPDay = findViewById(R.id.resDataPDay);
        TextView tvDailyAverage = findViewById(R.id.dailyAverage);
        TextView tvNominalUsedData = findViewById(R.id.nominalUsed);

        // Get data from edit text
        String inputUsedData = etUsedData.getText().toString(); //TODO: add check for zero etc.
        double usedData = Double.parseDouble(inputUsedData.substring(0, inputUsedData.length() -2 ));
        if (usedData == 0 || usedData < 0){
            etUsedData.setText(getResources().getText(R.string.invalid));
        } else {

            // calc average data
            double dailyAverage = DataCalculator.calcAvgData(usedData);
            // calc expected Data to be used
            double expectedData = DataCalculator.calcExpectedData(usedData);
            // calc remaining Data per day
            int dataLimit = getResources().getInteger(R.integer.DataLimit);
            double remDataPD = DataCalculator.calcRemainingDataPerDay(usedData, dataLimit);
            // calc nominal Used Data if average usage per day
            double nominalUsedData = DataCalculator.calcNominalUsedData(dataLimit);


            @SuppressLint("DefaultLocale") String exDatStr = String.format("%.2f", expectedData);

            @SuppressLint("DefaultLocale") String remDataPDayStr = String.format("%.2f", remDataPD);

            @SuppressLint("DefaultLocale") String strDailyAverage = String.format("%.2f", dailyAverage);

            @SuppressLint("DefaultLocale") String strNominalUsed = String.format("%.2f", nominalUsedData);

            tvExpectedData.setText(getResources().getText(R.string.expectedData) + exDatStr + getResources().getText(R.string.uniMB));
            tvResultingDataPDay.setText(getResources().getText(R.string.resData) + remDataPDayStr + getResources().getText(R.string.uniMB));
            tvDailyAverage.setText(getResources().getText(R.string.dailyAvg) + strDailyAverage + getResources().getText(R.string.uniMB));
            tvNominalUsedData.setText(getResources().getText(R.string.nominalUsed) + strNominalUsed + getResources().getText(R.string.uniMB));
        }
    }
}