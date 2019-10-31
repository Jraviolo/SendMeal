package tp.sendmeal.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ItemsPedido {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id_itemPedido")
    private Integer id;
    //@Embedded private Pedido pedido;
    @Embedded private Plato plato;
    private Integer cantidad;
    private Float precioItem;

    private long pedidoId;

    public ItemsPedido() {
    }

    public long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Plato getPlato() {
        return plato;
    }

    public void setPlato(Plato plato) {
        this.plato = plato;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecioItem() {
        return precioItem;
    }

    public void setPrecioItem(Float precioItem) {
        this.precioItem = precioItem;
    }
}
