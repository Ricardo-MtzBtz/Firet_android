package com.rmb.firet_v1;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class PacientesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pacientes);
        cargar();
        findViewById(R.id.btnAgregarPaciente).setOnClickListener(v -> {
            startActivity(new Intent(this, FormPacienteActivity.class));
        });

        //Accion para el boton "Regresar al menu principal"
        findViewById(R.id.btnRegresarMenu).setOnClickListener(v -> {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void cargar () {
        DatabaseHelper db = new DatabaseHelper(this);
        Cursor cursor = db.obtenerPacientes();

        // Crear una lista para mostrar los pacientes
        List<String> listaPacientes = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                String motivo = cursor.getString(cursor.getColumnIndex("motivo"));
                String edad = cursor.getString(cursor.getColumnIndex("edad"));
                String antecedentes = cursor.getString(cursor.getColumnIndex("antecedentes"));
                String domicilio = cursor.getString(cursor.getColumnIndex("domicilio"));
                String detalles = cursor.getString(cursor.getColumnIndex("detalles"));
                listaPacientes.add("\nNombre: "+ nombre + "\nMotivo: " + motivo + "\nEdad: " + edad+ "\nAntecedentes: "+ antecedentes + "\nDomicilio: " +domicilio+ "\nEnfermedad Cronica: " +detalles + "\n");
            } while (cursor.moveToNext());
        } else {
            listaPacientes.add("No hay pacientes registrados");
        }
        cursor.close();

        // Configurar el ListView
        ListView listView = findViewById(R.id.lvPacientes);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaPacientes));
    }
}