package tp.sendmeal;


        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import android.widget.TextView;

        import androidx.recyclerview.widget.RecyclerView;

        import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

        import java.util.List;

        import tp.sendmeal.domain.ItemsPedido;


public class ItemPedidoRecyclerAdapter extends RecyclerView.Adapter<ItemPedidoRecyclerAdapter.ItemViewHolder> {

    public List<ItemsPedido> mDataSet;

    public static final int CODIGO_LISTA_ITEMS = 100;

    public ItemPedidoRecyclerAdapter (List<ItemsPedido> myDataSet){
        mDataSet = myDataSet;
    }

    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_item,parent,false);
        ItemViewHolder vh = new ItemViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        final ItemsPedido item = mDataSet.get(position);
        holder.nombrePlato.setText(item.getPlato().getTitulo());
        holder.cantidad.setNumber(item.getCantidad().toString());
        holder.precio.setText("$"+item.getPrecioItem());

        //define que elemento del array es
        holder.nombrePlato.setTag(position);
        holder.cantidad.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                System.out.println("cambio de "+oldValue+" a "+ newValue);
                mDataSet.get(position).setCantidad(newValue);
                holder.precio.setText("$"+item.getPrecioItem()*newValue);
            }
        });



    }



    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView nombrePlato;
        TextView precio;
        ElegantNumberButton cantidad;


        ItemViewHolder(View base){
            super(base);
            this.nombrePlato = (TextView) base.findViewById(R.id.FInombre);
            this.precio=(TextView) base.findViewById(R.id.FIprecio);
            this.cantidad= (ElegantNumberButton) base.findViewById(R.id.FIcantidad);
        }


    }

}