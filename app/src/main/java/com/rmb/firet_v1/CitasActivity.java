package com.rmb.firet_v1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import java.util.List;

public class CitasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_citas);
        cargarCita();
        findViewById(R.id.btnAgregarCita).setOnClickListener(v -> {
            startActivity(new Intent(this, FormCitaActivity.class));
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main4), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void cargarCita() {
        DatabaseHelper db = new DatabaseHelper(this);
        Cursor cursor = db.obtenerCitas();

        List<String> listaCitas = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do{
                String nombrePaciente = cursor.getString(cursor.getColumnIndex("nombrePaciente"));
                String fecha = cursor.getString(cursor.getColumnIndex("fecha"));
                String hora = cursor.getString(cursor.getColumnIndex("hora"));
                String motivo = cursor.getString(cursor.getColumnIndex("motivo"));

                listaCitas.add("Paciente: " + nombrePaciente + "\nFecha: " + fecha + "\nHora: " + hora + "\nMotivo: " + motivo);
            }while (cursor.moveToNext());
        }else {
            listaCitas.add("No hay citas registradas");
        }cursor.close();


        ListView listView = findViewById(R.id.lvCitas);
        listView.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listaCitas));
    }
}