package tp.sendmeal;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

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
