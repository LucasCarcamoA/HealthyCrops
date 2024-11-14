package com.example.healthycrops;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.healthycrops.Data.Repositorio;
import com.example.healthycrops.model.Sensor;
import com.example.healthycrops.model.Tipo;
import com.example.healthycrops.model.Ubicacion;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AgregarsenActivity extends AppCompatActivity {


    private Spinner tipoSensorSpinner;
    private Spinner ubicacionSensorSpinner;
    private List<Sensor> sensores;
    private List<Tipo> tiposSensor;
    private List<Ubicacion> ubicaciones;
    private EditText nombreSensorEditText, descripcionEditText, valorIdealEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregarsen);

        sensores = Repositorio.getInstance().sensores;
        tiposSensor = Repositorio.getInstance().tiposSensor;
        ubicaciones = Repositorio.getInstance().ubicaciones;
        ubicacionSensorSpinner = findViewById(R.id.ubicacionSensorSpinner);
        Button ingresarSensorButton = findViewById(R.id.agregarsen_Button);
        tipoSensorSpinner = findViewById(R.id.tipoSensorSpinner);
        nombreSensorEditText = findViewById(R.id.nombreSensor);
        descripcionEditText = findViewById(R.id.descripcionSensor);
        valorIdealEditText = findViewById(R.id.idealSensor);



        ArrayAdapter<Tipo> adapter = new ArrayAdapter<>(this, R.layout.tipo_sensor_spinner_item, tiposSensor);
        tipoSensorSpinner.setAdapter(adapter);
        ArrayAdapter<Ubicacion> adapter1 = new ArrayAdapter<>(this, R.layout.tipo_sensor_spinner_item, ubicaciones);
        ubicacionSensorSpinner.setAdapter(adapter1);

        ingresarSensorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarSensorAlRepositorio();
            }
        });
    }

    private void agregarSensorAlRepositorio() {
        String nombre = nombreSensorEditText.getText().toString().trim();
        String descripcion = descripcionEditText.getText().toString();
        String ideal = valorIdealEditText.getText().toString();
        Tipo tipoSeleccionado = (Tipo) tipoSensorSpinner.getSelectedItem();
        Ubicacion ubicacionSeleccionada = (Ubicacion) ubicacionSensorSpinner.getSelectedItem();

        if (nombre.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese el nombre", Toast.LENGTH_SHORT).show();
            return;
        }

        if (nombre.length() < 5 || nombre.length() > 15) {
            Toast.makeText(this, "El nombre debe tener entre 5 y 15 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!descripcion.isEmpty() && descripcion.length() > 30) {
            Toast.makeText(this, "La descripción debe tener un máximo de 30 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }
        if (descripcion.isEmpty()) {
            descripcion = "N/A";
        }

        float idealValue;
        try {
            idealValue = Float.parseFloat(ideal);
            if (idealValue <= 0){
                Toast.makeText(this, "El valor ideal debe ser positivo", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Valor ideal no válido", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear un nuevo sensor
        Sensor nuevoSensor = new Sensor(nombre, descripcion, idealValue, tipoSeleccionado, ubicacionSeleccionada);

        // Agregar el nuevo sensor al repositorio
        Repositorio.getInstance().agregarSensor(nuevoSensor);

        Toast.makeText(this, "Sensor agregado: " + nuevoSensor.getNombre(), Toast.LENGTH_SHORT).show();

        // Finaliza la actividad y regresa a la anterior
        finish();
    }
}
