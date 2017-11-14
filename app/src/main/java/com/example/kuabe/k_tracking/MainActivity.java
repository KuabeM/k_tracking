package com.example.kuabe.k_tracking;

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
        // display time in 'date# textview
        int currDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int currMonth = Calendar.getInstance().get(Calendar.MONTH) + 1; // count starts at 0
        int currYear = Calendar.getInstance().get(Calendar.YEAR);

        TextView tvDate = findViewById(R.id.date);
        String currDateStr = currDay + "." + currMonth + "." + currYear;
        tvDate.setText(currDateStr);
    }

    @Override
    public void onClick(View view) {
        EditText etUsedData =  findViewById(R.id.usedData);
        TextView tvExpectedData = findViewById(R.id.expectedData);

        // get current Day and Hour
        Calendar cal = Calendar.getInstance();
        int currDay = cal.get(Calendar.DAY_OF_MONTH);
        int currHour = cal.get(Calendar.HOUR);

        // get input data volume
        String inputUsedData = etUsedData.getText().toString();
        double usedData = Double.parseDouble(inputUsedData.substring(0, inputUsedData.length() - 2));
        double expectedData;

        expectedData = usedData / (currDay + currHour / 24) * 30;
        String exDatStr = String.format("%.2f", expectedData);

        tvExpectedData.setText(  getString(R.string.expectedData) + exDatStr + getString(R.string.uniMB));

        /*double res = 0;

        if( num == 0) {
            etNumerator.setText("Not Allowed");
        } else if ( denum == 0) {
            etDenumerator.setText("Not Alowed");
        } else {
            res = num/denum;
            etNumerator.setText(Double.toString(res));
            etDenumerator.setText(Integer.toString(0));
        }*/




    }
}
