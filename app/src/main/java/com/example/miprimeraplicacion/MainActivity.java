package com.example.miprimeraplicacion;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TabHost tbh;
    Button btn;
    TextView tempVal;
    Spinner spn;
    conversores objConversores = new conversores();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbh = findViewById(R.id.tbhConversor);
        tbh.setup();
        tbh.addTab(tbh.newTabSpec("Monedas").setContent(R.id.tabMonedas).setIndicator("MONEDAS", null));
        tbh.addTab(tbh.newTabSpec("Longitud").setContent(R.id.tabLongitud).setIndicator("LONGITUD", null));
        tbh.addTab(tbh.newTabSpec("Tiempo").setContent(R.id.tabTiempo).setIndicator("TIEMPO", null));
        tbh.addTab(tbh.newTabSpec("Almacenamiento").setContent(R.id.tabAlmacenamiento).setIndicator("ALMACENAMIENTO", null));
        btn = findViewById(R.id.btnCalcular);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempVal = findViewById(R.id.txtNum1);
                double num1 = Double.parseDouble(tempVal.getText().toString());
                int opcion = tbh.getCurrentTab();
                spn = findViewById(R.id.spnDeMonedas);
                int de = spn.getSelectedItemPosition();

                tempVal = findViewById(R.id.txtNum2);
                double num2 = Double.parseDouble(tempVal.getText().toString());
                spn = findViewById(R.id.spnAMonedas);
                int a = spn.getSelectedItemPosition();
                tempVal = findViewById(R.id.txtCantidad);
                double cantidad = Double.parseDouble(tempVal.getText().toString());

                double respuesta = 0.0;
                String msg = "";
                spn = findViewById(R.id.spnOpciones);
                switch (spn.getSelectedItemPosition()){
                    case 0:
                        respuesta = num1 + num2;
                        msg = "La suma es: "+ respuesta;
                        break;
                    case 1:
                        respuesta = num1 - num2;
                        msg = "La resta es: "+ respuesta;
                        break;
                    case 2:
                        respuesta = num1 * num2;
                        msg = "La multiplicacion es: "+ respuesta;
                        break;
                    case 3:
                        respuesta = num1 / num2;
                        msg = "La division: "+ respuesta;
                        break;
                }
                tempVal = findViewById(R.id.lblRespuesta);
                double respuesta = objConversores.convertir(opcion, de, a, cantidad);
                tempVal.setText("Respuesta: "+ respuesta);
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });
    }
}
class conversores{
    double[][] valores= {
            {1,0.98, 7.73, 25.45, 36.78, 508.87, 8.74},//monedas
            {},//Longitud
            {},//tiempo
            {},//Almacenamiento
    };
    public double convertir(int opcion, int de, int a, double cantidad){
        return valores[opcion][a] / valores[opcion][de] * cantidad;
    }