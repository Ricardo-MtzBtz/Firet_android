package com.rmb.firet_v1;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnPacientes).setOnClickListener(v -> {
            startActivity(new Intent(this, PacientesActivity.class));
        });

        findViewById(R.id.btnCitas).setOnClickListener(v -> {
            startActivity(new Intent(this, CitasActivity.class));
        });

        findViewById(R.id.btnReportes).setOnClickListener(v -> {
            // Redirigir a la actividad de reportes
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}