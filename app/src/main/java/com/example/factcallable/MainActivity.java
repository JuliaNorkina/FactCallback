package com.example.factcallable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int result;
    private EditText etNumber;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumber = findViewById(R.id.etNumber);
        tvResult = findViewById(R.id.tvResult);
        findViewById(R.id.bCalculate).setOnClickListener(this);
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("result", result);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        result = savedInstanceState.getInt("result");
        tvResult.setText(String.valueOf(result));
    }

    @Override
    public void onClick(View v) {
        if (etNumber.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter number", Toast.LENGTH_LONG).show();
        } else {
            int number = Integer.parseInt(etNumber.getText().toString());

            FactTask fact = new FactTask(number);
            ExecutorService executor = Executors.newFixedThreadPool(1);
            Future<Integer> future = executor.submit(fact);
            try {
                result = future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            tvResult.setText(String.valueOf(result));
            executor.shutdown();
        }
    }
}