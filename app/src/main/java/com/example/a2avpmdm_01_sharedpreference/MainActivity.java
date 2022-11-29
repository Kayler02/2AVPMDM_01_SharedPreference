package com.example.a2avpmdm_01_sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private EditText txtNombre;
    private EditText txtEdad;
    private Button btnGuardar;

    private Button btnBorrar;
    private ImageButton btnEliminarNombre;
    private ImageButton btnEliminarEdad;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private SharedPreferences sp;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarVistas();

        inicializarSharedPreferences();

        introducirClickListener();
    }

    private void inicializarSharedPreferences() {
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //MODE_PRIVATE -> Es un protector para que ninguna otra linea de ejecución tenga la capacidad de cambiar la información que contenga.
        sp = getSharedPreferences(Constantes.PERSONAS, MODE_PRIVATE);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //LEER LA SHAREDPREFERENCE -> Constantes.nombre será el tag que pedirá. Si no, el s1 será el por defecto, osea, vacío.
        String nombre = sp.getString(Constantes.NOMBRE,"");
        int edad = sp.getInt(Constantes.EDAD, -1);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (!nombre.isEmpty()){
            txtNombre.setText(nombre);
        }
        if (edad != -1){
            txtEdad.setText(String.valueOf(edad));
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    private void introducirClickListener() {
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtNombre.getText().toString();
                int edad = Integer.parseInt(txtEdad.getText().toString());

                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                //El editor tiene el mismo estilo que un Bundle (mochila de dora la exploradora)
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(Constantes.NOMBRE, nombre);
                editor.putInt(Constantes.EDAD,edad);
                // Se tiene que usar commit o apply -> Commit para la ejecución de programa hasta que se escriba -> Apply es cuando pueda escribir, lo hará.
                //De normal se usa el apply pero si tienes alguna dependencia -> Reventará si no se cumplen los tiempos -> El commit es sincrono, a diferencia del apply
                //que es asincrono.
                editor.apply();
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                SharedPreferences.Editor editor = sp.edit();
                //El clear limpiará todo el contenido que tenga el editor.
                editor.clear();
                editor.apply();
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            }
        });

        btnEliminarNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                SharedPreferences.Editor editor = sp.edit();
                //El remove quitará el contenido relacionado con NOMBRE.
                editor.remove(Constantes.NOMBRE);
                editor.apply();
                txtNombre.setText("");
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            }
        });

        btnEliminarEdad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                SharedPreferences.Editor editor = sp.edit();
                //El remove quitará el contenido relacionado con EDAD.
                editor.remove(Constantes.EDAD);
                editor.apply();
                //Si pones -1 explota
                txtEdad.setText("");
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            }
        });
    }

    private void inicializarVistas() {
        txtNombre = findViewById(R.id.txtNombre);
        txtEdad = findViewById(R.id.txtEdad);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnBorrar = findViewById(R.id.btnBorrar);
        btnEliminarNombre = findViewById(R.id.btnNombreBorrar);
        btnEliminarEdad = findViewById(R.id.btnEdadBorrar);
    }



}