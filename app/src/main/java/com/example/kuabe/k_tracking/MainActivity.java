package com.example.kuabe.k_tracking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        EditText etNumerator =  findViewById(R.id.numerator);
        EditText etDenumerator = findViewById(R.id.denumerator);

        int num =Integer.parseInt(etNumerator.getText().toString());
        int denum = Integer.parseInt(etDenumerator.getText().toString());
        double res = 0;

        res = num/denum;

        etNumerator.setText(Double.toString(res));
        etDenumerator.setText(Integer.toString(0));


    }
}
