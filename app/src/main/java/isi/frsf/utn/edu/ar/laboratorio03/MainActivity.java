package isi.frsf.utn.edu.ar.laboratorio03;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.support.design.widget.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import dao.TrabajoDao;
import dao.TrabajoDaoJSON;
import dao.TrabajoDaoSQLite;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    FloatingActionButton fbAlta;
    ArrayList<Trabajo> listaTrabajos;

    CustomAdapter adaptador;

    TrabajoDao trabajoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            TrabajoDao trabajoCategs = new TrabajoDaoJSON();
            Trabajo.setDao(trabajoCategs);
        }
        catch(Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        trabajoDao = new TrabajoDaoSQLite(this);

        try{
            listaTrabajos = new ArrayList<Trabajo>();
            listaTrabajos.addAll(trabajoDao.listaTrabajo());
            lv = (ListView)findViewById(R.id.listView);
            adaptador = new CustomAdapter(this, listaTrabajos);
            lv.setAdapter(adaptador);
            registerForContextMenu(lv);

            fbAlta = (FloatingActionButton)findViewById(R.id.fbAlta);
            fbAlta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, AltaActivity.class);
                    intent.putExtra("id", listaTrabajos.size() + 1);
                    startActivityForResult(intent, 1);
                }
            });
        }
        catch(Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        menu.setHeaderTitle("Acciones");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuinfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Long ids = adaptador.getItemId(info.position);
        int i = ids.intValue();
        if(item.getItemId() == R.id.postularse){
            postularseTrabajo(listaTrabajos.get(i));
        }
        else if(item.getItemId() == R.id.descartar){
            descartarTrabajo(listaTrabajos.get(i));
        }
        else{
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            try{
                Trabajo result = (Trabajo)data.getParcelableExtra("trabRes");
                trabajoDao.crearOferta(result);
                listaTrabajos = new ArrayList<>();
                listaTrabajos.addAll(trabajoDao.listaTrabajo());
                adaptador = new CustomAdapter(this, listaTrabajos);
                lv.setAdapter(adaptador);
            }
            catch(Exception ex){
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void descartarTrabajo(Trabajo t){
        trabajoDao.eliminarOferta(t);
        listaTrabajos = new ArrayList<>();
        listaTrabajos.addAll(trabajoDao.listaTrabajo());
        adaptador = new CustomAdapter(this, listaTrabajos);
        lv.setAdapter(adaptador);
        Toast.makeText(MainActivity.this, "Se ha descartado la oferta de trabajo " + t.getDescripcion() + " correctamente.", Toast.LENGTH_LONG).show();
    }

    private void postularseTrabajo(Trabajo t){
        Toast.makeText(MainActivity.this, "La postulación a la oferta " + t.getDescripcion() + " fue aceptada correctamente.", Toast.LENGTH_LONG).show();
    }
}