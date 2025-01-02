package com.rmb.firet_v1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Nombres y version de la base de datos
    private static final String DATABASE_NAME = "firet_clinica.db";
    private static final int DATABASE_VERSION = 1;

    //Tables
    private static final String TABLE_PACIENTES = "Pacientes";
    private static final String TABLE_CITAS = "Citas";

    //Columnas de la tabla Pacientes
    private static final String COL_PACIENTE_ID = "id";
    private static final String COL_PACIENTE_NOMBRE = "nombre";
    private static final String COL_PACIENTE_EDAD = "edad";
    private static final String COL_PACIENTE_PESO = "peso";
    private static final String COL_PACIENTE_MOTIVO = "motivo";
    private static final String COL_PACIENTE_DOMICILIO = "domicilio";
    private static final String COL_PACIENTE_ANTECEDENTES = "antecedentes";
    private static final String COL_PACIENTE_DETALLES = "detalles";

    //Columnas de la tabla Citas
    private static final String COL_CITA_ID = "id";
    private static final String COL_CITA_PACIENTE = "nombrePaciente";
    private static final String COL_CITA_FECHA = "fecha";
    private static final String COL_CITA_HORA = "hora";
    private static final String COL_CITA_MOTIVO = "motivo";

    //Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crear tables Pacientes
        String CREATE_PACIENTES_TABLE = "CREATE TABLE " + TABLE_PACIENTES + "("
                + COL_PACIENTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_PACIENTE_NOMBRE + " TEXT, "
                + COL_PACIENTE_EDAD + " INTEGER, "
                + COL_PACIENTE_PESO + " REAL, "
                + COL_PACIENTE_MOTIVO + " TEXT, "
                + COL_PACIENTE_DOMICILIO + " TEXT, "
                + COL_PACIENTE_ANTECEDENTES + " TEXT, "
                + COL_PACIENTE_DETALLES + " TEXT )";
        db.execSQL(CREATE_PACIENTES_TABLE);

        //Crear tabla citas
        String CREATE_CITAS_TABLE = "CREATE TABLE " + TABLE_CITAS + " ("
                + COL_CITA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_CITA_PACIENTE + " TEXT, "
                + COL_CITA_FECHA + " TEXT, "
                + COL_CITA_HORA + " TEXT, "
                + COL_CITA_MOTIVO + " TEXT )";
        db.execSQL(CREATE_CITAS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PACIENTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CITAS);
        onCreate(db);
    }

    //Metodos CRUD

    //Insertar Paciente
    public boolean insertarPaciente(String nombre, int edad, double peso, String motivo,
                                    String domicilio, String antecedentes, String detalles) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_PACIENTE_NOMBRE, nombre);
        values.put(COL_PACIENTE_EDAD, edad);
        values.put(COL_PACIENTE_PESO, peso);
        values.put(COL_PACIENTE_MOTIVO, motivo);
        values.put(COL_PACIENTE_DOMICILIO, domicilio);
        values.put(COL_PACIENTE_ANTECEDENTES, antecedentes);
        values.put(COL_PACIENTE_DETALLES, detalles);

        long result = db.insert(TABLE_PACIENTES,null,values);
        db.close();
        return result != -1;
    }
    //Insertar Cita
    public boolean insertarCita(String nombrePaciente, String fecha, String hora, String motivo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_CITA_PACIENTE, nombrePaciente);
        values.put(COL_CITA_FECHA, fecha);
        values.put(COL_CITA_HORA, hora);
        values.put(COL_CITA_MOTIVO, motivo);


        long result = db.insert(TABLE_CITAS,null,values);
        db.close();
        return result != -1;
    }
    //Obtener todos los pacientes
    public Cursor obtenerPacientes() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_PACIENTES, null);
    }
    //Obtener todas las citas
    public Cursor obtenerCitas() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_CITAS, null);
    }

}




