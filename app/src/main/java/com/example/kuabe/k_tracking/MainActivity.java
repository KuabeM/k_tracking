package com.example.kuabe.k_tracking;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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


        // get current Day and Hour
        Calendar cal = Calendar.getInstance();
        int currMonth = cal.get( Calendar.MONTH);
        int currDay = cal.get(Calendar.DAY_OF_MONTH);
        int currHour = cal.get(Calendar.HOUR);

        // get input data volume
        String inputUsedData = etUsedData.getText().toString();
        double usedData = Double.parseDouble(inputUsedData.substring(0, inputUsedData.length() - 2));
        double expectedData;

        // do calculations for expected data
        expectedData = usedData / (currDay + currHour / 24) * cal.getActualMaximum( Calendar.DAY_OF_MONTH );
        @SuppressLint("DefaultLocale") String exDatStr = String.format("%.2f", expectedData);
        // calc resuming volume per day
        int dataLimit = getResources().getInteger(R.integer.DataLimit);
        double resDataPDay = (dataLimit - usedData) / ( cal.getActualMaximum( currMonth ) - currDay);
        @SuppressLint("DefaultLocale") String resDataPDayStr = String.format("%.2f", resDataPDay);
        // calc daily average so far
        double dailyAverage = usedData / currDay;
        @SuppressLint("DefaultLocale") String strDailyAverage = String.format("%.2f", dailyAverage);

        tvExpectedData.setText(  getResources().getText(R.string.expectedData) + exDatStr + getResources().getText(R.string.uniMB) );
        tvResultingDataPDay.setText( getResources().getText(R.string.resData) + resDataPDayStr + getResources().getText(R.string.uniMB));
        tvDailyAverage.setText( getResources().getText(R.string.dailyAvg) + strDailyAverage + getResources().getText(R.string.uniMB));


    }
}
