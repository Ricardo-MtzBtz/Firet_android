package com.rmb.firet_v1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FormCitaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_cita);

        EditText etNombrePaciente = findViewById(R.id.etNombrePaciente);
        EditText etFecha = findViewById(R.id.etFecha);
        EditText etHora = findViewById(R.id.etHora);
        EditText etMotivo = findViewById(R.id.etMotivo);
        Button btnGuardarCita = findViewById(R.id.btnGuardarCita);

        btnGuardarCita.setOnClickListener(v -> {
            String nombreP = etNombrePaciente.getText().toString().trim();
            String fecha = etFecha.getText().toString().trim();
            String hora = etHora.getText().toString().trim();
            String motivo = etMotivo.getText().toString().trim();

            if (nombreP.isEmpty() || fecha.isEmpty() || hora.isEmpty() || motivo.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                DatabaseHelper db = new DatabaseHelper(this);
                boolean exito = db.insertarCita(nombreP, fecha, hora, motivo);
                if (exito) {
                    Toast.makeText(this, "Cita Guardada: " + nombreP, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(this, CitasActivity.class);
                    startActivity(i);
                    finish(); // Cerrar la actividad
                } else {
                    Toast.makeText(this, "Error al guardar la cita.", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Por favor, ingrese valores numéricos válidos.", Toast.LENGTH_SHORT).show();

            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main5), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}