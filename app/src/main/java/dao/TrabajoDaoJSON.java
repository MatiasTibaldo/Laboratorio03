package dao;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import isi.frsf.utn.edu.ar.laboratorio03.Categoria;
import isi.frsf.utn.edu.ar.laboratorio03.MainActivity;
import isi.frsf.utn.edu.ar.laboratorio03.Trabajo;

public class TrabajoDaoJSON implements TrabajoDao{

    private ArrayList<Categoria> listaCategorias;

    public TrabajoDaoJSON() throws IOException, JSONException {
        try{
            String path = "/data/data/isi.frsf.utn.edu.ar.laboratorio03/files/categoria.txt";
            File categoriasFile = new File(path);
            if(categoriasFile.exists()){
                BufferedReader mInput = new BufferedReader(new FileReader(path));
                StringBuilder sb = new StringBuilder();
                String data;
                while ((data = mInput.readLine()) != null) {
                    sb.append(new String(data));
                }
                mInput.close();
                JSONArray categorias = (JSONArray) new JSONTokener(sb.toString()).nextValue();
                Categoria cat;
                ArrayList<Categoria> categs = new ArrayList<Categoria>();
                for(int i=0; i<categorias.length(); i++){
                    if(!categorias.isNull(i)){
                        JSONObject categoria = categorias.getJSONObject(i);
                        cat = new Categoria();
                        cat.setId(categoria.getInt("Id"));
                        cat.setDescripcion(categoria.getString("Descripcion"));
                        categs.add(cat);
                    }
                }
                listaCategorias = categs;
            }
            else{
                BufferedWriter buf = new BufferedWriter(new FileWriter(path));
                JSONArray categoriasJson = new JSONArray();
                JSONObject categoriaJson;
                int i = 0;
                for(Categoria c : Categoria.CATEGORIAS_MOCK){
                    categoriaJson = new JSONObject();
                    categoriaJson.put("Id", c.getId());
                    categoriaJson.put("Descripcion", c.getDescripcion());
                    categoriasJson.put(i, categoriaJson);
                    i++;
                }
                buf.write(categoriasJson.toString());
                buf.close();
                listaCategorias = new ArrayList<Categoria>();
                listaCategorias.addAll(Arrays.asList(Categoria.CATEGORIAS_MOCK));
            }
        }
        catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public List<Categoria> listaCategoria() throws IOException, JSONException {
        try{
            String path = "/data/data/isi.frsf.utn.edu.ar.laboratorio03/files/categoria.txt";
            File categoriasFile = new File(path);
            if(categoriasFile.exists()){
                BufferedReader mInput = new BufferedReader(new FileReader(path));
                StringBuilder sb = new StringBuilder();
                String data;
                while ((data = mInput.readLine()) != null) {
                    sb.append(new String(data));
                }
                mInput.close();
                JSONArray categorias = (JSONArray) new JSONTokener(sb.toString()).nextValue();
                Categoria cat;
                ArrayList<Categoria> categs = new ArrayList<Categoria>();
                for(int i=0; i<categorias.length(); i++){
                    if(!categorias.isNull(i)){
                        JSONObject categoria = categorias.getJSONObject(i);
                        cat = new Categoria();
                        cat.setId(categoria.getInt("Id"));
                        cat.setDescripcion(categoria.getString("Descripcion"));
                        categs.add(cat);
                    }
                }
                listaCategorias = categs;
            }
            return listaCategorias;
        }
        catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public void crearOferta(Trabajo p) {

    }

    @Override
    public void eliminarOferta(Trabajo p){

    }

    @Override
    public List<Trabajo> listaTrabajo() {
        return null;
    }
}
