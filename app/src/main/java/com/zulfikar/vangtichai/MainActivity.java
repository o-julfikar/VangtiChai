package com.zulfikar.vangtichai;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView txt500Val, txt100Val, txt50Val, txt20Val, txt10Val, txt5Val, txt2Val, txt1Val, txtTaka;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnClear;
    private Toast limitToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        limitToast = Toast.makeText(MainActivity.this, "Maximum amount is 4999999", Toast.LENGTH_SHORT);

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

        addButtonClickListener();
        recover(savedInstanceState);
    }

    private void addButtonClickListener() {
        for (Button btn : new Button[]{btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0}) {
            btn.setOnClickListener(v -> {
                int value = getTaka(txtTaka.getText().toString().concat(btn.getText().toString()));
                if (value > 4999999) {
                    limitToast.show();
                } else {
                    txtTaka.setText(String.format(java.util.Locale.ENGLISH, "%s%d", getString(R.string.taka), value));
                    vangtiChai(value);
                }
            });
        }
        btnClear.setOnClickListener(this::btnClearOnClick);
        btnClear.setOnLongClickListener(this::btnClearOnLongClick);
    }

    private void btnClearOnClick(View view) {
        String takaText = txtTaka.getText().toString();
        if (!takaText.equals(getString(R.string.taka))) {
            txtTaka.setText(takaText.substring(0, takaText.length() - 1));
            vangtiChai(getTaka(txtTaka.getText().toString()));
        }
    }

    private boolean btnClearOnLongClick(View view) {
        txtTaka.setText(getString(R.string.taka));
        vangtiChai(0);
        return true;
    }

    private int getTaka(String taka) {
        return Integer.parseInt("0" + taka.substring(6));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("taka", txtTaka.getText().toString());
        super.onSaveInstanceState(outState);
    }

    private void recover(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            txtTaka.setText(savedInstanceState.getString("taka"));
            vangtiChai(getTaka(txtTaka.getText().toString()));
        }
    }

    private void vangtiChai(int value) {
        int[] notes = {value + 1, 500, 100, 50, 20, 10, 5, 2, 1};
        TextView[] views = {txt500Val, txt100Val, txt50Val, txt20Val, txt10Val, txt5Val, txt2Val, txt1Val};
        for (int i = 0; i < views.length; i++)
            views[i].setText(String.valueOf((value %= notes[i]) / notes[i + 1]));
    }
}