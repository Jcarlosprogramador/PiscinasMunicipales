package uem.dam.seg.piscinasmunicipales_juancarlos.retrofict;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uem.dam.seg.piscinasmunicipales_juancarlos.R;
import uem.dam.seg.piscinasmunicipales_juancarlos.model.Graph;
import uem.dam.seg.piscinasmunicipales_juancarlos.model.Piscinas;

public class PiscinasAdapter extends RecyclerView.Adapter<PiscinasAdapter.PiscinaViewHolder>
        implements View.OnClickListener {
//recuperamos una lista de objetos de tipo graph que contiene las piscinas
        View.OnClickListener listener;
        List<Graph> listaPiscinas;

    public PiscinasAdapter(List<Graph> listaPiscinas) {
        this.listaPiscinas = listaPiscinas;
    }

    @NonNull
    @Override
    public PiscinaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_piscina, parent, false);
        v.setOnClickListener(listener);
        return new PiscinaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PiscinaViewHolder holder, int position) {
        holder.bindGrahp(listaPiscinas.get(position));
    }

    @Override
    public int getItemCount() {
        return listaPiscinas.size();
    }
// declaramos los 3 atributos que vamos a mostrar en el RecyclerWiew
    public static class PiscinaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombrePiscina;
        TextView tvLatitud;
        TextView tvLongitud;

// los referenciamos
        public PiscinaViewHolder(@NonNull View v) {
            super(v);
            tvNombrePiscina = v.findViewById(R.id.tvNombrePiscina);
            tvLatitud = v.findViewById(R.id.tvLatitud);
            tvLongitud = v.findViewById(R.id.tvLongitud);

        }
//asiganmos el valor recuperado de la clase graph
        public void bindGrahp(Graph p) {
            tvNombrePiscina.setText(p.getTitle());
            tvLatitud.setText("Latitud: "+p.getLocation().getLatitude());
            tvLongitud.setText("Longitud: "+ p.getLocation().getLongitude());
        }
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }
// cuando pulsamos el boton
    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }
}
