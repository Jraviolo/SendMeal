package tp.sendmeal;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URI;
import java.util.List;

import tp.sendmeal.domain.Plato;

public class PlatoRecyclerAdapter extends RecyclerView.Adapter<PlatoRecyclerAdapter.PlatoViewHolder> {
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
        holder.imagenPlato.setImageResource(R.drawable.ha);
        holder.nombrePlato.setText(plato.getTitulo());
        holder.precio.setText("$ "+plato.getPrecio().toString());

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class PlatoViewHolder extends RecyclerView.ViewHolder {
        ImageView imagenPlato;
        TextView nombrePlato;
        TextView precio;

        PlatoViewHolder(View base){
            super(base);
            this.imagenPlato = (ImageView) base.findViewById(R.id.FPimagen);
            this.nombrePlato = (TextView) base.findViewById(R.id.FPnombrePlato);
            this.precio = (TextView) base.findViewById(R.id.FPprecio);

        }

    }

}
