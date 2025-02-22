package com.example.miprimeraplicacion;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
@@ -17,64 +20,54 @@

        import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView tempVal;
    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    LocationManager locationManager;
    LocationListener locationListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorLuz();
    }
    @Override
    protected void onResume() {
        iniciar();
        super.onResume();
    }
    @Override
    protected void onPause() {
        detener();
        super.onPause();
        tempVal = findViewById(R.id.lblSensorGps);
        obtenerPosicion();
    }
    private void iniciar(){
        sensorManager.registerListener(sensorEventListener, sensor, 2000*1000);
    }
    private void detener(){
        sensorManager.unregisterListener(sensorEventListener);
    }
    private void sensorLuz(){
        tempVal = findViewById(R.id.lblSensorProximidad);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if( sensor==null ){
            tempVal.setText("Tu dispositivo, NO tiene el senor de PROXIMIDAD");
            finish();
        }
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                double valor = event.values[0];
                tempVal.setText("Proximidad: "+ valor);

                if(valor<=4){
                    getWindow().getDecorView().setBackgroundColor(Color.BLACK);
                }else if(valor<=8){
                    getWindow().getDecorView().setBackgroundColor(Color.GRAY);
                }else{
                    getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

                void obtenerPosicion(){
                    try{
                        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != getPackageManager().PERMISSION_GRANTED &&
                                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != getPackageManager().PERMISSION_GRANTED){
                            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                            tempVal.setText("Solicitando permisos de ubicación...");
                        }
                    };
                    locationListener = new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            mostrarUbicacion(location);
                        }
                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {
                            tempVal.setText("Estado del proveedor: "+ status);
                        }
                        @Override
                        public void onProviderEnabled(String provider) {
                            tempVal.setText("Proveedor habilitado: "+ provider);
                        }
                        @Override
                        public void onProviderDisabled(String provider) {
                            tempVal.setText("Proveedor deshabilitado: "+ provider);
                        }
                    };
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                }catch (SecurityException e){
                    tempVal.setText("Error al obtener la ubicación: "+ e.getMessage());
                }
            }
            void mostrarUbicacion(Location location){
                tempVal.setText("Latitud: "+ location.getLatitude() + "\nLongitud: "+ location.getLongitude() + "\nAltitud: "+ location.getAltitude());
            }
        }