package dao;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import isi.frsf.utn.edu.ar.laboratorio03.Categoria;
import isi.frsf.utn.edu.ar.laboratorio03.Trabajo;

public interface TrabajoDao {
    public List<Categoria> listaCategoria() throws IOException, JSONException;

    public void crearOferta(Trabajo p);

    public void eliminarOferta(Trabajo p);

    public List<Trabajo> listaTrabajo();
}