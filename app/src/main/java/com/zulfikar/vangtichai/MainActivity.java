package com.zulfikar.vangtichai;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView txt500Val, txt100Val, txt50Val, txt20Val, txt10Val, txt5Val, txt2Val, txt1Val, txtTaka;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt500Val = findViewById(R.id.txt500val);
        txt100Val = findViewById(R.id.txt100val);
        txt50Val = findViewById(R.id.txt50val);
        txt20Val = findViewById(R.id.txt20val);
        txt10Val = findViewById(R.id.txt10val);
        txt5Val = findViewById(R.id.txt5val);
        txt2Val = findViewById(R.id.txt2val);
        txt1Val = findViewById(R.id.txt1val);
        txtTaka = findViewById(R.id.txtTaka);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn0 = findViewById(R.id.btn0);
        btnClear = findViewById(R.id.btnClear);

        for (Button btn : new Button[]{
                btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0}) {
            btn.setOnClickListener(v -> {
                String taka = txtTaka.getText().toString().concat(btn.getText().toString());
                int value = Integer.parseInt("0" + taka.substring(6));
                if (value > 4999999) {
                    Toast.makeText(MainActivity.this, "Maximum amount is 4999999", Toast.LENGTH_SHORT).show();
                } else {
                    txtTaka.setText(taka);
                    txt500Val.setText(String.valueOf(value / 500));
                    txt100Val.setText(String.valueOf(value % 500 / 100));
                    txt50Val.setText(String.valueOf(value % 500 % 100 / 50));
                    txt20Val.setText(String.valueOf(value % 500 % 100 % 50 / 20));
                    txt10Val.setText(String.valueOf(value % 500 % 100 % 50 % 20 / 10));
                    txt5Val.setText(String.valueOf(value % 500 % 100 % 50 % 20 % 10 / 5));
                    txt2Val.setText(String.valueOf(value % 500 % 100 % 50 % 20 % 10 % 5 / 2));
                    txt1Val.setText(String.valueOf(value % 500 % 100 % 50 % 20 % 10 % 5 % 2));
                }
            });
        }

        btnClear.setOnClickListener(this::btnClearOnClick);
        btnClear.setOnLongClickListener(this::btnClearOnLongClick);
        recover(savedInstanceState);
    }

    private void btnClearOnClick(View view) {
        String takaText = txtTaka.getText().toString();
        if (!takaText.equals("Taka: ")) {
            txtTaka.setText(takaText.substring(0, takaText.length() - 1));
        }
    }

    private boolean btnClearOnLongClick(View view) {
        txtTaka.setText("Taka: ");
        return true;
    }

    private void recover(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            txtTaka.setText(savedInstanceState.getString("taka"));
            TextView[] tviews = {txt500Val, txt100Val, txt50Val,
                    txt20Val, txt10Val, txt5Val, txt2Val, txt1Val};
            String[] denominations = savedInstanceState.getStringArray("denominations");
            for (int i = 0; i < tviews.length; i++) {
                tviews[i].setText(denominations[i]);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("taka", txtTaka.getText().toString());
        outState.putStringArray("denominations", new String[]{
                txt500Val.getText().toString(),
                txt100Val.getText().toString(),
                txt50Val.getText().toString(),
                txt20Val.getText().toString(),
                txt10Val.getText().toString(),
                txt5Val.getText().toString(),
                txt2Val.getText().toString(),
                txt1Val.getText().toString(),
                txtTaka.getText().toString()
        });
        super.onSaveInstanceState(outState);
    }
}