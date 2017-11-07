package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import isi.frsf.utn.edu.ar.laboratorio03.Categoria;
import isi.frsf.utn.edu.ar.laboratorio03.MainActivity;
import isi.frsf.utn.edu.ar.laboratorio03.Trabajo;
import isi.frsf.utn.edu.ar.laboratorio03.WorkFromHomeOpenHelper;

/**
 * Created by fede_ on 26/10/2017.
 */

public class TrabajoDaoSQLite implements TrabajoDao{

    private ArrayList<Trabajo> listaTrabajos;
    WorkFromHomeOpenHelper sqlDbHelper;

    public TrabajoDaoSQLite(Context ctx){
        try{
            sqlDbHelper = WorkFromHomeOpenHelper.getInstance(ctx);
            listaTrabajos =  new ArrayList<>();
            listaTrabajos.addAll(Arrays.asList(Trabajo.TRABAJOS_MOCK));
        }
        catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public List<Categoria> listaCategoria() {
        return null;
    }

    @Override
    public void crearOferta(Trabajo p) {
        ContentValues trabajo = new ContentValues(7);
        trabajo.put(WorkFromHomeOpenHelper.CAMPO_DESCRIPCION_TRABAJO, p.getDescripcion());
        trabajo.put(WorkFromHomeOpenHelper.CAMPO_HORASPRESUPUESTADAS_TRABAJO, p.getHorasPresupuestadas());
        if(p.getCategoria() != null){
            trabajo.put(WorkFromHomeOpenHelper.CAMPO_IDCATEG_TRABAJO, p.getCategoria().getId());
        }
        trabajo.put(WorkFromHomeOpenHelper.CAMPO_PRECIOMAXIMOHORA_TRABAJO, p.getPrecioMaximoHora());
        trabajo.put(WorkFromHomeOpenHelper.CAMPO_FECHAENTREGA_TRABAJO, p.getFechaEntrega().getTime());
        trabajo.put(WorkFromHomeOpenHelper.CAMPO_MONEDAPAGO_TRABAJO, p.getMonedaPago());
        trabajo.put(WorkFromHomeOpenHelper.CAMPO_REQUIEREINGLES_TRABAJO, p.getRequiereIngles() ? 1 : 0);
        SQLiteDatabase conn = sqlDbHelper.getWritableDatabase();
        conn.insert(WorkFromHomeOpenHelper.DATABASE_NAME_TRABAJO, WorkFromHomeOpenHelper.CAMPO_DESCRIPCION_TRABAJO, trabajo);
        conn.close();
    }

    @Override
    public void eliminarOferta(Trabajo trab){
        String[] args = {trab.getId().toString()};
        SQLiteDatabase conn = sqlDbHelper.getWritableDatabase();
        conn.delete(WorkFromHomeOpenHelper.DATABASE_NAME_TRABAJO, "_id = ?", args);
        conn.close();
    }

    @Override
    public List<Trabajo> listaTrabajo() {
        listaTrabajos = new ArrayList<Trabajo>();
        String[] columnas = {WorkFromHomeOpenHelper.CAMPO_ID_TRABAJO, WorkFromHomeOpenHelper.CAMPO_DESCRIPCION_TRABAJO, WorkFromHomeOpenHelper.CAMPO_HORASPRESUPUESTADAS_TRABAJO, WorkFromHomeOpenHelper.CAMPO_IDCATEG_TRABAJO, WorkFromHomeOpenHelper.CAMPO_PRECIOMAXIMOHORA_TRABAJO, WorkFromHomeOpenHelper.CAMPO_FECHAENTREGA_TRABAJO, WorkFromHomeOpenHelper.CAMPO_MONEDAPAGO_TRABAJO, WorkFromHomeOpenHelper.CAMPO_REQUIEREINGLES_TRABAJO};
        Cursor result = sqlDbHelper.getReadableDatabase().query(WorkFromHomeOpenHelper.DATABASE_NAME_TRABAJO, null, null, null, null, null, null);
        //Cursor result = sqlDbHelper.getReadableDatabase().rawQuery("SELECT name FROM sqlite_master WHERE type='table';", null);
        Trabajo t;
        while (result.moveToNext()) {
            String name = result.getString(0);
            int id = result.getInt(0);
            String desc = result.getString(1);
            int horasPresupuestadas = result.getInt(2);
            int idCateg = result.getInt(3);
            double precioMaximoHora = result.getDouble(4);
            Date fechaEntrega = new Date(result.getLong(5));
            int monedaPago = result.getInt(6);
            boolean requiereIngles = result.getInt(7) == 1;

            t = new Trabajo(id, desc, horasPresupuestadas, idCateg, precioMaximoHora, fechaEntrega, monedaPago, requiereIngles);
            listaTrabajos.add(t);
        }
        result.close();
        return listaTrabajos;
    }
}
