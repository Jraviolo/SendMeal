package tp.sendmeal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import tp.sendmeal.dao.PlatoRepository;
import tp.sendmeal.domain.Plato;

import static android.app.PendingIntent.getActivity;
import static androidx.core.app.ActivityCompat.startActivityForResult;
import static androidx.core.content.ContextCompat.startActivity;

public class PlatoRecyclerAdapter extends RecyclerView.Adapter<PlatoRecyclerAdapter.PlatoViewHolder> {

    public List<Plato> mDataSet;
    private Boolean buscar;

    public static final int CODIGO_LISTA_PLATO = 100;

    public PlatoRecyclerAdapter (List<Plato> myDataSet, boolean b){
        mDataSet = myDataSet;
        buscar = b;
    }

    public PlatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v;
        if (buscar == false) {
             v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_plato, parent, false);
        }
        else{
             v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.resultado_busqueda, parent, false);
        }
        PlatoViewHolder vh = new PlatoViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(PlatoViewHolder holder, final int position) {
        final Plato plato = mDataSet.get(position);
        holder.imagenPlato.setImageResource(R.drawable.ha);
        holder.nombrePlato.setText(plato.getTitulo());
        holder.precio.setText("$ "+plato.getPrecio().toString());

        if(buscar==false) {
            //define que elemento del array es
            holder.btnEditar.setTag(position);
            holder.btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String indice = view.getTag().toString();

                    Intent i = new Intent(view.getContext(), EditarPlato.class);
                    i.putExtra("INDICE", indice);
                    ((Activity) view.getContext()).startActivityForResult(i, CODIGO_LISTA_PLATO);
                }
            });


            /* Opcion ELIMINAR */

            holder.btnEliminar.setTag(position);
            holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View view) {
                                                          final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                                          builder.setMessage("Desea eliminar el plato \" " + plato.getTitulo() + "\"")
                                                                  .setTitle("Eliminar Plato")
                                                                  .setPositiveButton("Aceptar",
                                                                          new DialogInterface.OnClickListener() {
                                                                              @Override
                                                                              public void onClick(DialogInterface dlgInt, int i) {
                                                                                  mDataSet.remove(position);
                                                                                  PlatoRepository.getInstance().borrarPlato(plato, miHandler);
                                                                                  notifyDataSetChanged();
                                                                                  Toast.makeText(builder.getContext(), "El plato fue eliminado", Toast.LENGTH_LONG).show();

                                                                              }
                                                                          });
                                                          builder.setNegativeButton("Cancelar",
                                                                  new DialogInterface.OnClickListener() {
                                                                      @Override
                                                                      public void onClick(DialogInterface dlgInt, int i) {
                                                                          //Toast.makeText(builder.getContext(), "Cancelado", Toast.LENGTH_LONG).show();
                                                                      }
                                                                  });
                                                          AlertDialog dialog = builder.create();
                                                          dialog.show();
                                                      }
                                                  }
            );

            /* Opcion OFERTA */

            holder.btnOferta.setTag(position);
            holder.btnOferta.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        final String indice = view.getTag().toString();
                                                        final Activity vie = (Activity) view.getContext();
                                                        Runnable r = new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                try {
                                                                    Thread.currentThread().sleep(10000);
                                                                } catch (InterruptedException e) {
                                                                    e.printStackTrace();
                                                                }


                                                                Intent i = new Intent();
                                                                i.putExtra("INDICE", indice);
                                                                i.putExtra("NOMBRE", plato.getTitulo());
                                                                i.setAction(OfertaReceiver.EVENTO1);
                                                                vie.sendBroadcast(i);

                                                            }
                                                        };
                                                        Thread t1 = new Thread(r);
                                                        t1.start();
                                                    }
                                                }
            );

        }
        else{
            //LOGICA BOTON AGREGAR AL PEDIDO
        }
    }



    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class PlatoViewHolder extends RecyclerView.ViewHolder{
        ImageView imagenPlato;
        TextView nombrePlato;
        TextView precio;

        Button btnEditar;
        Button btnEliminar;
        Button btnOferta;
        Button btnAgregar;

        PlatoViewHolder(View base){
            super(base);

            if(buscar==false) {
                this.imagenPlato = (ImageView) base.findViewById(R.id.FPimagen);
                this.nombrePlato = (TextView) base.findViewById(R.id.FPnombrePlato);
                this.precio = (TextView) base.findViewById(R.id.FPprecio);

                this.btnEditar = (Button) base.findViewById(R.id.FPbtnEditar);
                this.btnEliminar = (Button) base.findViewById(R.id.FPbtnEliminar);
                this.btnOferta = (Button) base.findViewById(R.id.FPbtnOferta);
            }
            else{
                this.imagenPlato = (ImageView) base.findViewById(R.id.RBimagen);
                this.nombrePlato = (TextView) base.findViewById(R.id.RBnombrePlato);
                this.precio = (TextView) base.findViewById(R.id.RBprecio);
                this.btnAgregar = (Button) base.findViewById(R.id.RBbtnAgregar);
            }
        }


    }


    Handler miHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message m){
            mDataSet = PlatoRepository.getInstance().getListaPlatos();
            switch (m.arg1){
                case PlatoRepository._CONSULTA_PLATO:
                    break;
                case PlatoRepository._BORRADO_PLATO:

                    break;
            }
        }
    };

}
