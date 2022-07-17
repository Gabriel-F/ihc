package com.example.gabrielfonseca.atividade1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText et1,et2;
    private TextView tvResult;
    private Button btSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.edit1);
        et2= (EditText) findViewById(R.id.edit2);
        tvResult = (TextView) findViewById(R.id.tvResult);
    }

    public void sum(View view){
        int sumRes = Integer.parseInt(et1.getText().toString()) + Integer.parseInt(et2.getText().toString());
        tvResult.setText("Result: " + String.valueOf(sumRes));
    }
}
