package tp.sendmeal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tp.sendmeal.domain.Plato;

public class PlatoRecyclerAdapter extends RecyclerView.Adapter<PlatoViewHolder> {
    private List<Plato> mDataSet;


    public PlatoRecyclerAdapter (List<Plato> myDataSet){
        mDataSet = myDataSet;
    }

    public PlatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_plato,parent,false);
        PlatoViewHolder vh = new PlatoViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(PlatoViewHolder holder, int position) {
        Plato plato = mDataSet.get(position);
        holder.imagenPlato.setImageResource(android.R.drawable.gallery_thumb);
        holder.nombrePlato.setText(plato.getTitulo());
        holder.precio.setText(plato.getPrecio().toString());

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}
