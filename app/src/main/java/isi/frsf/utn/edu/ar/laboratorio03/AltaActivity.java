package isi.frsf.utn.edu.ar.laboratorio03;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class AltaActivity extends AppCompatActivity implements View.OnClickListener {


    EditText etDescripcion;
    Button btnGuardar;

    int idTrab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta);
        idTrab = getIntent().getIntExtra("id", 0);
        if(idTrab == 0){
            Intent i = getIntent();
            setResult(Activity.RESULT_CANCELED, i);
            finish();
        }
        else{
            etDescripcion = (EditText)findViewById(R.id.etDescripcion);
            btnGuardar = (Button)findViewById(R.id.btnGuardar);
            btnGuardar.setOnClickListener(this);
        }
    }

    private void retornar(Trabajo trab){
        Intent i = new Intent();
        i.putExtra("trabRes", trab);
        setResult(Activity.RESULT_OK, i);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnGuardar:{
                if(!etDescripcion.getText().toString().equals("")){
                    Trabajo trab = new Trabajo(idTrab, etDescripcion.getText().toString());
                    trab.asignarCategoria();
                    retornar(trab);
                }
                else{
                    Toast.makeText(AltaActivity.this, "Error, debe completar todos los campos.", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }
}
