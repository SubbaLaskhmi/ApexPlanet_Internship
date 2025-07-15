package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewDisplay;
    private StringBuilder expression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewDisplay = findViewById(R.id.textViewDisplay);
        expression = new StringBuilder();

        int[] buttonIds = {
                R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3,
                R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7,
                R.id.btn_8, R.id.btn_9, R.id.btn_add, R.id.btn_sub,
                R.id.btn_mul, R.id.btn_div, R.id.btn_dot, R.id.btn_clear,
                R.id.btn_del, R.id.btn_equal, R.id.btn_percent
        };

        for (int id : buttonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        Button clicked = (Button) view;
        String value = clicked.getText().toString();

        switch (value) {
            case "AC":
                expression.setLength(0);
                textViewDisplay.setText("0");
                break;

            case "DEL":
                if (expression.length() > 0) {
                    expression.setLength(expression.length() - 1);
                    textViewDisplay.setText(expression.length() > 0 ? expression.toString() : "0");
                }
                break;

            case "=":
                try {
                    double result = new ExpressionEvaluator().evaluate(expression.toString());

                    if (result == (int) result) {
                        textViewDisplay.setText(String.valueOf((int) result));
                    } else {
                        textViewDisplay.setText(String.valueOf(result));
                    }

                    expression.setLength(0);

                } catch (Exception e) {
                    textViewDisplay.setText("Error");
                    expression.setLength(0);
                }
                break;

            default:
                expression.append(value);
                textViewDisplay.setText(expression.toString());
                break;
        }
    }

    // Native expression evaluator without external libraries
    class ExpressionEvaluator {
        int pos = -1, ch;

        public double evaluate(String str) {
            str = str.replace("×", "*").replace("÷", "/").replace("%", "/100.0");
            pos = -1;
            ch = 0;
            return parse(str);
        }

        private void nextChar(String str) {
            ch = (++pos < str.length()) ? str.charAt(pos) : -1;
        }

        private boolean eat(String str, int charToEat) {
            while (ch == ' ') nextChar(str);
            if (ch == charToEat) {
                nextChar(str);
                return true;
            }
            return false;
        }

        private double parse(String str) {
            nextChar(str);
            double x = parseExpression(str);
            if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
            return x;
        }

        private double parseExpression(String str) {
            double x = parseTerm(str);
            while (true) {
                if (eat(str, '+')) x += parseTerm(str);
                else if (eat(str, '-')) x -= parseTerm(str);
                else return x;
            }
        }

        private double parseTerm(String str) {
            double x = parseFactor(str);
            while (true) {
                if (eat(str, '*')) x *= parseFactor(str);
                else if (eat(str, '/')) x /= parseFactor(str);
                else return x;
            }
        }

        private double parseFactor(String str) {
            if (eat(str, '+')) return parseFactor(str);
            if (eat(str, '-')) return -parseFactor(str);

            double x;
            int startPos = this.pos;
            if ((ch >= '0' && ch <= '9') || ch == '.') {
                while ((ch >= '0' && ch <= '9') || ch == '.') nextChar(str);
                x = Double.parseDouble(str.substring(startPos, this.pos));
            } else {
                throw new RuntimeException("Unexpected character: " + (char) ch);
            }

            return x;
        }
    }
}
