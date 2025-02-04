package com.example.miPrimerAplicacion;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.miPrimerAplicacion.R;

import org.w3c.dom.Text;
public class MainActivity extends AppCompatActivity {
        Button btn;
        TextView tempVal;
        RadioGroup rgo;
        RadioButton opt;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            btn = findViewById(R.id.btnCalcular);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tempVal = findViewById(R.id.txtNum1);
                    double num1 = Double.parseDouble(tempVal.getText().toString());

                    tempVal= findViewById(R.id.txtNum2);
                    double num2 = Double.parseDouble(tempVal.getText().toString());

                    double respuesta = 0.0;

                    opt = findViewById(R.id.optSuma);
                    if (opt.isChecked()) {
                        respuesta = num1 + num2;
                    }
                    opt = findViewById(R.id.optResta);
                    if (opt.isChecked()) {
                        respuesta = num1 - num2;
                    }
                    opt = findViewById(R.id.optMultiplicacion);
                    if (opt.isChecked()) {
                        respuesta = num1 * num2;
                    }
                    opt = findViewById(R.id.optDivision);
                    if (opt.isChecked()) {
                        respuesta = num1 / num2;
                    }
                    opt = findViewById(R.id.optExponenciacion);
                    if (opt.isChecked()) {
                        respuesta = Math.pow(num1, num2);
                    }
                    opt = findViewById(R.id.optPorcentaje);
                    if (opt.isChecked()) {
                        respuesta = (num1 * num2) / 100;
                    }
                    opt = findViewById(R.id.optRaizCuarta);
                    if (opt.isChecked()) {
                        respuesta = Math.pow(num1, 1.0 / 4);
                    }
                    opt = findViewById(R.id.optFactorial);
                    if (opt.isChecked()) {
                        respuesta = factorial((int) num1);
                    }

                    tempVal= findViewById(R.id.lblRespuesta);
                    tempVal.setText("Respuesta: " + respuesta);
                }
            });
        }

        private int factorial(int num) {
            if (num <= 1) return 1;
            return num * factorial(num - 1);
        }
    }



