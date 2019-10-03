package tp.sendmeal;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import tp.sendmeal.domain.Plato;

import static android.app.PendingIntent.getActivity;
import static androidx.core.app.ActivityCompat.startActivityForResult;
import static androidx.core.content.ContextCompat.startActivity;

public class PlatoRecyclerAdapter extends RecyclerView.Adapter<PlatoRecyclerAdapter.PlatoViewHolder> {
    public List<Plato> mDataSet;



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

        //define que elemento del array es
        holder.btnEditar.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class PlatoViewHolder extends RecyclerView.ViewHolder {
        ImageView imagenPlato;
        TextView nombrePlato;
        TextView precio;

        Button btnEditar;

        private static final int CODIGO_LISTA_PLATO = 987;

        PlatoViewHolder(View base){
            super(base);
            this.imagenPlato = (ImageView) base.findViewById(R.id.FPimagen);
            this.nombrePlato = (TextView) base.findViewById(R.id.FPnombrePlato);
            this.precio = (TextView) base.findViewById(R.id.FPprecio);

            this.btnEditar=(Button) base.findViewById(R.id.FPbtnEditar);


            this.btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*Intent i = new Intent(,AltaPlato.class);
                    i.putExtra("INDICE",(int)btnEditar.getTag());
                    startActivityForResult(i,CODIGO_LISTA_PLATO);*/
                }
            });

            /*this.btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("Editar: "+btnEditar.getTag().toString());
                }
            });*/
        }

    }

}
