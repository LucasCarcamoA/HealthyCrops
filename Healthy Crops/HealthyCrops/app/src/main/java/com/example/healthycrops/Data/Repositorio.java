package com.example.healthycrops.Data;

import com.example.healthycrops.model.Sensor;
import com.example.healthycrops.model.Tipo;
import com.example.healthycrops.model.Ubicacion;

import java.util.ArrayList;
import java.util.List;

public class Repositorio {
    private static Repositorio instance = null;

    public List<Sensor> sensores;
    public List<Tipo> tiposSensor;
    public List<Ubicacion> ubicaciones;

    protected Repositorio(){
        sensores = new ArrayList<>();

        tiposSensor = new ArrayList<>();
        tiposSensor.add(new Tipo("Humedad"));
        tiposSensor.add(new Tipo("Temperatura"));
        ubicaciones = new ArrayList<>();
        ubicaciones.add(new Ubicacion("Invernadero", "Plantas en invernadero"));
        ubicaciones.add(new Ubicacion("Hidroponico", "Lechugas en hidroponia"));
        ubicaciones.add(new Ubicacion("Arándanos", "Sector de cosecha de arándanos"));
        sensores.add(new Sensor("Sensor De Prueba 1", "Esta es una descripción", 1.4f, tiposSensor.get(1), ubicaciones.get(0)));
        sensores.add(new Sensor("Sensor De Prueba 2", "N/A", 70f, tiposSensor.get(0), ubicaciones.get(1)));

    }

    public static synchronized Repositorio getInstance(){
        if (instance == null) {
            instance = new Repositorio();
        }
        return instance;
    }

    public void agregarSensor(Sensor sensor) {
        sensores.add(sensor);
    }



}
