package com.example.healthycrops;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthycrops.Data.Repositorio;
import com.example.healthycrops.adapters.SensoresAdapter;
import com.example.healthycrops.model.Sensor;
import com.example.healthycrops.model.Tipo;

import java.util.List;

public class SensoresActivity extends AppCompatActivity {

    private RecyclerView sensoresRecyclerView;
    private SensoresAdapter adapter;
    private List<Sensor> sensores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores);

        sensoresRecyclerView = findViewById(R.id.list_view);
        sensoresRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializa el adaptador con la lista actual de sensores
        actualizarListaSensores();

        Button btn_agregarsen = findViewById(R.id.agregar_sensor);
        btn_agregarsen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SensoresActivity.this, AgregarsenActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Actualiza la lista de sensores cada vez que se regresa a esta actividad
        actualizarListaSensores();
    }

    private void actualizarListaSensores() {
        sensores = Repositorio.getInstance().sensores; // Obtén la lista actualizada desde el repositorio
        adapter = new SensoresAdapter(sensores); // Crea un nuevo adaptador con la lista actualizada
        sensoresRecyclerView.setAdapter(adapter); // Establece el adaptador en el RecyclerView
    }
}
