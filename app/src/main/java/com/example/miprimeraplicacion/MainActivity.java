package com.example.miprimeraplicacion;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etMetrosConsumidos, etAreaValor;
    private TextView tvResultado, tvAreaResultado;
    private Button btnCalcular, btnConvertir;
    private Spinner spUnidadOrigen, spUnidadDestino;
    private final double TARIFA_POR_METRO = 0.65; // Tarifa por metro cúbico consumido mayor a 28 metros
    private final double TARIFA_BASE = 6.00; // Tarifa base fija para consumos de 1 a 18 metros
    private final double TARIFA_INTERMEDIA = 0.45; // Tarifa por metro cúbico entre 19 y 28 metros

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMetrosConsumidos = findViewById(R.id.etMetrosConsumidos);
        etAreaValor = findViewById(R.id.etAreaValor);
        tvResultado = findViewById(R.id.tvResultado);
        tvAreaResultado = findViewById(R.id.tvAreaResultado);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnConvertir = findViewById(R.id.btnConvertir);
        spUnidadOrigen = findViewById(R.id.spUnidadOrigen);
        spUnidadDestino = findViewById(R.id.spUnidadDestino);

        String[] unidades = {"Pie Cuadrado", "Vara Cuadrada", "Yarda Cuadrada", "Metro Cuadrado", "Tareas", "Manzana", "Hectárea"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, unidades);
        spUnidadOrigen.setAdapter(adapter);
        spUnidadDestino.setAdapter(adapter);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularPago();
            }
        });

        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertirArea();
            }
        });
    }

    private void calcularPago() {
        String metrosStr = etMetrosConsumidos.getText().toString();
        if (!metrosStr.isEmpty()) {
            double metrosConsumidos = Double.parseDouble(metrosStr);
            double totalPagar;
            if (metrosConsumidos >= 1 && metrosConsumidos <= 18) {
                totalPagar = TARIFA_BASE;
            } else if (metrosConsumidos > 18 && metrosConsumidos <= 28) {
                totalPagar = TARIFA_BASE + ((metrosConsumidos - 18) * TARIFA_INTERMEDIA);
            } else {
                totalPagar = TARIFA_BASE + (10 * TARIFA_INTERMEDIA) + ((metrosConsumidos - 28) * TARIFA_POR_METRO);
            }
            tvResultado.setText("Total a pagar: $" + String.format("%.2f", totalPagar));
        } else {
            tvResultado.setText("Ingrese los metros consumidos");
        }
    }

    private void convertirArea() {
        String valorStr = etAreaValor.getText().toString();
        if (!valorStr.isEmpty()) {
            double valor = Double.parseDouble(valorStr);
            String unidadOrigen = spUnidadOrigen.getSelectedItem().toString();
            String unidadDestino = spUnidadDestino.getSelectedItem().toString();
            double factorConversion = obtenerFactorConversion(unidadOrigen, unidadDestino);
            double resultado = valor * factorConversion;
            tvAreaResultado.setText("Resultado: " + String.format("%.2f", resultado) + " " + unidadDestino);
        } else {
            tvAreaResultado.setText("Ingrese un valor");
        }
    }

    private double obtenerFactorConversion(String origen, String destino) {
        // Factores de conversión básicos (referencia en metros cuadrados)
        double metroCuadrado = 1.0;
        double pieCuadrado = 0.092903;
        double varaCuadrada = 0.6987;
        double yardaCuadrada = 0.836127;
        double tareas = 628.86;
        double manzana = 7000.0;
        double hectarea = 10000.0;

        double factorOrigen = switch (origen) {
            case "Pie Cuadrado" -> pieCuadrado;
            case "Vara Cuadrada" -> varaCuadrada;
            case "Yarda Cuadrada" -> yardaCuadrada;
            case "Metro Cuadrado" -> metroCuadrado;
            case "Tareas" -> tareas;
            case "Manzana" -> manzana;
            case "Hectárea" -> hectarea;
            default -> 1.0;
        };

        double factorDestino = switch (destino) {
            case "Pie Cuadrado" -> pieCuadrado;
            case "Vara Cuadrada" -> varaCuadrada;
            case "Yarda Cuadrada" -> yardaCuadrada;
            case "Metro Cuadrado" -> metroCuadrado;
            case "Tareas" -> tareas;
            case "Manzana" -> manzana;
            case "Hectárea" -> hectarea;
            default -> 1.0;
        };

        return factorOrigen / factorDestino;
    }
}