package com.rmb.firet_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
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
        Spinner etHora = findViewById(R.id.spinnerHora);
        EditText etMotivo = findViewById(R.id.etMotivo);
        EditText etDomicilio = findViewById(R.id.etDomicilio);
        RadioGroup rgTipoCita = findViewById(R.id.rgTipoCita);
        Button btnGuardarCita = findViewById(R.id.btnGuardarCita);

        //Datos del campo de horarios disponibles
        String[] horas ={
          "08:00 AM","09:00 AM","10:00 AM","11:00 AM","12:00 PM","1:00 PM","2:00 PM",
          "3:00 PM","4:00 PM","5:00 PM","6:00 PM"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, horas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etHora.setAdapter(adapter);

        //Cambios para tipo de consulta (local o foranea).
        rgTipoCita.setOnCheckedChangeListener(((group, checkedId) -> {
            if (checkedId == R.id.rbExterna) {
                etDomicilio.setVisibility(View.VISIBLE);
            } else if (checkedId == R.id.rbLocal) {
                etDomicilio.setVisibility(View.GONE);
                etDomicilio.setText("Clinica");
            }
        }));


        btnGuardarCita.setOnClickListener(v -> {
            String nombreP = etNombrePaciente.getText().toString().trim();
            String fecha = etFecha.getText().toString().trim();
            String hora = etHora.getSelectedItem().toString().trim();
            String motivo = etMotivo.getText().toString().trim();
            String domicilio = etDomicilio.getText().toString().trim();

            if (nombreP.isEmpty() || fecha.isEmpty() || hora.isEmpty() || motivo.isEmpty() || domicilio.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                DatabaseHelper db = new DatabaseHelper(this);
                boolean exito = db.insertarCita(nombreP, fecha, hora, motivo, domicilio);
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