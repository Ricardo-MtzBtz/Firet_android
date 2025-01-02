package com.rmb.firet_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FormPacienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_paciente);

        EditText etNombre = findViewById(R.id.etNombre);
        EditText etEdad = findViewById(R.id.etEdad);
        EditText etPeso = findViewById(R.id.etPeso);
        EditText etMotivo = findViewById(R.id.etMotivoConsulta);
        EditText etDetallesEnfermedades = findViewById(R.id.etDetallesEnfermedades);
        EditText etAntecedente = findViewById(R.id.etAntecendentes);
        EditText etDomicilio = findViewById(R.id.etDomicilio);
        RadioGroup rgEnfermedadesCronicas = findViewById(R.id.rgEnfermedadesCronicas);
        RadioButton rbSi = findViewById(R.id.rbSi);
        RadioButton rbNo = findViewById(R.id.rbNo);
        Button btnGuardar = findViewById(R.id.btnGuardarPaciente);

        rgEnfermedadesCronicas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbSi) {
                    // Mostrar campo de texto
                    etDetallesEnfermedades.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.rbNo) {
                    // Ocultar campo de texto y limpiar contenido
                    etDetallesEnfermedades.setVisibility(View.GONE);
                    etDetallesEnfermedades.setText("No tiene enfermedades");
                }
            }
        });

        btnGuardar.setOnClickListener(v -> {
            // Captura de datos (ejemplo)

            String nombre = etNombre.getText().toString().trim();
            String edadstr = etEdad.getText().toString().trim();
            String pesostr = etPeso.getText().toString().trim();
            String motivo  = etMotivo.getText().toString().trim();
            boolean tieneEnfermedadesCronicas = rbSi.isChecked();
            String detallesEnfermedades;
            String ante = etAntecedente.getText().toString().trim();
            String domicilio = etDomicilio.getText().toString().trim();

            //Detectar texto de enfermedades cronicas

            if (tieneEnfermedadesCronicas) {
                detallesEnfermedades = etDetallesEnfermedades.getText().toString().trim();
            if (detallesEnfermedades.isEmpty()) {
                Toast.makeText(this,"Por favor, indique los dettalles de "
                        + "las enfermedades cronicas",Toast.LENGTH_SHORT).show();
                return;
            }}else {
                detallesEnfermedades = "No tiene enfermedades Cronicas";
            }
            // Aquí se guardarían los datos en la base de datos
            if (nombre.isEmpty() || edadstr.isEmpty() || pesostr.isEmpty() || motivo.isEmpty() ) {
                Toast.makeText(this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                //Conversion de edad y peso a enteros
                int edad = Integer.parseInt(edadstr);
                double peso = Double.parseDouble(pesostr);

                //Guardar a la base de datos
                DatabaseHelper db = new DatabaseHelper(this);
                boolean exito = db.insertarPaciente(nombre, edad, peso, motivo, domicilio, ante, detallesEnfermedades);
                if (exito) {
                    Toast.makeText(this, "Paciente guardado: " + nombre, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(this,PacientesActivity.class);
                    startActivity(i);
                    finish(); // Cerrar la actividad
                } else {
                    Toast.makeText(this, "Error al guardar el paciente.", Toast.LENGTH_SHORT).show();
                }
            }catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor, ingrese valores numéricos válidos.", Toast.LENGTH_SHORT).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main3), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}