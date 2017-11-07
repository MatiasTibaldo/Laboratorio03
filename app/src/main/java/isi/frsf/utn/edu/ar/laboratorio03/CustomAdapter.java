package isi.frsf.utn.edu.ar.laboratorio03;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter{
    ArrayList<Trabajo> trabajos;
    Context context;
    private static LayoutInflater inflater=null;
    public CustomAdapter(MainActivity mainActivity, ArrayList<Trabajo> ls) {
        context=mainActivity;
        trabajos = ls;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return trabajos.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        TextView tvCategoria;
        TextView tvDescripcion;
        TextView tvHorasPresupuestadas;
        TextView tvMaxPrecioHoras;
        ImageView ivBandera;
        TextView tvFechaFin;
        CheckBox cbIngles;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.content_ofertalaboral, null);
        holder.tvCategoria = (TextView)rowView.findViewById(R.id.tvCategoria);
        holder.tvDescripcion = (TextView)rowView.findViewById(R.id.tvDescripcion);
        holder.tvHorasPresupuestadas = (TextView)rowView.findViewById(R.id.tvHorasPresupuestadas);
        holder.tvMaxPrecioHoras = (TextView)rowView.findViewById(R.id.tvMaxPrecioHoras);
        holder.ivBandera = (ImageView)rowView.findViewById(R.id.ivBandera);
        holder.tvFechaFin = (TextView)rowView.findViewById(R.id.tvFechaFin);
        holder.cbIngles = (CheckBox)rowView.findViewById(R.id.cbIngles);

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        if(trabajos.get(position).getCategoria() != null){
            holder.tvCategoria.setText(trabajos.get(position).getCategoria().getDescripcion());
        }
        else{
            holder.tvCategoria.setText("");
        }
        holder.tvDescripcion.setText(trabajos.get(position).getDescripcion());
        holder.tvHorasPresupuestadas.setText("Horas: " + trabajos.get(position).getHorasPresupuestadas());
        holder.tvMaxPrecioHoras.setText("Max $/Hora: " + String.format("%.2f", trabajos.get(position).getPrecioMaximoHora()));
        holder.ivBandera.setImageResource(trabajos.get(position).getBandera());
        holder.tvFechaFin.setText("Fecha Fin: " + fmt.format(trabajos.get(position).getFechaEntrega()));
        holder.cbIngles.setChecked(trabajos.get(position).getRequiereIngles());

        rowView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                return false;
            }
        });
        return rowView;
    }

}
