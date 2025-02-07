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

    Spinner spn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        @@ -36,24 +35,23 @@

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
            spn = findViewById(R.id.spnOpciones);
            switch (spn.getSelectedItemPosition()){
                case 0:
                    respuesta = num1 + num2;
                    break;
                case 1:
                    respuesta = num1 - num2;
                    break;
                case 2:
                    respuesta = num1 * num2;
                    break;
                case 3:
                    respuesta = num1 / num2;
                    break;
            }
            tempVal = findViewById(R.id.lblRespuesta);
            tempVal.setText("Respuesta: "+ respuesta);
        }
    });
}

